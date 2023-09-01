package com.phoenix;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;

/**
 * @Author phoenix
 * @Date 2023/9/1 9:00
 * @Version 1.0.0
 */
public class EncryptUtilTest {

    public static void main(String[] args) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //加密所需的salt
        textEncryptor.setPassword("phoenix");
        System.out.println(textEncryptor.encrypt("Common123qwer!@#"));
        System.out.println(textEncryptor.encrypt("123456"));
        System.out.println(textEncryptor.decrypt("PfDPA+y3YVDqtcZ0XvkPjL6AYJse5+da"));
    }

}
