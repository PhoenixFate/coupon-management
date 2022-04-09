package com.phoenix.core.utils;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ErrorLogUtils {
    private static final String path = "/home/APP_LOG/jiankang51/";
    private static final File fErrorOutPath = new File("/home/APP_LOG/jiankang51/");
    private static SimpleDateFormat fDateformat_File = new SimpleDateFormat("yyyyMMdd_HHmmss");
    public static int C_iZero = 0;
    public static String C_sBlank = "";

    public ErrorLogUtils() {
    }

    public static final String outLogFile(String msg, Exception e) {
        return outLogFile(msg, e, (String)null);
    }

    public static final String outLogFile(String msg, Exception e, String fileNamePre) {
        String errorfile = "";

        try {
            File fErrorFile;
            if (e != null) {
                fErrorFile = getNowDateFile(fErrorOutPath.getAbsolutePath(), ".error", fileNamePre);
            } else {
                fErrorFile = getNowDateFile(fErrorOutPath.getAbsolutePath(), ".log", fileNamePre);
            }

            errorfile = fErrorFile.getAbsolutePath();
            PrintWriter fPw = new PrintWriter(new File(errorfile));

            try {
                if (msg != null) {
                    fPw.write(msg.toString().toCharArray());
                }

                if (e != null) {
                    e.printStackTrace(fPw);
                }

                fPw.flush();
            } finally {
                fPw.close();
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        }

        return errorfile;
    }

    public static File getNowDateFile(String aPath, String aExt, String pre) {
        String fFileHead = fDateformat_File.format(new Date());
        int i = C_iZero;
        File fFile = new File(getPathStr_hasEnd(aPath));
        if (pre != null) {
            fFileHead = pre + fFileHead;
        }

        if (!fFile.exists()) {
            fFile.mkdirs();
        }

        do {
            String fFileName = getPathStr_hasEnd(aPath) + fFileHead;
            if (i == C_iZero) {
                fFileName = fFileName + aExt;
            } else {
                fFileName = fFileName + i + aExt;
            }

            fFile = new File(fFileName);
            ++i;
        } while(fFile.exists());

        return fFile;
    }

    public static final String getPathStr_hasEnd(String aPath) {
        String result = getPathStr(aPath);
        if (!result.endsWith("\\") && !result.endsWith("/")) {
            result = result + File.separator;
        }

        return result;
    }

    public static final String getPathStr(String aPath) {
        if (isBlank(aPath)) {
            return aPath;
        } else {
            aPath = aPath.trim();
            int fEndPos = aPath.length();

            for(int i = aPath.length() - 1; i >= 0; --i) {
                char fChar = aPath.charAt(i);
                if (fChar != '\\' && fChar != '/') {
                    break;
                }

                --fEndPos;
            }

            String result = aPath.substring(0, fEndPos);
            if (result.endsWith(":")) {
                result = result + File.separator;
            }

            return result;
        }
    }

    public static boolean isBlank(String aStr) {
        if (aStr == null) {
            return true;
        } else {
            return aStr.trim().equalsIgnoreCase(C_sBlank);
        }
    }
}
