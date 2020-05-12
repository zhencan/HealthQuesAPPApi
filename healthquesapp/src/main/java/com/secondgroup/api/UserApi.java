/**
 * FileName: UserApi
 * Author:   ZSX
 * Date:     2020/4/7 22:48
 * Description: 用户api接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.secondgroup.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.secondgroup.annotation.UserLoginToken;
import com.secondgroup.pojo.School;
import com.secondgroup.pojo.User;
import com.secondgroup.service.SchoolService;
import com.secondgroup.service.TokenService;
import com.secondgroup.service.UserService;
import com.secondgroup.util.Const;
import com.secondgroup.util.ImageUtil;
import com.secondgroup.util.MD5Util;
import com.secondgroup.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户api接口〉
 *
 * @author ZSX
 * @create 2020/4/7
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/user")
public class UserApi {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    @Autowired
    SchoolService schoolService;

    /**
     * 用户登录+JWT
     * @param user  localhost:8080/api/login/{"username":"张三","password":"123"}
     * @return
     */
    @PostMapping("/login")
    public Object login(
            @RequestBody User user,
            HttpSession session){
        JSONObject jsonObject = new JSONObject();
        List<User> userForBase = userService.findByUsername(user.getUsername());
        if(userForBase == null){
            jsonObject.put(Const.MESSAGE, "登录失败,用户不存在");
            return jsonObject;
        }else {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("username", user.getUsername());
            //密码MD5加密
            paramMap.put("password", MD5Util.digest(user.getPassword()));
            User loginUser = userService.queryUserLogin(paramMap);
            if (loginUser == null){
                jsonObject.put(Const.MESSAGE, "登录失败,密码错误");
                return jsonObject;
            }else {
                //头像为空设置默认头像
                if (StringUtil.isEmpty(loginUser.getImageUrl())){
                    loginUser.setImageUrl("/image/user/default.jpg");
                }

                //将登录用户存入session
                session.setAttribute(Const.LOGIN_USER,loginUser);
                //JWT
                String token = tokenService.getToken(loginUser);
                jsonObject.put(Const.TOKEN, token);
                jsonObject.put(Const.LOGIN_USER, loginUser);
                return jsonObject;
            }
        }
    }

    //JWT验证测试
