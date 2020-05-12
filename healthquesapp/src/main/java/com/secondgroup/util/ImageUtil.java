/**
 * FileName: ImageUtil
 * Author:   ZSX
 * Date:     2020/4/11 22:27
 * Description: 判断上传文件是否是图片类型
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.secondgroup.util;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈判断上传文件是否是图片类型〉
 *
 * @author ZSX
 * @create 2020/4/11
 * @since 1.0.0
 */
public class ImageUtil {
    /**
     * 通过读取文件并获取其width及height的方式，来判断判断当前文件是否图片，这是一种非常简单的方式。
     * @param multipartFile
     * @return
     */
    public static boolean isImage(MultipartFile multipartFile) throws IOException {

        //MultipartFile 转 File
        File imageFile= new File(multipartFile.getOriginalFilename());
        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),imageFile);

        if (!imageFile.exists()) {
            return false;
        }
        Image img = null;
        try {
            img = ImageIO.read(imageFile);
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            img = null;
        }
    }
}