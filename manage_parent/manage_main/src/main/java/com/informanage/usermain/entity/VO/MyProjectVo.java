package com.informanage.usermain.entity.VO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MyProjectVo {
    private String id;
    private Date gmtCreate;
    private String name;//项目名字
    private String creatusername;
    private String info;
    private Integer membernum;
}
