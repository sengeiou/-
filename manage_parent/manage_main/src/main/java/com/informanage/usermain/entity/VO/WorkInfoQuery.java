package com.informanage.usermain.entity.VO;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class WorkInfoQuery {
    private String name;
    private String sex;
    private String applyDateBegin;
    private String applyDateEnd;
    private Integer departmentid;
}
