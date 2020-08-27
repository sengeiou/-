package com.informanage.usermain.controller.admin;

import com.informanage.common.R;
import com.informanage.usermain.service.ProjectService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/usermain/project/admin")
@CrossOrigin
public class AdminProjectController {
    @Autowired
    ProjectService projectService;

    /**
     * 后台管理查询每年项目完成状态
     * @return
     */
    @GetMapping("/getPassPorjectByYear")
    public R getPassPorjectByYear(){
        ArrayList<ArrayList<Object>> list=projectService.getPassPorjectByYear();
        return R.ok().data("list",list);
    }
}
