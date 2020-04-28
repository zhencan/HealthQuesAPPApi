/**
 * FileName: TokenService
 * Author:   ZSX
 * Date:     2020/4/7 15:00
 * Description: 封装token
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.secondgroup.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.secondgroup.pojo.User;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br>
 * 〈封装token〉
 *
 * @author ZSX
 * @create 2020/4/7
 * @since 1.0.0
 */

@Service("TokenService")
public class TokenService {
    public String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(user.getId().toString())// 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
        return token;
    }
}

