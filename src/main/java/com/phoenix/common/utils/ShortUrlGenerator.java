package com.phoenix.common.utils;

import java.util.Random;

import com.phoenix.core.utils.MD5Util;

public class ShortUrlGenerator {
   
   public static String getRandomString(int length){
	    if(length < 6 || length > 10){
	    	length  = 6;
	    }
	    //定义一个字符串（A-Z，a-z，0-9）即62位；
	    String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
	    //由Random生成随机数
	        Random random=new Random();  
	        StringBuffer sb=new StringBuffer();
	        //长度为几就循环几次
	        for(int i=0; i<length; ++i){
	          //产生0-61的数字
	          int number=random.nextInt(62);
	          //将产生的数字通过length次承载到sb中
	          sb.append(str.charAt(number));
	        }
	        //将承载的字符转换成字符串
	        return sb.toString();
	}
  
   public static String[] shortUrl(String url) {  
      // 可以自定义生成 MD5 加密字符传前的混合 KEY  
      String key = "mengdelong" ;  
      // 要使用生成 URL 的字符  
      String[] chars = new String[] { "a" , "b" , "c" , "d" , "e" , "f" , "g" , "h" ,  
             "i" , "j" , "k" , "l" , "m" , "n" , "o" , "p" , "q" , "r" , "s" , "t" ,  
             "u" , "v" , "w" , "x" , "y" , "z" , "0" , "1" , "2" , "3" , "4" , "5" ,  
             "6" , "7" , "8" , "9" , "A" , "B" , "C" , "D" , "E" , "F" , "G" , "H" ,  
             "I" , "J" , "K" , "L" , "M" , "N" , "O" , "P" , "Q" , "R" , "S" , "T" ,  
             "U" , "V" , "W" , "X" , "Y" , "Z"  
  
      };  
      // 对传入网址进行 MD5 加密  
      String sMD5EncryptResult = MD5Util.MD5Encode(key + url, "UTF-8");  
      String hex = sMD5EncryptResult;  
      String[] resUrl = new String[4];  
      for ( int i = 0; i < 4; i++) {  
          // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算  
          String sTempSubString = hex.substring(i * 8, i * 8 + 8);  
          // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用 long ，则会越界  
          long lHexLong = 0x3FFFFFFF & Long.parseLong (sTempSubString, 16);  
          String outChars = "" ;  
          for ( int j = 0; j < 6; j++) {  
             // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引  
             long index = 0x0000003D & lHexLong;  
             // 把取得的字符相加  
             outChars += chars[( int ) index];  
             // 每次循环按位右移 5 位  
             lHexLong = lHexLong >> 5;  
          }  
          // 把字符串存入对应索引的输出数组  
          resUrl[i] = outChars;  
      }  
      return resUrl;  
   }  
}
