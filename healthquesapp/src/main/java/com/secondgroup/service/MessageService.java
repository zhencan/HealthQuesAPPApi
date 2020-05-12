/**
 * FileName: MessageService
 * Author:   ZSX
 * Date:     2020/4/16 13:38
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.secondgroup.service;

import com.secondgroup.mapper.MessageMapper;
import com.secondgroup.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ZSX
 * @create 2020/4/16
 * @since 1.0.0
 */
@Service
public class MessageService {

    @Autowired
    MessageMapper messageMapper;

    public int selectOccupyMessage(Integer userId, Integer isOccupy) {
        return messageMapper.selectOccupyMessage(userId, isOccupy);
    }

    public List<Message> selectAllMessage(Integer userId) {
        return messageMapper.selectAllMessage(userId);
    }

    public Message selectMessage(Integer id) {
        return messageMapper.selectByPrimaryKey(id);
    }

    public int updateOccupyMessage(Integer id, Integer atUpdate) {
        return messageMapper.updateOccupyMessage(id, atUpdate);
    }
}