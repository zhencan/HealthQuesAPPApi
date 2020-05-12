/**
 * FileName: QuestionnaireTemplateApi
 * Author:   ZSX
 * Date:     2020/4/14 10:35
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.secondgroup.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.secondgroup.annotation.UserLoginToken;
import com.secondgroup.pojo.*;
import com.secondgroup.service.HealthyMessageService;
import com.secondgroup.service.QuestionnaireTemplateService;
import com.secondgroup.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class HealthyMessageApi {

    @Autowired
    HealthyMessageService healthyMessageService;
    @Autowired
    QuestionnaireTemplateService questionnaireTemplateService;

    //通过用户id查找健康问卷消息列表List
    @UserLoginToken
    @PostMapping("/healthyMessage")
    public Object healthyMessage(@RequestBody User user){
        JSONObject jsonObject = new JSONObject();
        List<HealthyMessage> healthyMessages = healthyMessageService.selectAllByUserId(user.getId());
        if (healthyMessages.isEmpty()){
            jsonObject.put(Const.MESSAGE, "查询用户健康问卷消息失败！");
            return jsonObject;
        }
        //通过模板id查询模板信息放入healthyMessage返回给前端
        for (HealthyMessage s : healthyMessages) {
            //System.out.println(s.getQuesTplId());
            QuestionnaireTemplate questionnaireTemplate =
                    questionnaireTemplateService.selectByPrimaryKey(s.getQuesTplId());
            s.setQuestionnaireTemplate(questionnaireTemplate);
        }
        jsonObject.put(Const.HEALTHY_MESSAGES, healthyMessages);
        jsonObject.put(Const.MESSAGE, "查询用户健康问卷消息成功！");
        return jsonObject;
    }

    @UserLoginToken
    @PostMapping("/answer")
    public Object quesOption(@RequestBody RequestQuesOptionData requestQuesOptionData){
        JSONObject jsonObject = new JSONObject();
        //System.out.println(requestQuesOptionData.getHealthyMessageId());
        //System.out.println(requestQuesOptionData.getAnswers());
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("id", requestQuesOptionData.getHealthyMessageId());
        //将Json数组转换为字符串
        paramMap.put("quesOption", JSON.toJSONString(requestQuesOptionData.getAnswers()));
        //获得系统的时间，单位为毫秒,转换为妙
        Integer updateTime = (int) (System.currentTimeMillis() / 1000);
        paramMap.put("atUpdate", updateTime);
        int count = healthyMessageService.updateQuesOption(paramMap);
        if(count == 1){
            jsonObject.put(Const.MESSAGE, "提交问卷成功！");
            return jsonObject;
        }else{
            jsonObject.put(Const.MESSAGE, "提交问卷失败！");
            return jsonObject;
        }

    }
}