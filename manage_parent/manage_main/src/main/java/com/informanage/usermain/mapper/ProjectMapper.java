package com.informanage.usermain.mapper;

import com.informanage.usermain.entity.Project;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.informanage.usermain.entity.admin.ProjectStatisCount;

import java.util.List;

/**
 * <p>
 * 项目记录信息 Mapper 接口
 * </p>
 *
 * @author LQ
 * @since 2020-08-09
 */
public interface ProjectMapper extends BaseMapper<Project> {

    List<Integer> getYearList();

    ProjectStatisCount getInfo(Integer id);
}
