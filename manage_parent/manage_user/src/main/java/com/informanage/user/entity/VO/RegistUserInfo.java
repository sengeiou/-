package com.informanage.user.entity.VO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RegistUserInfo implements Serializable {
    private String number;

    private String email;

    private String password;

    private String nickname;

    private  String code;
}
