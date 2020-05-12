/**
 * FileName: QuestionTypeRadioService
 * Author:   ZSX
 * Date:     2020/4/14 14:00
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.secondgroup.service;

import com.secondgroup.mapper.QuestionRadioMapper;
import com.secondgroup.pojo.QuestionRadio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ZSX
 * @create 2020/4/14
 * @since 1.0.0
 */
@Service
public class QuestionRadioService {
    @Autowired
    QuestionRadioMapper questionRadioMapper;

    public List<QuestionRadio> selectAllByTemplateId(Integer id) {
        return questionRadioMapper.selectAllByTemplateId(id);
    }
}