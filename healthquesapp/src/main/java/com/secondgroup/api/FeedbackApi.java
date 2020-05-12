/**
 * FileName: FeedbackApi
 * Author:   ZSX
 * Date:     2020/4/13 16:37
 * Description: 消息反馈
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.secondgroup.api;

import com.alibaba.fastjson.JSONObject;
import com.secondgroup.annotation.UserLoginToken;
import com.secondgroup.pojo.Feedback;
import com.secondgroup.service.FeedbackService;
import com.secondgroup.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈消息反馈〉
 *
 * @author ZSX
 * @create 2020/4/13
 * @since 1.0.0
 */
@RestController
@RequestMapping("api")
public class FeedbackApi {

    @Autowired
    FeedbackService feedbackService;

    //创建用户消息反馈
    @UserLoginToken
    @PostMapping("/feedback/create")
    public Object createFeedback(@RequestBody Feedback feedback) {
        JSONObject jsonObject = new JSONObject();
        //获得系统的时间，单位为毫秒,转换为妙
        Integer createTime = (int) (System.currentTimeMillis() / 1000);

        feedback.setAtCreate(createTime);
        // 不允许为空
        feedback.setAtUpdate(createTime);

        int count = feedbackService.createFeedback(feedback);
        if(count == 1){
            jsonObject.put(Const.MESSAGE, "消息反馈成功！");
            jsonObject.put(Const.FEEDBACKS, feedback);
            return jsonObject;
        }else{
            jsonObject.put(Const.MESSAGE, "消息反馈失败！");
            return jsonObject;
        }
    }


    //修改用户消息反馈
    @UserLoginToken
    @PostMapping("/feedback/update")
    public Object updateFeedback(@RequestBody Feedback feedback) {
        JSONObject jsonObject = new JSONObject();
        //获得系统的时间，单位为毫秒,转换为妙
        Integer updateTime = (int) (System.currentTimeMillis() / 1000);
        feedback.setAtUpdate(updateTime);
        int count = feedbackService.updateFeedback(feedback);
        if(count == 1){
            jsonObject.put(Const.MESSAGE, "修改消息反馈成功！");
            feedback.setAtUpdate(updateTime);
            jsonObject.put(Const.FEEDBACKS, feedback);
            return jsonObject;
        }else{
            jsonObject.put(Const.MESSAGE, "修改消息反馈失败！");
            return jsonObject;
        }
    }

    //查询用户所有消息反馈
    @UserLoginToken
    @PostMapping("/feedback/selectAll")
    public Object selectAllFeedback(@RequestBody Feedback feedback) {
        JSONObject jsonObject = new JSONObject();

        List<Feedback> feedbacks = feedbackService.selectAllFeedback(feedback.getUserId());
        if(feedbacks != null){
            jsonObject.put(Const.MESSAGE, "查询反馈消息成功！");
            jsonObject.put(Const.FEEDBACKS, feedbacks);
            return jsonObject;
        }else{
            jsonObject.put(Const.MESSAGE, "没有查询到反馈消息！");
            return jsonObject;
        }
    }

}