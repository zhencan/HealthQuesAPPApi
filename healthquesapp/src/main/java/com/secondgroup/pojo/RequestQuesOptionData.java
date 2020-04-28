/**
 * FileName: RequestQuesOptionData
 * Author:   ZSX
 * Date:     2020/4/14 17:38
 * Description: 接收问题答案的数据对象类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.secondgroup.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈接收问题答案的数据对象类〉
 *
 * @author ZSX
 * @create 2020/4/14
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestQuesOptionData {
    private Integer healthyMessageId;
    private List<Answer> answers;
}