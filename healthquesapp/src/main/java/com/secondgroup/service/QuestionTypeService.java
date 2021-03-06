/**
 * FileName: QuestionTypeService
 * Author:   ZSX
 * Date:     2020/4/14 14:41
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.secondgroup.service;

import com.secondgroup.mapper.QuestionTypeMapper;
import com.secondgroup.pojo.QuestionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ZSX
 * @create 2020/4/14
 * @since 1.0.0
 */
@Service
public class QuestionTypeService {

    @Autowired
    QuestionTypeMapper questionTypeMapper;

    public QuestionType selectByPrimaryKey(Integer typeId) {
        return questionTypeMapper.selectByPrimaryKey(typeId);
    }
}