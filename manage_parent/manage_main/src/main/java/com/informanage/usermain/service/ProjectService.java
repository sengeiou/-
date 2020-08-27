package com.informanage.usermain.service;

import com.informanage.usermain.entity.Project;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 项目记录信息 服务类
 * </p>
 *
 * @author LQ
 * @since 2020-08-09
 */
public interface ProjectService extends IService<Project> {

    void createProject(List yesData,Project project);

    ArrayList<ArrayList<Object>> getPassPorjectByYear();
}
