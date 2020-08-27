package com.informanage.usermain.controller.admin;

import com.informanage.common.R;
import com.informanage.usermain.entity.Department;
import com.informanage.usermain.entity.Power;
import com.informanage.usermain.entity.PowerDepartment;
import com.informanage.usermain.entity.UserWorkInfo;
import com.informanage.usermain.entity.admin.AddDepartment;
import com.informanage.usermain.entity.admin.BarSalary;
import com.informanage.usermain.service.DepartmentService;
import com.informanage.usermain.service.PowerDepartmentService;
import com.informanage.usermain.service.UserWorkInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usermain/department")
@CrossOrigin
public class AdminDepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private UserWorkInfoService userWorkInfoService;
    @Autowired
    private PowerDepartmentService powerDepartmentService;

    @GetMapping("/getdepartmentPeople")
    public R getdepartmentPeople() {
        List<BarSalary> list = departmentService.getBarSalary();
        ArrayList<String> namelist = new ArrayList<>();
        ArrayList<Integer> numlist = new ArrayList<>();
        for (BarSalary barSalary : list) {
            namelist.add(barSalary.getSalary());
            numlist.add(barSalary.getNum());
        }
        return R.ok().data("list1", namelist).data("list2", numlist);
    }

    @PostMapping("/adddepartment")
    public R adddepartment(@RequestBody AddDepartment department) {
        String name = department.getName();
        List<Integer> power = department.getPower();//权限ID
        List<String> yesData = department.getYesData();//员工ID
        String detail = department.getDetail();
        if (StringUtils.isEmpty(name))
            return R.error().message("请填写部门名称");
        if (StringUtils.isEmpty(detail))
            return R.error().message("请填写部门详细描述");
        if (power.size() == 0)
            return R.error().message("请选择部门权力");
        if (yesData.size() == 0)
            return R.error().message("请选择部门员工");
        Department department1 = new Department();
        department1.setName(name).setDetail(detail).setPeoplenumber(yesData.size());
        departmentService.insert(department1);//调用mapper中的insert,返回id主键

        Runnable runnable = () ->{
            for (String yesDatum : yesData) {//这个循环修改员工的部门ID
                UserWorkInfo workInfo = new UserWorkInfo();
                workInfo.setUserid(yesDatum).setDepartmentid(department1.getId());
                userWorkInfoService.updateById(workInfo);
            }

            for (Integer po : power) {//这个方法为部门添加权限
                PowerDepartment powerDepartment = new PowerDepartment();
                powerDepartment.setDepartmentid(department1.getId()).setPowerid(po);
                powerDepartmentService.save(powerDepartment);
            }
        };
        new Thread(runnable).start();

        return R.ok().message("添加部门成功！");
    }
}
