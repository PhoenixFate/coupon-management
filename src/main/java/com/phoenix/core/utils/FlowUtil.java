package com.phoenix.core.utils;

import com.phoenix.core.exception.ServerException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

public class FlowUtil {
    private static String appendStr = "0000000";
    private static int index = 0;
    public static int rsptIndex = 1;
    public static int logIndex = 1;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    private static HashMap<String, String> steamPrefixMap = new HashMap();
    private static String ipAppend = "";
    private static AtomicLong atomic = new AtomicLong(1L);

    static {
        steamPrefixMap.put("02", "C002");
        steamPrefixMap.put("03", "D002");
        steamPrefixMap.put("06", "Y002");
        steamPrefixMap.put("04", "B002");
        steamPrefixMap.put("09", "M002");
        String ip = getLocalIP();
        if (ip != null && ip.trim().length() > 0) {
            ipAppend = ip.substring(ip.lastIndexOf(".") + 1);
            if (ipAppend.length() == 1) {
                ipAppend = "00" + ipAppend;
            } else if (ipAppend.length() == 2) {
                ipAppend = "0" + ipAppend;
            }
        }

    }

    public FlowUtil() {
    }

    public static String getFlow() {
        return DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMddHHmmss") + getIndexStr();
    }

    public static String generateFlow() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().toLowerCase().replace("-", "");
    }

    private static synchronized String getIndexStr() {
        ++index;
        if (index > 999) {
            index = 1;
        }

        String indexStr = String.valueOf(index);
        return (ipAppend + appendStr).substring(0, 6 - indexStr.length()) + indexStr;
    }

    public static synchronized String getHspRcptStreamNoIndex(String hosCode, String payBizEnum, String payMethodId) throws ServerException {
        String orderIndex = (String)steamPrefixMap.get(payMethodId);
        if (orderIndex == null) {
            throw new ServerException("1045");
        } else if (payBizEnum == null) {
            throw new ServerException("1023");
        } else {
            String ret = orderIndex + payBizEnum + hosCode + sdf.format(new Date()) + getIndexStr();
            if (ret.length() > 33) {
                ret = ret.substring(0, 15) + ret.substring(ret.length() - 18);
            }

            return ret;
        }
    }

    public static synchronized String getHspRcptStreamNoIndexByNew(String hosCode, String payBizEnum) throws ServerException {
        String orderIndex = (String)steamPrefixMap.get(payBizEnum);
        if (orderIndex == null) {
            throw new ServerException("1045");
        } else if (payBizEnum == null) {
            throw new ServerException("1023");
        } else {
            String ret = orderIndex + payBizEnum + hosCode + sdf.format(new Date()) + getIndexStr();
            if (ret.length() > 32) {
                ret = ret.substring(0, 15) + ret.substring(ret.length() - 17);
            }

            return ret;
        }
    }

    public static synchronized String getLogFlowIdByType(String logType, String logCode) {
        String returnIndex = "";
        String baseFlow = "";
        if ("1".equals(logType)) {
            baseFlow = "base_";
        } else if ("2".equals(logType)) {
            baseFlow = "trans_";
        }

        Date now_date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = df.format(now_date);
        ++logIndex;
        if (logIndex > 9999) {
            logIndex = 1;
        }

        if (rsptIndex < 10) {
            returnIndex = baseFlow + logCode + time + "000" + logIndex;
        } else if (logIndex < 100) {
            returnIndex = baseFlow + logCode + time + "00" + logIndex;
        } else if (logIndex < 1000) {
            returnIndex = baseFlow + logCode + time + "0" + logIndex;
        } else {
            returnIndex = baseFlow + logCode + time + logIndex;
        }

        return "HSPS" + returnIndex;
    }

    public static synchronized String getTransFlowId(String logCode) {
        String returnIndex = "";
        String baseFlow = "trans_";
        Date now_date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = df.format(now_date);
        ++logIndex;
        if (logIndex > 9999) {
            logIndex = 1;
        }

        if (rsptIndex < 10) {
            returnIndex = baseFlow + logCode + time + "000" + logIndex;
        } else if (logIndex < 100) {
            returnIndex = baseFlow + logCode + time + "00" + logIndex;
        } else if (logIndex < 1000) {
            returnIndex = baseFlow + logCode + time + "0" + logIndex;
        } else {
            returnIndex = baseFlow + logCode + time + logIndex;
        }

        return "HSPS" + returnIndex;
    }

    public static synchronized String getLisReportFlowId(String hoscode) {
        String returnIndex = "";
        String baseFlow = "report";
        Date now_date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = df.format(now_date);
        ++logIndex;
        if (logIndex > 9999) {
            logIndex = 1;
        }

        if (rsptIndex < 10) {
            returnIndex = baseFlow + hoscode + time + "000" + logIndex;
        } else if (logIndex < 100) {
            returnIndex = baseFlow + hoscode + time + "00" + logIndex;
        } else if (logIndex < 1000) {
            returnIndex = baseFlow + hoscode + time + "0" + logIndex;
        } else {
            returnIndex = baseFlow + hoscode + time + logIndex;
        }

        return returnIndex;
    }

    public static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1) {
            isWindowsOS = false;
        }

        return isWindowsOS;
    }

    public static String getLocalIP() {
        String sIP = "";
        InetAddress ip = null;

        try {
            if (isWindowsOS()) {
                ip = InetAddress.getLocalHost();
            } else {
                boolean bFindIP = false;
                Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();

                label43:
                while(true) {
                    while(true) {
                        if (!netInterfaces.hasMoreElements() || bFindIP) {
                            break label43;
                        }

                        NetworkInterface ni = (NetworkInterface)netInterfaces.nextElement();
                        Enumeration ips = ni.getInetAddresses();

                        while(ips.hasMoreElements()) {
                            ip = (InetAddress)ips.nextElement();
                            if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                                bFindIP = true;
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        if (ip != null && ip.getHostAddress().indexOf(":") < 0) {
            sIP = ip.getHostAddress();
        }

        System.out.println(sIP);
        return sIP;
    }

    public static synchronized String getOneRcptStreamNoIndexByNew(String terminalCode, String payBiz) throws ServerException {
        if (StringUtils.isEmpty(payBiz)) {
            throw new ServerException("1023");
        } else if (StringUtils.isEmpty(terminalCode)) {
            throw new ServerException("9198");
        } else {
            if (terminalCode.length() > 8) {
                terminalCode = terminalCode.substring(0, 8);
            } else if (terminalCode.length() < 8) {
                for(int i = terminalCode.length(); i < 8; ++i) {
                    terminalCode = terminalCode + "0";
                }
            }

            String ret = sdf.format(new Date()) + terminalCode;
            if (payBiz.toString().length() == 1) {
                ret = ret + "0";
            }

            ret = ret + payBiz;
            String seqNum = String.valueOf(atomic.getAndIncrement());
            int len = seqNum.length();
            int i;
            if (len >= 6) {
                seqNum = seqNum.substring(len - 6, len);
            } else {
                for(i = 0; i < 6 - len; ++i) {
                    seqNum = "0" + seqNum;
                }
            }

            ret = ret + seqNum;
            len = ret.length();
            if (len >= 30) {
                ret = ret.substring(len - 30, len);
            } else {
                for(i = 0; i < 30 - len; ++i) {
                    ret = ret + "0";
                }
            }

            return ret;
        }
    }

    public static synchronized String generateRefundRcptStreamNo(String hosCode, String msstCode) {
        if (StringUtils.isEmpty(msstCode)) {
            msstCode = "";
        }

        if (hosCode.length() > 8) {
            hosCode = hosCode.substring(0, 8);
        } else if (hosCode.length() < 8) {
            for(int i = hosCode.length(); i < 8; ++i) {
                hosCode = hosCode + "0";
            }
        }

        String ret = sdf.format(new Date()) + hosCode;
        if (msstCode.toString().length() == 1) {
            ret = ret + "0";
        }

        ret = ret + msstCode;
        String seqNum = String.valueOf(atomic.getAndIncrement());
        int len = seqNum.length();
        int i;
        if (len >= 6) {
            seqNum = seqNum.substring(len - 6, len);
        } else {
            for(i = 0; i < 6 - len; ++i) {
                seqNum = "0" + seqNum;
            }
        }

        ret = ret + seqNum;
        len = ret.length();
        if (len >= 29) {
            ret = ret.substring(len - 29, len);
        } else {
            for(i = 0; i < 29 - len; ++i) {
                ret = ret + "0";
            }
        }

        return "R" + ret;
    }
}
