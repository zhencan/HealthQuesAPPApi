/**
 * FileName: SendSmsUtil
 * Author:   ZSX
 * Date:     2020/4/12 10:24
 * Description: 短信发送工具
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.secondgroup.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈短信发送工具〉
 *
 * @author ZSX
 * @create 2020/4/12
 * @since 1.0.0
 */
@Service
public class SendSmsUtil {

    @Value("${alisms.accessKeyId}")
    private String accessKeyId;
    @Value("${alisms.accessSecret}")
    private String accessSecret;
    @Value("${alisms.signName}")
    private String signName;
    @Value("${alisms.templateCode}")
    private String templateCode;


    public String aliSendSms(String PhoneNumbers,String TemplateParam){
        String result = "";

        // 设置鉴权参数，初始化客户端codeTemplate
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        //组装请求对象
        CommonRequest request = new CommonRequest();
        //使用post提交
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        //必填:待发送手机号。
        request.putQueryParameter("PhoneNumbers", PhoneNumbers);
        //必填:短信签名-可在短信控制台中找到
        request.putQueryParameter("SignName", signName);
        //必填:短信模板-可在短信控制台中找到
        request.putQueryParameter("TemplateCode", templateCode);
        // 短信模板变量对应的实际值，JSON格式。
        //request.putQueryParameter("TemplateParam", "{\"code\":\"8888\"}");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.putQueryParameter("TemplateParam", TemplateParam);

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //request.setOutId("yourOutId");

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            String infojson = response.getData();
            Gson gson = new Gson();
            Map map = gson.fromJson(infojson, Map.class);
            //Object bizId = map.get("BizId");
            //System.out.println("BizId=" + bizId.toString());
            String message = map.get("Message").toString();
            //System.out.println("Message=" + message);
            if(message.equals("OK")){
                result="ok";
            }else {
                result="not_ok";
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        //System.out.println("result="+result);
        return result;
    }

}
