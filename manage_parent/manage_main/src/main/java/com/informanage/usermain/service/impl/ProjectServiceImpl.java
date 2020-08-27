package com.informanage.usermain.service.impl;

import com.informanage.usermain.entity.Project;
import com.informanage.usermain.entity.ProjectMenberlInfo;
import com.informanage.usermain.entity.UserWorkInfo;
import com.informanage.usermain.entity.admin.ProjectStatisCount;
import com.informanage.usermain.mapper.ProjectMapper;
import com.informanage.usermain.service.ProjectMenberlInfoService;
import com.informanage.usermain.service.ProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.informanage.usermain.service.UserWorkInfoService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 项目记录信息 服务实现类
 * </p>
 *
 * @author LQ
 * @since 2020-08-09
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {
@Autowired
    UserWorkInfoService service;
@Autowired
    ProjectMenberlInfoService projectMenberlInfoService;
    @Override
    public void createProject(List yesData,Project project) {
        baseMapper.insert(project);//这句话执行后，对于的project中被被赋值了返回的主键
        ArrayList<UserWorkInfo> list = (ArrayList<UserWorkInfo>) service.listByIds(yesData);
        for (UserWorkInfo workInfo : list) {
            ProjectMenberlInfo projectMenberlInfo=new ProjectMenberlInfo();
            projectMenberlInfo.setProjectid(project.getId())
                    .setMemberid(workInfo.getUserid())
                    .setMembername(workInfo.getName())
                    .setStatis("wait");
            projectMenberlInfoService.save(projectMenberlInfo);
        }
    }

    /**
     * 后台管理查询每年项目完成状态
     * @return
     */
    @Override
    public ArrayList<ArrayList<Object>> getPassPorjectByYear() {
        ArrayList<ArrayList<Object>> result=new ArrayList<ArrayList<Object>>();
            ArrayList<Object> year=new ArrayList<>();
        ArrayList<Object> pass=new ArrayList<>();
        ArrayList<Object> wait=new ArrayList<>();
        List<Integer> listyear=baseMapper.getYearList();
        year.add("年份");
        wait.add("未完成");
        pass.add("已完成");

        for (Integer s : listyear) {
           ProjectStatisCount oneyear=baseMapper.getInfo(s);
            year.add(s);
            pass.add(oneyear.getPass());
            wait.add(oneyear.getWait());
        }
        result.add(year);
        result.add(pass);
        result.add(wait);
        return result;
    }
}
