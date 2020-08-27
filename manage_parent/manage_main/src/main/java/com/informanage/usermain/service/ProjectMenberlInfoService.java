package com.informanage.usermain.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.informanage.usermain.entity.Project;
import com.informanage.usermain.entity.ProjectMenberlInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.informanage.usermain.entity.VO.AllProjectDetailVo;
import com.informanage.usermain.entity.VO.MyProjectVo;

import java.util.List;

/**
 * <p>
 * 项目成员详细信息 服务类
 * </p>
 *
 * @author LQ
 * @since 2020-08-09
 */
public interface ProjectMenberlInfoService extends IService<ProjectMenberlInfo> {

    List<MyProjectVo> selectMyPage(Page<MyProjectVo> projectPage, QueryWrapper<MyProjectVo> queryWrapper);

    IPage<AllProjectDetailVo> selectAllDetailPage(int current, int size, QueryWrapper<AllProjectDetailVo> queryWrapper);
}
