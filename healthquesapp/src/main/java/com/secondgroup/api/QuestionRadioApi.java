/**
 * FileName: QuestionTypeRadioApi
 * Author:   ZSX
 * Date:     2020/4/14 14:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.secondgroup.api;

import com.alibaba.fastjson.JSONObject;
import com.secondgroup.pojo.*;
import com.secondgroup.service.QuestionRadioService;
import com.secondgroup.service.QuestionTypeService;
import com.secondgroup.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ZSX
 * @create 2020/4/14
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/user")
public class QuestionRadioApi {

    @Autowired
    QuestionRadioService questionRadioService;

    @Autowired
    QuestionTypeService questionTypeService;

    //通过问卷模板id查找问题List
    @PostMapping("/radio")
    public Object radio(@RequestBody HealthyMessage healthyMessage){
        JSONObject jsonObject = new JSONObject();
        System.out.println(healthyMessage.getQuesTplId());
        List<QuestionRadio> questionRadios = questionRadioService
                .selectAllByTemplateId(healthyMessage.getQuesTplId());
        if (questionRadios.isEmpty()){
            jsonObject.put(Const.MESSAGE, "查询问题消息失败！");
            return jsonObject;
        }
        List<Answer> answers = new ArrayList<Answer>();
        //通过模板id查询模板信息放入healthyMessage返回给前端
        for (QuestionRadio s : questionRadios) {
            //查找问题类型
            QuestionType questionType =
                    questionTypeService.selectByPrimaryKey(s.getTypeId());
            Answer answer = new Answer();
            answer.setQuestion(s.getQuestion());
            answer.setOption(s.getOptions());
            answer.setType(questionType.getTypeName());
            answers.add(answer);
        }
        //jsonObject.put(Const.HealthyMessages, questionTypeRadios);
        jsonObject.put(Const.MESSAGE, "查询问题消息成功！");
        jsonObject.put(Const.ANSWERS, answers);
        return jsonObject;
    }

}