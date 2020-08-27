package com.informanage.usermain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.informanage.usermain.entity.Department;
import com.informanage.usermain.entity.PowerDepartment;
import com.informanage.usermain.entity.admin.BarSalary;
import com.informanage.usermain.entity.admin.ChangeDepartmentPower;
import com.informanage.usermain.mapper.DepartmentMapper;
import com.informanage.usermain.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.informanage.usermain.service.PowerDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LQ
 * @since 2020-08-05
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    PowerDepartmentService powerDepartmentService;

    @Override
    @Cacheable(value = "listDepartment", key = "methodName")//可以用springel表达式获取参数，参数属性，方法名等作为key
    public List<Department> departmentlist() {
        return departmentMapper.selectList(null);
    }

    @Override
    public List<BarSalary> getBarSalary() {
        return baseMapper.getBarSalary();
    }

    @Override
    @CacheEvict(value = "listDepartment", allEntries = true)//清除全部缓存
    public void insert(Department department1) {
        baseMapper.insert(department1);
    }

    @Override
    public List<ChangeDepartmentPower> departmentlist2() {
        return baseMapper.departmentlist2();
    }

    @Override
    @Transactional(rollbackForClassName = {"RuntimeException", "Exception"})
    public void changePower(List<ChangeDepartmentPower> changeDepartmentPower) {
        for (ChangeDepartmentPower departmentPower : changeDepartmentPower) {
            QueryWrapper<PowerDepartment> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("departmentid", departmentPower.getId());
            powerDepartmentService.remove(queryWrapper);//删除全部权限，重新添加
            ArrayList<PowerDepartment> list = new ArrayList<>();
            for (Integer integer : departmentPower.getList()) {
                list.add(new PowerDepartment().setDepartmentid(departmentPower.getId()).setPowerid(integer));
            }
            powerDepartmentService.saveBatch(list);
        }
    }
}
