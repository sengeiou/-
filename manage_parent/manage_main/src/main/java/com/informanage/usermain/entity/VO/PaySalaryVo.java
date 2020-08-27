package com.informanage.usermain.entity.VO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PaySalaryVo {
    private String userid;

    private Integer departmentid;

    private Integer salary;
    private String email;
    private String phone;
    private String name;
}
