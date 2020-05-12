/**
 * FileName: Answer
 * Author:   ZSX
 * Date:     2020/4/14 15:18
 * Description: 接收前端问卷答案
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.secondgroup.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 〈一句话功能简述〉<br> 
 * 〈接收前端问卷答案〉
 *
 * @author ZSX
 * @create 2020/4/14
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    private String question;

    private String option;

    private String type;
}