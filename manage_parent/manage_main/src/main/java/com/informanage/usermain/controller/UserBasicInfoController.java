package com.informanage.usermain.controller;


import com.informanage.common.JwtInfo;
import com.informanage.common.JwtUtils;
import com.informanage.common.R;
import com.informanage.usermain.entity.UserBasicInfo;
import com.informanage.usermain.service.UserBasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LQ
 * @since 2020-08-05
 */
@RestController
@RequestMapping("/usermain/user-basic-info")
@CrossOrigin
public class UserBasicInfoController {
@Autowired
    UserBasicInfoService userBasicInfoService;

@GetMapping("/getUserBasicInfo")
    public R getUserBasicInfo(HttpServletRequest request){
    JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
    UserBasicInfo user=userBasicInfoService.getById(new UserBasicInfo().setUserid(jwtInfo.getId()));
    return R.ok().data("user",user);
}

    @PostMapping("/updateUserBasicInfo")
    public R updateUserBasicInfo(@RequestBody UserBasicInfo userBasicInfo,HttpServletRequest request){
        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        userBasicInfoService.saveOrUpdate(userBasicInfo.setUserid(jwtInfo.getId()));
        return R.ok().message("保存成功！");
    }
}

