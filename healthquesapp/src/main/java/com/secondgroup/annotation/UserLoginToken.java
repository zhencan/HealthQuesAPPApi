/**
 * FileName: UserLoginToken
 * Author:   ZSX
 * Date:     2020/4/7 14:07
 * Description: 需要登录才能进行操作的注解
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.secondgroup.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 〈一句话功能简述〉<br> 
 * 〈需要登录才能进行操作的注解〉
 *
 * @author ZSX
 * @create 2020/4/7
 * @since 1.0.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLoginToken {
    boolean required() default true;
}
