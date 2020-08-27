package com.informanage.usermain.entity.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class QueryVoByTime {
    private String startTime;
    private String endTime;
}
