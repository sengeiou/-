package com.informanage.usermain.service;

import com.informanage.usermain.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.informanage.usermain.entity.admin.BarSalary;
import com.informanage.usermain.entity.admin.ChangeDepartmentPower;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LQ
 * @since 2020-08-05
 */
public interface DepartmentService extends IService<Department> {

    List<Department> departmentlist();

    List<BarSalary> getBarSalary();

    void insert(Department department1);

    List<ChangeDepartmentPower> departmentlist2();

    void changePower(List<ChangeDepartmentPower> changeDepartmentPower);
}
