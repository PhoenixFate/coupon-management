package com.phoenix.core.controller;


import com.alibaba.fastjson.JSON;
import com.phoenix.core.entity.TerminalInfoBo;
import com.phoenix.core.exception.ServerException;
import com.phoenix.core.utils.EncryptionUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {
    protected static Logger logger = LoggerFactory.getLogger(BaseController.class);

    public BaseController() {
    }

    protected TerminalInfoBo getZzTerminalInfoBo(HttpServletRequest request, String requestBody) {
        TerminalInfoBo zzTerminalInfoBo = new TerminalInfoBo();

        try {
            InputStream inputStream = request.getInputStream();
            String encoding = request.getCharacterEncoding() != null ? request.getCharacterEncoding() : "utf-8";
            String str = this.getRequestStr(inputStream, encoding);
            String reqStr = null;
            if (StringUtils.isBlank(str)) {
                reqStr = EncryptionUtil.decode(requestBody);
            } else {
                reqStr = EncryptionUtil.decode(str);
            }

            zzTerminalInfoBo.setHosCode(request.getParameter("hosCode"));
            zzTerminalInfoBo.setProductId(request.getParameter("productId"));
            zzTerminalInfoBo.setVersion(request.getParameter("version"));
            zzTerminalInfoBo.setSversion(request.getParameter("sversion"));
            zzTerminalInfoBo.setPltId(request.getParameter("pltId"));
            zzTerminalInfoBo.setMachineCode(request.getParameter("machineCode"));
            zzTerminalInfoBo.setParamContent(reqStr);
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        return zzTerminalInfoBo;
    }

    protected String parseParamContent(HttpServletRequest request) {
        return this.parseParamContent(request, false);
    }

    protected String parseParamContent(HttpServletRequest request, boolean decode) {
        try {
            InputStream inputStream = request.getInputStream();
            String encoding = request.getCharacterEncoding() != null ? request.getCharacterEncoding() : "utf-8";
            String reqStr = this.getRequestStr(inputStream, encoding);
            if (decode) {
                reqStr = EncryptionUtil.decode(reqStr);
            }

            return reqStr;
        } catch (Exception var6) {
            logger.error("error{}", var6);
            return null;
        }
    }

    protected String getRequestStr(InputStream inputStream, String charsetName) {
        String ret = "";
        String tempString = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charsetName));

            for(tempString = bufferedReader.readLine(); tempString != null; tempString = bufferedReader.readLine()) {
                ret = ret + tempString;
            }

            tempString = null;
            bufferedReader.close();
            bufferedReader = null;
        } catch (Exception var7) {
            logger.error("getRequestStr error{}" + var7);
        }

        return ret.trim();
    }

    protected String getResult(Object t) {
        if (t instanceof ServerException) {
            return this.getException((ServerException)t);
        } else {
            String result = null;
            if (t instanceof String) {
                result = (String)t;
            } else {
                result = JSON.toJSONString(t);
            }

            return EncryptionUtil.encode(result);
        }
    }

    protected String getResult(String uuid, Object t) {
        if (t instanceof ServerException) {
            return this.getException((ServerException)t);
        } else {
            String result = JSON.toJSONString(t);
            return EncryptionUtil.encode(result);
        }
    }

    protected String getException(ServerException exception) {
        Map<String, Object> exceptionMap = new HashMap();
        exceptionMap.put("rspCode", exception.getExpCode());
        exceptionMap.put("rspMsg", exception.getExpMsg());
        String result = JSON.toJSONString(exceptionMap);
        return EncryptionUtil.encode(result);
    }

    protected String getRequestBody(String requestBody) {
        String reqStr = null;

        try {
            reqStr = EncryptionUtil.decode(requestBody);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return reqStr;
    }
}
