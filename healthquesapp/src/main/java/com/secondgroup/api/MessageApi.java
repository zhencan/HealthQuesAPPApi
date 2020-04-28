/**
 * FileName: MessageApi
 * Author:   ZSX
 * Date:     2020/4/16 13:28
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.secondgroup.api;

import com.alibaba.fastjson.JSONObject;
import com.secondgroup.annotation.UserLoginToken;
import com.secondgroup.pojo.Message;
import com.secondgroup.pojo.User;
import com.secondgroup.service.MessageService;
import com.secondgroup.util.Const;
import lombok.extern.slf4j.Slf4j;
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
 * @create 2020/4/16
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/api/user/message")
public class MessageApi {

    @Autowired
    MessageService messageService;

    //查询用户未读消息数量
    @UserLoginToken
    @PostMapping("/occupy")
    public Object selectOccupyMessage(@RequestBody Message message) {
        JSONObject jsonObject = new JSONObject();
        log.info("userId："+message.getUserId().toString());
        // isOccupy==1,查询未读
        int count = messageService.selectOccupyMessage(message.getUserId(), 1);
        if (count == 0){
            jsonObject.put(Const.MESSAGE,"查询未读消息失败");
            return jsonObject;
        }else {
            jsonObject.put(Const.USER_OCCUPY_MESSAGES_COUNT,count);
            jsonObject.put(Const.MESSAGE,"查询未读消息成功");
            return jsonObject;
        }
    }

    //查询用户全部消息(查询结果将未读的放在前面，并按时间降序)
    @UserLoginToken
    @PostMapping("/all")
    public Object selectAllMessage(@RequestBody Message message) {
        JSONObject jsonObject = new JSONObject();
        // isOccupy==1,查询未读
        List<Message> messages = messageService.selectAllMessage(message.getUserId());
        if (messages.isEmpty()){
            jsonObject.put(Const.MESSAGE,"查询用户全部消息失败");
            return jsonObject;
        }else {
            jsonObject.put(Const.USER_MESSAGES,messages);
            jsonObject.put(Const.MESSAGE,"查询用户全部消息成功");
            return jsonObject;
        }
    }

    //查看消息，若未读则更新为已读
    @UserLoginToken
    @PostMapping("/read")
    public Object selectMessage(@RequestBody Message message) {
        JSONObject jsonObject = new JSONObject();
        log.info("messageId："+message.getId().toString());
        Message message1 = messageService.selectMessage(message.getId());
        if (message1 != null){
            jsonObject.put(Const.MESSAGE,"查询消息成功");
            //获得系统的时间，单位为毫秒,转换为妙
            Integer atUpdate = (int) (System.currentTimeMillis() / 1000);
            //更新未读消息
            if (message1.getIsOccupy()==1){
                int count = messageService.updateOccupyMessage(message1.getId(), atUpdate);
                if (count == 0){
                    message1.setIsOccupy(0);
                    jsonObject.put(Const.MESSAGE,"更新为已读失败");
                }else {
                    jsonObject.put(Const.MESSAGE,"更新为已读成功");
                }
            }
            jsonObject.put(Const.USER_MESSAGES,message1);

        }else {
            jsonObject.put(Const.MESSAGE,"查询消息失败");
        }

        return jsonObject;
    }

}