package com.informanage.usermain.entity.VO;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SendEmailVo {
    private  String username;
    private  String departmentname;
    private String email;
    private  String statis;
}
