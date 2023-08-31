package com.phoenix.core.utils;

import java.nio.charset.Charset;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.util.DigestUtils;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class EncryptionUtil {
    private static String SALT = "phoenix)(*!@#$%^&*^";
    private static char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static String sKey = "phoenixPhoenix12";
    public static final String ENCRYPT_KEY_INNER = "phoenixPhoenix12";
    public static final String KEY_ALGORITHM = "AES";
    public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";

    public EncryptionUtil() {
    }

    public static String asymEncryptMD5SALT(String value) {
        byte[] b = Base64.encodeBase64(DigestUtils.md5Digest((value + SALT).getBytes(Charset.forName("UTF-8"))));
        return new String(b, Charset.forName("UTF-8"));
    }

    public static String asymEncryptMD5SALT(String value, String salt) {
        byte[] b = Base64.encodeBase64(DigestUtils.md5Digest((value + salt).getBytes(Charset.forName("UTF-8"))));
        return new String(b, Charset.forName("UTF-8"));
    }

    public static String asymEncryptMD5(String value) {
        byte[] b = value.getBytes();

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(b);
            byte[] val = md.digest();
            int j = val.length;
            char[] str = new char[j * 2];
            int k = 0;

            for(int i = 0; i < j; ++i) {
                byte byte0 = val[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            return (new String(str)).toLowerCase();
        } catch (NoSuchAlgorithmException var9) {
            var9.printStackTrace();
            return value;
        }
    }

    public static String encode(String sSrc) {
        try {
            byte[] data = sSrc.getBytes("UTF-8");
            Key k = toKey(sKey.getBytes());
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
            cipher.init(1, k);
            Encoder encoder = java.util.Base64.getEncoder();
            return encoder.encodeToString(cipher.doFinal(data));
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static String encode(String sSrc, String sKey) {
        try {
            byte[] data = sSrc.getBytes("UTF-8");
            Key k = toKey(sKey.getBytes());
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
            cipher.init(1, k);
            Encoder encoder = java.util.Base64.getEncoder();
            return encoder.encodeToString(cipher.doFinal(data));
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static String decode(String sSrc) {
        Decoder decoder = java.util.Base64.getDecoder();
        try {
            byte[] data = decoder.decode(sSrc);
            Key k = toKey(sKey.getBytes());
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, k);
            return new String(cipher.doFinal(data), "utf-8");
        } catch (Exception var5) {
            return null;
        }
    }

    public static String decode(String sSrc, String sKey) {
        Decoder decoder = java.util.Base64.getDecoder();
        try {
            byte[] data = decoder.decode(sSrc);
            Key k = toKey(sKey.getBytes());
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, k);
            return new String(cipher.doFinal(data), "utf-8");
        } catch (Exception var6) {
            return null;
        }
    }

    public static String encryptDES(String value, String password) {
        SecretKey key = null;

        try {
            DESKeySpec ds = new DESKeySpec(password.getBytes());
            SecretKeyFactory sf = SecretKeyFactory.getInstance("DES");
            key = sf.generateSecret(ds);
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        try {
            byte[] unencryptedByteArray = value.getBytes("UTF-8");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(1, key);
            byte[] encryptedBytes = cipher.doFinal(unencryptedByteArray);
            byte[] encodedBytes = Base64.encodeBase64(encryptedBytes);
            return new String(encodedBytes);
        } catch (Exception var7) {
            var7.printStackTrace();
            return null;
        }
    }

    public static String decryptDES(String value, String password) {
        SecretKey key = null;

        try {
            DESKeySpec ds = new DESKeySpec(password.getBytes());
            SecretKeyFactory sf = SecretKeyFactory.getInstance("DES");
            key = sf.generateSecret(ds);
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        byte[] decodedBytes = Base64.decodeBase64(value.getBytes());

        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(2, key);
            byte[] unencryptedByteArray = cipher.doFinal(decodedBytes);
            return new String(unencryptedByteArray, "UTF-8");
        } catch (Exception var6) {
            var6.printStackTrace();
            return null;
        }
    }

    public static Key toKey(byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        return secretKey;
    }
}
