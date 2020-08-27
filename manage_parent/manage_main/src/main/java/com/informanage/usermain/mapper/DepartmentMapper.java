package com.informanage.usermain.mapper;

import com.informanage.usermain.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.informanage.usermain.entity.admin.BarSalary;
import com.informanage.usermain.entity.admin.ChangeDepartmentPower;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LQ
 * @since 2020-08-05
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    List<BarSalary> getBarSalary();

    List<ChangeDepartmentPower> departmentlist2();
}
