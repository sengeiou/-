package com.informanage.usermain.entity.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AddDepartment {
    private String departmentid;
    private String name;
    private String detail;
    private List<String> yesData=new ArrayList<>();
    private List<Integer> power=new ArrayList<>();
}
