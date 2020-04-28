/**
 * FileName: ImgConfig
 * Author:   ZSX
 * Date:     2020/4/11 10:34
 * Description: 接入虚拟路径（解决重启服务器才显示图片的问题）
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.secondgroup.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 〈一句话功能简述〉<br> 
 * 〈接入虚拟路径（解决重启服务器才显示图片的问题）〉
 *
 * @author ZSX
 * @create 2020/4/11
 * @since 1.0.0
 */

@Configuration
public class ImgConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // addResourceHandler: （ 存储图片的虚拟路径，在 static 目录下的 /image/user 文件夹，用于存储上传图片）
        // addResourceLocations: （ file: + 存储图片的路径）
        // th:src="@{/image/user/文件名}"
        //registry.addResourceHandler("/image/user/**").addResourceLocations("file:" + "/image/user/");
        registry.addResourceHandler("classpath:static/image/user/**")
                .addResourceLocations("file:" + "static/image/user/");
    }
}
