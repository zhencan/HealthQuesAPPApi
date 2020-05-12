/**
 * FileName: UserServer
 * Author:   ZSX
 * Date:     2020/4/7 17:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.secondgroup.service;

import com.secondgroup.mapper.UserMapper;
import com.secondgroup.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ZSX
 * @create 2020/4/7
 * @since 1.0.0
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User findUserById(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public User queryUserLogin(Map<String, Object> paramMap) {

        User user = userMapper.queryUserLogin(paramMap);

//        if(user == null) {
//            //可自定义异常
//            throw new LoginFailException("用户账号或密码不正确！");
//        }

        return user;
    }

    public List<User> findByUsername(String username) {
        List<User> users = userMapper.findByUsername(username);
        return users;
    }

    public int updateImageUrl(Map<String, Object> paramMap) {
        return userMapper.updateImageUrl(paramMap);
    }

    public int updatePassword(Map<String, Object> paramMap) {
        return userMapper.updatePassword(paramMap);
    }


    public int updateGeneralInfo(Map<String, Object> paramMap) {
        return userMapper.updateGeneralInfo(paramMap);
    }
}