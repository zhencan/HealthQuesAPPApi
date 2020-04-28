/**
 * FileName: MD5Util
 * Author:   ZSX
 * Date:     2020/4/9 16:56
 * Description: MD5-工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.secondgroup.util;

import java.security.MessageDigest;

/**
 * 〈一句话功能简述〉<br> 
 * 〈MD5-工具类〉
 *
 * @author ZSX
 * @create 2020/4/9
 * @since 1.0.0
 */
public class MD5Util {

    public static String digest16(String inStr) {

        return digest(inStr, 16);

    }

    public static String digest(String inStr) {

        return digest(inStr, 32);

    }

    private static String digest(String inStr, int rang) {

        MessageDigest md5 = null;

        if ( StringUtil.isEmpty(inStr) ) {

            return "";

        }

        try {

            md5 = MessageDigest.getInstance("MD5");  //取得算法

        } catch (Exception e) {

            e.printStackTrace();

            return "";

        }

        char[] charArray = inStr.toCharArray();

        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {

            byteArray[i] = (byte) charArray[i];

        }

        byte[] md5Bytes = md5.digest(byteArray);  //加密

        StringBuilder hexValue = new StringBuilder();

        for (int i = 0; i < md5Bytes.length; i++) {

            int val = ((int) md5Bytes[i]) & 0xff;

            if (val < 16)

                hexValue.append("0");

            hexValue.append(Integer.toHexString(val));

        }

        if ( rang == 32 ) {

            return hexValue.toString();

        } else {

            return hexValue.toString().substring(8, 24);//转换为32位字符串

        }

    }

    public static void main(String args[]) {
        String s = "123";
        System.out.println(digest(s));
    }

}