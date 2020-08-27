package com.informanage.usermain.entity.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChangeDepartmentPower {
    private Integer id;

    private String name;

    private List<Integer> list;
}
