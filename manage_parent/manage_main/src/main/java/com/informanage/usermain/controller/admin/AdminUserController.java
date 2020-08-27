package com.informanage.usermain.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.informanage.common.R;
import com.informanage.usermain.entity.User;
import com.informanage.usermain.entity.VO.WorkInfoQuery;
import com.informanage.usermain.entity.admin.UserQueryVo;
import com.informanage.usermain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/usermain/admin/user")
@CrossOrigin
public class AdminUserController {
    @Autowired
    UserService userService;

    @PostMapping("/getAllUser/{current}/{size}")
    public R getAllUser(@PathVariable int current, @PathVariable int size,
                        @RequestBody(required = false) UserQueryVo userQueryVo){
        String nickname = userQueryVo.getNickname();
        String statis = userQueryVo.getStatis();
        String applyDateBegin = userQueryVo.getApplyDateBegin();
        String applyDateEnd = userQueryVo.getApplyDateEnd();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(nickname))
            userQueryWrapper.like("nickname", nickname);
        if(!StringUtils.isEmpty(statis))
            userQueryWrapper.eq("is_deleted", statis);
        if(!StringUtils.isEmpty(applyDateBegin))
            userQueryWrapper.gt("gmt_create", applyDateBegin);
        if(!StringUtils.isEmpty(applyDateEnd))
            userQueryWrapper.lt("gmt_create", applyDateEnd);
        Page<User> page=new Page<>(current, size);
        userService.mypage(page, userQueryWrapper);//这个自定义方法查询出来的逻辑删除字段为null，不能转换成boolean，可以自定义typehandler
       // userService.page(page, userQueryWrapper);//这个查询自带逻辑删除条件，不能用
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("list", page.getRecords());
        return R.ok().data(map);


    }

    @GetMapping("/lock/{id}")
    public  R lock(@PathVariable String id){

        userService.myupdate(id,1);
        return R.ok().message("锁定成功，该账号已不能正常登录");
    }

    @GetMapping("/unlock/{id}")
    public  R unlock(@PathVariable String id){

        userService.myupdate(id,0);
        return R.ok().message("解锁成功，该账号已能正常登录");
    }
}
