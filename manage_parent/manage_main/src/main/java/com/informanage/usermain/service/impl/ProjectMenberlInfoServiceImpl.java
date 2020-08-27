package com.informanage.usermain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.informanage.usermain.entity.Project;
import com.informanage.usermain.entity.ProjectMenberlInfo;
import com.informanage.usermain.entity.VO.AllProjectDetailVo;
import com.informanage.usermain.entity.VO.MyProjectVo;
import com.informanage.usermain.mapper.ProjectMenberlInfoMapper;
import com.informanage.usermain.service.ProjectMenberlInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 项目成员详细信息 服务实现类
 * </p>
 *
 * @author LQ
 * @since 2020-08-09
 */
@Service
public class ProjectMenberlInfoServiceImpl extends ServiceImpl<ProjectMenberlInfoMapper, ProjectMenberlInfo> implements ProjectMenberlInfoService {

    @Override
    public List<MyProjectVo> selectMyPage(Page<MyProjectVo> projectPage, QueryWrapper<MyProjectVo> queryWrapper) {
        return baseMapper.selectMyPage(projectPage, queryWrapper);
    }

    @Override
    public IPage<AllProjectDetailVo> selectAllDetailPage(int current, int size, QueryWrapper<AllProjectDetailVo> queryWrapper) {
        Page<AllProjectDetailVo> page = new Page<>(current, size);
        List<AllProjectDetailVo> records = baseMapper.selectAllDetailPage(page, queryWrapper);
        return page.setRecords(records);
    }
}
