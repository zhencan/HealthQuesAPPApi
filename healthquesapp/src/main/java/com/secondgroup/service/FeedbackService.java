/**
 * FileName: FeedbackService
 * Author:   ZSX
 * Date:     2020/4/13 17:03
 * Description: 消息反馈
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.secondgroup.service;

import com.secondgroup.mapper.FeedbackMapper;
import com.secondgroup.pojo.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 〈一句话功能简述〉<br> 
 * 〈消息反馈〉
 *
 * @author ZSX
 * @create 2020/4/13
 * @since 1.0.0
 */
@Service
public class FeedbackService {

    @Autowired
    FeedbackMapper feedbackMapper;

    public int createFeedback(Feedback feedback) {
        return feedbackMapper.insert(feedback);
    }

    public int updateFeedback(Feedback feedback) {
        return feedbackMapper.updateByIdAndUserId(feedback);
    }

    public List<Feedback> selectAllFeedback(Integer userId) {
        return feedbackMapper.selectAllByUserId(userId);
    }
}