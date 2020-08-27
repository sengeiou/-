package com.informanage.usermain.entity.VO;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ApplyInfoQuery {
    private String name;
    private Integer applyMoneyBegin;
    private Integer applyMoneyEnd;
    private String applyDateBegin;
    private String applyDateEnd;
    private String statis;

}
