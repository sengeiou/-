package com.informanage.usermain.entity.VO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AllSalaryRecords {
    private String tableid;
    private Date time;
    private String department;//收款人部门名称
    private  String dealname;//处理人姓名
    private String name;//收款人姓名
    private  Integer money;

}
