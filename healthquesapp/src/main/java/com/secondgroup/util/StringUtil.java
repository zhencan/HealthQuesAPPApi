/**
 * FileName: StringUtil
 * Author:   ZSX
 * Date:     2020/4/9 16:53
 * Description: 判断字符串是否为空的工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.secondgroup.util;

/**
 * 〈一句话功能简述〉<br> 
 * 〈判断字符串是否为空的工具类〉
 *
 * @author ZSX
 * @create 2020/4/9
 * @since 1.0.0
 */
public class StringUtil {

    public static boolean isEmpty(String s) {
        return s == null || "".equals(s);
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

}