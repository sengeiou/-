package com.informanage.usermain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.informanage.usermain.entity.Project;
import com.informanage.usermain.entity.ProjectMenberlInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.informanage.usermain.entity.VO.AllProjectDetailVo;
import com.informanage.usermain.entity.VO.MyProjectVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 项目成员详细信息 Mapper 接口
 * </p>
 *
 * @author LQ
 * @since 2020-08-09
 */
public interface ProjectMenberlInfoMapper extends BaseMapper<ProjectMenberlInfo> {

    List<MyProjectVo> selectMyPage(Page<MyProjectVo> projectPage,
                                   @Param(Constants.WRAPPER)QueryWrapper<MyProjectVo> queryWrapper);

    List<AllProjectDetailVo> selectAllDetailPage(Page<AllProjectDetailVo> page,
                                                 @Param(Constants.WRAPPER)QueryWrapper<AllProjectDetailVo> queryWrapper);
}
