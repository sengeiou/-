package com.informanage.usermain.entity.VO;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class ApplySpendPlus {
    private String statis;
    private String tableid;
    private Integer money;
    private String name;
    private String type;
    private String detail;
    private String sourceurl;
    private Date gmtCreate;
}
