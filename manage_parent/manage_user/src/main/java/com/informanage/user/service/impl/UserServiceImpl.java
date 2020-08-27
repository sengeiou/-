package com.informanage.user.service.impl;

import com.baidu.aip.face.AipFace;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.informanage.common.*;
import com.informanage.user.entity.User;
import com.informanage.user.entity.VO.RegistUserInfo;
import com.informanage.user.feignlient.OssClient;
import com.informanage.user.mapper.UserMapper;
import com.informanage.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LQ
 * @since 2020-07-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper usermapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private OssClient ossclient;

    @Override
    public void insertByPhone(RegistUserInfo registUserInfo) {
        String nickname = registUserInfo.getNickname();
        String mobile = registUserInfo.getNumber();
        String password = registUserInfo.getPassword();
        String code = registUserInfo.getCode();

        if (StringUtils.isEmpty(mobile) || !FormUtils.isMobile(mobile)) {
            throw new MyException(ResultCodeEnum.LOGIN_PHONE_ERROR);
        }

        if (StringUtils.isEmpty(nickname) || StringUtils.isEmpty(password) || StringUtils.isEmpty(code)) {
            throw new MyException(ResultCodeEnum.PARAM_ERROR);
        }
        // 校验验证码：redis
        String checkCode = (String) redisTemplate.opsForValue().get(mobile);
        if (!code.equals(checkCode)) {
            throw new MyException(ResultCodeEnum.CODE_ERROR);
        }
        // 用户是否注册：mysql
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("number", mobile);
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new MyException(ResultCodeEnum.REGISTER_MOBLE_ERROR);
        }

        User user = new User();
        BeanUtils.copyProperties(registUserInfo,user);
        user.setPassword(MD5.encrypt(password));//密码编码插入数据库
        usermapper.insert(user);
    }

    @Override
    public void insertByEmail(RegistUserInfo registUserInfo) {
        String nickname = registUserInfo.getNickname();
        String email = registUserInfo.getEmail();
        String password = registUserInfo.getPassword();
        String code = registUserInfo.getCode();

        if (StringUtils.isEmpty(email) || !FormUtils.isEmail(email)) {
            System.out.println("错误的邮箱格式！"+email);
            throw new MyException(ResultCodeEnum.LOGIN_Email_ERROR);
        }

        if (StringUtils.isEmpty(nickname) || StringUtils.isEmpty(password) || StringUtils.isEmpty(code)) {
            throw new MyException(ResultCodeEnum.PARAM_ERROR);
        }
        // 校验验证码：redis
        String checkCode = (String) redisTemplate.opsForValue().get(email);
        if (!code.equals(checkCode)) {
            throw new MyException(ResultCodeEnum.CODE_ERROR);
        }
        // 用户是否注册：mysql
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new MyException(ResultCodeEnum.REGISTER_EMAIL_ERROR);
        }

        User user = new User();
        BeanUtils.copyProperties(registUserInfo,user);
        user.setPassword(MD5.encrypt(password));//密码编码插入数据库
        usermapper.insert(user);
    }

    @Override
    public void registFace(String image, String imageType, String huadi, String uuid, HashMap<String, String> options, AipFace client, MultipartFile file){
            // 人脸注册
            JSONObject res = client.addUser(image, imageType, huadi, uuid, options);
            if (res.get("error_msg").toString().equals("SUCCESS")) {
                // API注册成功
                JSONObject result2 = (JSONObject) res.get("result");
                String result3 = (String) result2.get("face_token");
                User user = new User();
                //这里调用oss微服务存储头像返回地址
                R upload = ossclient.upload(file, "huadi-test");
                if(upload.getSuccess()==true) {
                    String url = (String) upload.getData().get("url");
                    user.setAvatar(url);//调用成功才设置头像，否则用默认头像
                }
                user.setFaceToken(result3).setUserId(uuid).setNickname("新用户-"+ UUID.randomUUID());
                baseMapper.insert(user);
            }else{
                throw new MyException("调用百度API接口注册人脸失败",30000);
            }
        }

        @Override
        public String checkIsRegist(JSONObject result) {
            JSONObject result2=null;
            try {
                result2 = (JSONObject) result.get("result");
            } catch (Exception e) {
                // 这里要判断是否为null，第一次第一个人注册必定为null
                if (result2 == null)
                    return "three";
            }
            JSONArray jsonArray = result2.getJSONArray("user_list");
            JSONObject object = (JSONObject) jsonArray.get(0);
            Double score = object.getDouble("score");
            String user_id = (String) object.get("user_id");
            System.out.println("匹配度为：" + score + ",用户ID为：" + user_id);
            if (score >= 90) {
                return user_id;
            }
            return null;
        }

        @Override
        public String checkFace(JSONObject result) {
            JSONObject result2 = (JSONObject) result.get("result");
            JSONArray jsonArray = result2.getJSONArray("face_list");
            JSONObject object = (JSONObject) jsonArray.get(0);
            JSONObject object2 = (JSONObject) object.get("quality");
            JSONObject object3 = (JSONObject) object2.get("occlusion");
            StringBuffer message = new StringBuffer();
            Double left_eye = object3.getDouble("left_eye");
            if (left_eye >= 0.6)
                message.append("左眼被遮挡");
            Double right_eye = object3.getDouble("right_eye");
            if (right_eye >= 0.6)
                message.append("右眼被遮挡");
            Double nose = object3.getDouble("nose");
            if (nose >= 0.7)
                message.append("鼻子被遮挡");
            Double mouth = object3.getDouble("mouth");
            if (mouth >= 0.7)
                message.append("嘴巴被遮挡");
            Double left_check = object3.getDouble("left_cheek");
            if (left_check >= 0.8)
                message.append("左脸颊被遮挡");
            Double right_check = object3.getDouble("right_cheek");
            if (right_check >= 0.8)
                message.append("右脸颊遮挡");
            Double chin_contour = object3.getDouble("chin_contour");
            if (chin_contour >= 0.6)
                message.append("下巴被遮挡");
            Double blur = object2.getDouble("blur");
            if (blur >= 0.7)
                message.append("照片太模糊");
            Double illumination = object2.getDouble("illumination");
            if (illumination <= 40)
                message.append("灯光灰暗");
            Double completeness = object2.getDouble("completeness");
            if (completeness < 1)
                message.append("人脸未全部在照片框内");
            return message.toString();
        }

    @Override
    public User getByOpenid(String openid) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", openid);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public String selectByUserId(String user_id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user_id);
        User member = baseMapper.selectOne(queryWrapper);
        if (member == null) {
           throw new MyException("数据库中不存在此用户，请去注册",9999);
        }
        //登录：生成token
        JwtInfo info = new JwtInfo();
        info.setId(member.getId());
        info.setNickname(member.getNickname());
        info.setAvatar(member.getAvatar());

        String jwtToken = JwtUtils.getJwtToken(info);
        return jwtToken;
    }

    @Override
    public String emailLogin(RegistUserInfo loginVo) {
        String email = loginVo.getEmail();
        String password = loginVo.getPassword();

        //校验：参数是否合法
        if(StringUtils.isEmpty(email)
                || !FormUtils.isEmail(email)
                || StringUtils.isEmpty(password)){
            throw new MyException(ResultCodeEnum.PARAM_ERROR);
        }

        //校验邮箱是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        User member = baseMapper.selectOne(queryWrapper);
        if(member == null){
            throw new MyException(ResultCodeEnum.LOGIN_MOBILE_ERROR);
        }
        //校验密码是否正确
        if(!MD5.encrypt(password).equals(member.getPassword())){
            throw new MyException(ResultCodeEnum.LOGIN_PASSWORD_ERROR);
        }
        //登录：生成token
        JwtInfo info = new JwtInfo();
        info.setId(member.getId());
        info.setNickname(member.getNickname());
        info.setAvatar(member.getAvatar());

        String jwtToken = JwtUtils.getJwtToken(info);
        return jwtToken;
    }

    @Override
    public String phoneLogin(RegistUserInfo loginVo) {
        String mobile = loginVo.getNumber();
        String password = loginVo.getPassword();

        //校验：参数是否合法
        if(StringUtils.isEmpty(mobile)
                || !FormUtils.isMobile(mobile)
                || StringUtils.isEmpty(password)){
            throw new MyException(ResultCodeEnum.PARAM_ERROR);
        }

        //校验手机号是否存在
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("number", mobile);
        User member = baseMapper.selectOne(queryWrapper);
        if(member == null){
            throw new MyException(ResultCodeEnum.LOGIN_MOBILE_ERROR);
    }

        //校验密码是否正确
        if(!MD5.encrypt(password).equals(member.getPassword())){
            throw new MyException(ResultCodeEnum.LOGIN_PASSWORD_ERROR);
        }

        //登录：生成token
        JwtInfo info = new JwtInfo();
        info.setId(member.getId());
        info.setNickname(member.getNickname());
        info.setAvatar(member.getAvatar());

        String jwtToken = JwtUtils.getJwtToken(info);
        return jwtToken;
    }

    @Override
    public void changeAvator(JwtInfo jwtInfo) {
            User user=new User();
            user.setId(jwtInfo.getId());
            user.setAvatar(jwtInfo.getAvatar());
            baseMapper.updateById(user);
    }

    @Override
    public void lockByEmail(RegistUserInfo registUserInfo,String id) {
        String email = registUserInfo.getEmail();
        String code = registUserInfo.getCode();
        if (StringUtils.isEmpty(email) || !FormUtils.isEmail(email)) {
            throw new MyException(ResultCodeEnum.LOGIN_Email_ERROR);
        }
        // 校验验证码：redis
        String checkCode = (String) redisTemplate.opsForValue().get(email);
        if (!code.equals(checkCode)) {
            throw new MyException(ResultCodeEnum.CODE_ERROR);
        }
        // 用户是否注册：mysql
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new MyException(ResultCodeEnum.REGISTER_MOBLE_ERROR);
        }
        User user=new User();
        user.setId(id).setEmail(email);
        usermapper.updateById(user);
    }

    @Override
    public void lockByPhone(RegistUserInfo registUserInfo,String id) {
        String mobile = registUserInfo.getNumber();
        String code = registUserInfo.getCode();
        if (StringUtils.isEmpty(mobile) || !FormUtils.isMobile(mobile)) {
            throw new MyException(ResultCodeEnum.LOGIN_PHONE_ERROR);
        }
        // 校验验证码：redis
        String checkCode = (String) redisTemplate.opsForValue().get(mobile);
        if (!code.equals(checkCode)) {
            throw new MyException(ResultCodeEnum.CODE_ERROR);
        }
        // 用户是否注册：mysql
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("number", mobile);
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new MyException(ResultCodeEnum.REGISTER_MOBLE_ERROR);
        }
        User user=new User();
        user.setId(id).setNumber(mobile);
        usermapper.updateById(user);
    }

    @Override
    public void changepassByPhone(RegistUserInfo registUserInfo) {
        String number = registUserInfo.getNumber();
        String password = registUserInfo.getPassword();
        String code = registUserInfo.getCode();

        if (StringUtils.isEmpty(number) || !FormUtils.isMobile(number)) {
            throw new MyException(ResultCodeEnum.LOGIN_MOBILE_ERROR);
        }
        if (StringUtils.isEmpty(password)) {
            throw new MyException("请输入密码",99996);
        }

        // 校验验证码：redis
        String checkCode = (String) redisTemplate.opsForValue().get(number);
        if (!code.equals(checkCode)) {
            throw new MyException(ResultCodeEnum.CODE_ERROR);
        }
        // 用户是否注册：mysql
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("number", number);
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count == 0) {
            throw new MyException("该手机号未注册",99998);
        }

        User user = new User();
        user.setPassword(MD5.encrypt(password));//密码编码插入数据库
        UpdateWrapper wrapper=new UpdateWrapper();
        wrapper.eq("number", number);
        usermapper.update(user,wrapper);
    }

    @Override
    public void changepassByEmail(RegistUserInfo registUserInfo) {
        String email = registUserInfo.getEmail();
        String password = registUserInfo.getPassword();
        String code = registUserInfo.getCode();
        if (StringUtils.isEmpty(email) || !FormUtils.isEmail(email)) {
            throw new MyException(ResultCodeEnum.LOGIN_Email_ERROR);
        }
        if (StringUtils.isEmpty(password)) {
            throw new MyException("请输入密码",99996);
        }

        // 校验验证码：redis
        String checkCode = (String) redisTemplate.opsForValue().get(email);
        if (!code.equals(checkCode)) {
            throw new MyException(ResultCodeEnum.CODE_ERROR);
        }
        // 用户是否注册：mysql
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count == 0) {
            throw new MyException("该邮箱未注册",99997);
        }

        User user = new User();
        user.setPassword(MD5.encrypt(password));//密码编码插入数据库
        UpdateWrapper wrapper=new UpdateWrapper();
        wrapper.eq("email", email);
        usermapper.update(user,wrapper);
    }


}