//    @UserLoginToken
//    @GetMapping("/getMessage")
//    public String getMessage(){
//        return "你已通过验证";
//    }

    //用户登出
    @GetMapping("/logout")
    @ResponseBody
    public Object logout(HttpSession session){
        session.invalidate();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Const.MESSAGE,"用户登出成功");
        return jsonObject;
    }

    //查找用户学校名
    @UserLoginToken
    @PostMapping("/school")
    @ResponseBody
    public Object school(@RequestBody User user){
        JSONObject jsonObject = new JSONObject();
        School school = schoolService.getSchoolById(user.getSchoolId());
        if(school != null){
            jsonObject.put(Const.MESSAGE,"查询学校成功！");
            jsonObject.put(Const.USER_SCHOOL, school);
            return jsonObject;
        }
        jsonObject.put(Const.MESSAGE,"查询学校失败！");
        return jsonObject;
    }

    //头像修改
    @UserLoginToken
    @PostMapping("/image/update")
    @ResponseBody
    public Object uploadImage(
            @RequestParam(value = "file") MultipartFile file,
            HttpServletRequest request) throws IOException {
        JSONObject jsonObject = new JSONObject();
        if (file.isEmpty()) {
            jsonObject.put(Const.MESSAGE, "请上传图片!");
            return jsonObject;
        }
        //获取文件名
        String fileName = file.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //String contentType = file.getContentType();   //image/jpeg
        
        //判断是否为图片类型
        if(!ImageUtil.isImage(file)){
            jsonObject.put(Const.MESSAGE, "请上传图片!");
            return jsonObject;
        }

        //重新生成文件名：防止图片名字相同而出现bug，使用 uuid 加密
        //28fb1f4c-6498-406e-a185-956811c49e0f.jpg
        fileName = UUID.randomUUID()+suffixName;

        //获取classes目录绝对路径
        String pathStart = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static";
        //设置绝对路径下的路径
        String pathEnd = "/image/user/";
        //指定文件上传的地址存储图片
        String path = pathStart + pathEnd;
        String fileDir = path.replace('/', '\\').substring(1,path.length());

        try {
            System.out.println("------->>"+fileDir);
            File dir = new File(fileDir);
            // 判断路径是否存在，如果不存在就创建一个
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File upload_file = new File(fileDir + fileName);

            //写入文件
            file.transferTo(upload_file);

            //把图片路径,图片名写入数据库
            //获取登录用户
            User loginUser = (User) request.getSession().getAttribute(Const.LOGIN_USER);
            if (loginUser == null){
                jsonObject.put(Const.MESSAGE, "请登录！");
                return jsonObject;
            }else{
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("id", loginUser.getId());
                paramMap.put("imageUrl", pathEnd+fileName);
                //获得系统的时间，单位为毫秒,转换为妙
                Integer updateTime = (int) (System.currentTimeMillis() / 1000);
                paramMap.put("atUpdate", updateTime);
                int count = userService.updateImageUrl(paramMap);
                if(count == 1){

                    jsonObject.put(Const.MESSAGE, "头像修改成功");
                    //更新用户头像路径
                    loginUser.setImageUrl(pathEnd+fileName);
                    jsonObject.put(Const.LOGIN_USER, loginUser);
                    return jsonObject;
                }else{
                    jsonObject.put(Const.MESSAGE, "头像修改失败");
                    return jsonObject;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put(Const.MESSAGE, "修改失败");
            return jsonObject;
        }
    }


    //验证验证码后修改用户密码
    @UserLoginToken
    @ResponseBody
    @RequestMapping(value = "/password/update",method = RequestMethod.POST)
    public Object updatePassword(
            @RequestBody Map<String,String> params,
            HttpServletRequest request){
        //参数处理
        JSONObject paramsObject = JSONObject.parseObject(JSON.toJSONString(params));
        String phoneNumbers = paramsObject.getString("phoneNumbers");
        String code = paramsObject.getString("code");
        Integer id = paramsObject.getInteger("id");
        String password = paramsObject.getString("password");

        JSONObject jsonObject = new JSONObject();
        HttpSession session = request.getSession();
        String ycode = (String) session.getAttribute(phoneNumbers);
        if(ycode.equals(code)){ //验证成功
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("id", id);
            paramMap.put("password", MD5Util.digest(password));
            //获得系统的时间，单位为毫秒,转换为妙
            Integer updateTime = (int) (System.currentTimeMillis() / 1000);
            paramMap.put("atUpdate", updateTime);
            int count = userService.updatePassword(paramMap);
            if(count == 1){
                jsonObject.put(Const.MESSAGE, "密码修改成功！");
                return jsonObject;
            }else{
                jsonObject.put(Const.MESSAGE, "密码修改失败");
                return jsonObject;
            }

        }else{  //验证失败
            jsonObject.put(Const.MESSAGE, "请输入正确的验证码！");
            return jsonObject;
        }
    }

    @UserLoginToken
    @PostMapping("/info/update")
    @ResponseBody
    public Object updateGeneralInfo(
            @RequestBody User user,
            HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", user.getId());
        paramMap.put("username", user.getUsername());
        paramMap.put("phone", user.getPhone());
        //获得系统的时间，单位为毫秒,转换为妙
        Integer updateTime = (int) (System.currentTimeMillis() / 1000);
        paramMap.put("atUpdate", updateTime);
        int count = userService.updateGeneralInfo(paramMap);
        if(count == 1){
            user.setAtUpdate(updateTime);
            jsonObject.put(Const.MESSAGE, "修改个人信息成功！");
            jsonObject.put(Const.LOGIN_USER, user);
            return jsonObject;
        }else{
            jsonObject.put(Const.MESSAGE, "修改个人信息失败！");
            return jsonObject;
        }
    }
}