package com.informanage.usermain.entity.VO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProjectQueryVo {
    private String name;
    private String statis;
    private Integer membernumS;
    private Integer membernumE;
    private String creatusername;
}
