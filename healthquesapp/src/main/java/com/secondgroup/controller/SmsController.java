/**
 * FileName: SmsController
 * Author:   ZSX
 * Date:     2020/4/12 10:49
 * Description: 短信
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.secondgroup.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.secondgroup.annotation.UserLoginToken;
import com.secondgroup.pojo.User;
import com.secondgroup.util.Const;
import com.secondgroup.util.SendSmsUtil;
import com.secondgroup.util.VerificationCodeUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈短信〉
 *
 * @author ZSX
 * @create 2020/4/12
 * @since 1.0.0
 */
@Controller
public class SmsController {


    @Autowired
    private SendSmsUtil sendSmsUtil;

    @Data
    public static class SmsData{
        String code;    //短信验证码

        public SmsData(String code) {
            this.code = code;
        }
    }

    /**
     * 跳转到短信测试页面
     * @return
     */
//    @RequestMapping(value = "/toSms",method = RequestMethod.GET)
//    public String toSms(){
//        return "sms";
//    }

    //处理前端传来的json串。"contentType": "application/json;charset=utf-8;",
//    @ResponseBody
//    @PostMapping("/api/smsTest")
//    public Object sendSms(
//            @RequestBody Map<String,String> params,
//            //@RequestParam("phoneNumbers") String phoneNumbers,
//            HttpServletRequest request) {
//        System.out.println(JSON.toJSONString(params));
//        JSONObject object = JSONObject
//                .parseObject(JSON.toJSONString(params));
//        System.out.println(object.get("phoneNumbers"));
//        return object;
//    }


     //发送验证码
    @UserLoginToken
    @ResponseBody
    @PostMapping("/api/sms")
    public Object sendSms(
            @RequestBody Map<String,String> params,
            //@RequestParam("phoneNumbers") String phoneNumbers,
            HttpServletRequest request){
        JSONObject paramsObject = JSONObject.parseObject(JSON.toJSONString(params));
        String phoneNumbers = paramsObject.getString("phoneNumbers");

        JSONObject jsonObject = new JSONObject();
        HttpSession session = request.getSession();
        //生成6位随机验证码
        String numeral = VerificationCodeUtil.getNumeral();
        SmsData smsData = new SmsData(numeral);
        //序列化
        Gson gson = new Gson();
        String code = gson.toJson(smsData);
        //System.out.println(code);
        //给手机号发送验证码
        String information = sendSmsUtil.aliSendSms(phoneNumbers, code);
        if(information.equals("ok")){
            session.setAttribute(phoneNumbers,numeral);
            //设置有效期为3分钟
            session.setMaxInactiveInterval(3 * 60);
            jsonObject.put(Const.MESSAGE, "验证码已发送");
            return jsonObject;
        }else {
            jsonObject.put(Const.MESSAGE, "验证码发送失败");
            return jsonObject;
        }
    }


    /**
     * 验证
     * @param phoneNumbers
     * @param code
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/yanSms",method = RequestMethod.POST)
    public String yanSms(String phoneNumbers, String code,HttpServletRequest request){
        HttpSession session = request.getSession();
        String ycode = (String) session.getAttribute(phoneNumbers);
        if(ycode.equals(code)){
            return phoneNumbers+"验证成功";
        }
        return phoneNumbers+"验证失败";

    }

}