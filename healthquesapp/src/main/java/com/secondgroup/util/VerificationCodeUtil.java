/**
 * FileName: VerificationCodeUtil
 * Author:   ZSX
 * Date:     2020/4/12 11:01
 * Description: 生成6位随机验证码工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.secondgroup.util;

/**
 * 〈一句话功能简述〉<br> 
 * 〈生成6位随机验证码工具类〉
 *
 * @author ZSX
 * @create 2020/4/12
 * @since 1.0.0
 */
public class VerificationCodeUtil {
    /**
     * 1.纯数字随机验证码
     * @return
     */
    public static String getNumeral(){
        String code = "";
        for (int i = 0; i < 6; i++) {
            code = code + (int)(Math.random() * 9);
        }
        return code;
    }

    /**
     * 2.纯字母随机验证码
     * @return
     */
    public static String getAlphabet(){
        String code = "";
        char[] ch = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K','L', 'M', 'N', 'O', 'P', 'Q','R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for (int i = 0; i < 6; i++) {
            int index = (int)(Math.random() * ch.length);
            code = code + ch[index];
        }
        return code;
    }

    public static void main(String[] args) {
        System.out.println("纯数字验证码："+getNumeral());
        System.out.println("纯字母验证码："+getAlphabet());
    }

}