package com.phoenix.core.utils;

public class PatientUtil {
    private static String space = "ID#%92ab@)s4827z";

    public PatientUtil() {
    }

    public static String getEncryptionIdNo(String idNo) throws Exception {
        String salt = getIdSalt(idNo);
        String result = EncryptionUtil.encode(idNo, salt);
        if (result == null) {
            throw new Exception("证件号码加密错误");
        } else {
            return EncryptionUtil.encode(idNo, salt);
        }
    }

    public static String getHideIdNo(String idNo) {
        if (idNo.length() == 18) {
            return idNo.substring(0, 10) + "******" + idNo.substring(16);
        } else {
            return idNo.length() > 12 ? idNo.substring(0, idNo.length() - 7) + "******" + idNo.substring(idNo.length() - 1) : idNo.substring(0, idNo.length() - 5) + "****" + idNo.substring(idNo.length() - 1);
        }
    }

    public static String getIdSalt(String idNo) {
        return idNo.length() > 16 ? idNo.substring(idNo.length() - 16) : space.substring(0, 16 - idNo.length()) + idNo;
    }

    public static String getPatientOriginalIdNo(String encryptionIdNo, String encryptionSalt) {
        String salt = EncryptionUtil.decode(encryptionSalt);
        return EncryptionUtil.decode(encryptionIdNo, salt);
    }

    public static String getBlankListRedisKey(String patientId) {
        return "RESERVE:BLACKLIST:" + patientId;
    }
}

