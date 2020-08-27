package com.informanage.user.controller;


import com.baidu.aip.face.AipFace;
import com.informanage.common.*;
import com.informanage.user.entity.User;
import com.informanage.user.entity.VO.RegistUserInfo;
import com.informanage.user.service.UserService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LQ
 * @since 2020-07-31
 */
@RestController
@RequestMapping("/userservice/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService service;

    /**
     * 管理员登录后台管理系统
     *
     * @return
     */
    @PostMapping("/adminLogin")
    public R adminLogin() {
        return R.ok().data("token", "admin");
    }

    @GetMapping("/adminGetInfo")
    public R adminGetInfo() {

        return R.ok().data("roles", "[admin]").data("name", "LQ").data("avatar", "https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3177406708,533494095&fm=26&gp=0.jpg");
    }

    @PostMapping("/changepass")
    public R changepass(@RequestBody RegistUserInfo registUserInfo,HttpServletRequest request) {
        JwtInfo id = JwtUtils.getMemberIdByJwtToken(request);
        User byId = service.getById(id.getId());
        if(byId.getPassword()==null||byId.getPassword().equals("")){
            byId.setPassword(MD5.encrypt(registUserInfo.getPassword()));
            service.updateById(byId);
            return R.ok();
        }
        //校验密码是否正确
        if(!MD5.encrypt(registUserInfo.getCode()).equals(byId.getPassword())){
          return R.error().message("原密码错误");
        }
        byId.setPassword(MD5.encrypt(registUserInfo.getPassword()));
        service.updateById(byId);
        return R.ok();
    }

    @PostMapping("/changepassByPhone")
    public R changepassByPhone(@RequestBody RegistUserInfo registUserInfo) {
        service.changepassByPhone(registUserInfo);
        return R.ok();
    }


    @PostMapping("/changepassByEmail")
    public R changepassByEmail(@RequestBody RegistUserInfo registUserInfo) {
        service.changepassByEmail(registUserInfo);
        return R.ok();
    }
    @PostMapping("/registByPhone")
    public R registByPhone(@RequestBody RegistUserInfo registUserInfo) {
        service.insertByPhone(registUserInfo);
        return R.ok();
    }

    @PostMapping("/registByEmail")
    public R registByEmail(@RequestBody RegistUserInfo registUserInfo) {
        service.insertByEmail(registUserInfo);
        return R.ok();
    }

    @GetMapping("/isLock")
    public R isLock(HttpServletRequest request) {
        JwtInfo id = JwtUtils.getMemberIdByJwtToken(request);
        User user = service.getById(id.getId());
        return R.ok().data("user", user);
    }

    @PostMapping("/lockByEmail")
    public R lockByEmail(HttpServletRequest request, @RequestBody RegistUserInfo registUserInfo) {
        JwtInfo memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        service.lockByEmail(registUserInfo, memberIdByJwtToken.getId());
        return R.ok().message("绑定邮箱成功！");
    }

    @PostMapping("/lockByPhone")
    public R lockByPhone(HttpServletRequest request, @RequestBody RegistUserInfo registUserInfo) {
        JwtInfo memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        service.lockByPhone(registUserInfo, memberIdByJwtToken.getId());
        return R.ok().message("绑定手机号成功！");
    }


    @PostMapping("/registByFace")
    public R registByFace(@RequestParam("file") MultipartFile file) {
        String image = "";
        try {
            image = new BASE64Encoder().encode(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imageType = "BASE64";
        String groupIdList = "huadi";
        // 人脸检测
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("face_field", "quality");
        options.put("max_face_num", "1");
        options.put("face_type", "LIVE");
        options.put("liveness_control", "LOW");
        // 准备创建请求
        AipFace client = new AipFace("19848270", "n1A9vKygYAz0pCT34DNOalAf", "1zB8o1wB9UXoBe6fML7NQDGyjOc8ZmzW");
        JSONObject res = client.detect(image, imageType, options);
        if (res.get("error_msg").toString().equals("SUCCESS")) {
            System.out.println("识别成功");
            String checkFace = service.checkFace(res);//检查图片质量
            if (checkFace.length() > 3) {
                return R.error().message("图片质量存在问题，存在的问题有：" + checkFace);
            }
            // 查看是否以及注册过该脸避免多次注册登录时出现用户部匹配问题
            JSONObject res2 = client.search(image, imageType, groupIdList, options);
            String checkIsRegist = service.checkIsRegist(res2);
            if (checkIsRegist != null && !(checkIsRegist.equals("three"))) {
                return R.error().message("您已经注册过了，请勿重复注册");
            }
            // 图片没有问题，可以存入数据库
            String uuid = UUID.randomUUID().toString();
            uuid = uuid.replaceAll("-", "_");
            service.registFace(image, imageType, "huadi", uuid, new HashMap<String, String>(),
                    client, file);
            return R.ok().message("注册成功，自动跳转到登录页面");
        }
        return R.error().message("图片识别失败！");
    }

    @PostMapping("/phoneLogin")
    public R phoneLogin(@RequestBody RegistUserInfo registUserInfo) {
        String token = service.phoneLogin(registUserInfo);
        return R.ok().data("token", token).message("登录成功");
    }

    @PostMapping("/emailLogin")
    public R emailLogin(@RequestBody RegistUserInfo registUserInfo) {
        String token = service.emailLogin(registUserInfo);
        return R.ok().data("token", token).message("登录成功");
    }

    @PostMapping("/faceLogin")
    public R faceLogin(@RequestParam("file") MultipartFile file) {
        String image = "";
        try {
            image = new BASE64Encoder().encode(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imageType = "BASE64";
        String groupIdList = "huadi";
        // 人脸检测
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("max_face_num", "1");
        options.put("match_threshold", "70");
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        options.put("max_user_num", "1");
        // 准备创建请求
        AipFace client = new AipFace("19848270", "n1A9vKygYAz0pCT34DNOalAf", "1zB8o1wB9UXoBe6fML7NQDGyjOc8ZmzW");
        JSONObject res = client.search(image, imageType, groupIdList, options);
        if (res.get("error_msg").toString().equals("SUCCESS")) {
            System.out.println("找到相似人脸");
            String result = service.checkIsRegist(res);
            if (result != null) {
                // 查询到人说明数据库有这个人
                String token = service.selectByUserId(result);
                return R.ok().data("token", token).message("登录成功");
            } else {
                return R.error().message("相似度不足，登录失败！");
            }
        }
        return R.error().message("未找到相似人脸");

    }

    @GetMapping("/get-login-info")
    public R getLoginInfo(HttpServletRequest request) {

        try {
            JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
            return R.ok().data("userInfo", jwtInfo);
        } catch (Exception e) {
            throw new MyException(ResultCodeEnum.FETCH_USERINFO_ERROR);
        }
    }

    @PostMapping("/changeAvator")
    public R changeAvator(@RequestBody JwtInfo jwtInfo) {
        service.changeAvator(jwtInfo);
        String jwtToken = JwtUtils.getJwtToken(jwtInfo);
        return R.ok().data("token", jwtToken).message("修改头像成功");
    }
}

