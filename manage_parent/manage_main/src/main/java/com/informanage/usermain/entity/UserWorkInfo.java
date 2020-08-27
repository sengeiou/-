package com.informanage.usermain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author LQ
 * @since 2020-08-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserWorkInfo对象", description="")
public class UserWorkInfo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "userid", type = IdType.INPUT)
    private String userid;

    private String name;

    private Integer age;

    private String sex;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "所属分组，0表示是刚注册用户")
    private Integer departmentid;

    private String workinfo;

    @ApiModelProperty(value = "获奖信息")
    private String awardsinfo;

    @ApiModelProperty(value = "处分信息")
    private String punishmentinfo;

    @ApiModelProperty(value = "联系方式")
    private String phone;

    @ApiModelProperty(value = "联系地址")
    private String address;

    @ApiModelProperty(value = "信息审核状态 等待状态为wait 通过状态为pass 未通过状态未defeat")
    private String statis;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    private Integer salary;


}
