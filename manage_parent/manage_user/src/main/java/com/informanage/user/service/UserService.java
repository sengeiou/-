package com.informanage.user.service;

import com.baidu.aip.face.AipFace;
import com.informanage.common.JwtInfo;
import com.informanage.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.informanage.user.entity.VO.RegistUserInfo;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LQ
 * @since 2020-07-31
 */
public interface UserService extends IService<User> {

    void insertByPhone(RegistUserInfo registUserInfo);
    void insertByEmail(RegistUserInfo registUserInfo);

    void registFace(String image, String imageType, String huadi, String uuid, HashMap<String, String> stringStringHashMap, AipFace client, MultipartFile file);

    String checkIsRegist(JSONObject result);

    String checkFace(JSONObject result);

    User getByOpenid(String openid);

    String selectByUserId(String user_id);

    String emailLogin(RegistUserInfo registUserInfo);

    String phoneLogin(RegistUserInfo registUserInfo);

    void changeAvator(JwtInfo jwtInfo);

    void lockByEmail(RegistUserInfo registUserInfo,String id);

    void lockByPhone(RegistUserInfo registUserInfo,String id);

    void changepassByPhone(RegistUserInfo registUserInfo);

    void changepassByEmail(RegistUserInfo registUserInfo);
}
