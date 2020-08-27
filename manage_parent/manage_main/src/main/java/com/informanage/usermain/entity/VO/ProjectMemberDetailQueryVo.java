package com.informanage.usermain.entity.VO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProjectMemberDetailQueryVo {//前端传来参数接收vo
    private String name;
    private String creatusername;
    private String timeStart;
    private String timeEnd;
    private String statis;
    private String membername;
}
