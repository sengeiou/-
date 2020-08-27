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
 * 部门分组申请信息表
 * </p>
 *
 * @author LQ
 * @since 2020-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MApplyDepartment对象", description="部门分组申请信息表")
public class MApplyDepartment implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "申请人ID")
    private String applicationid;

    @ApiModelProperty(value = "申请部门ID")
    private Integer departmentid;

    @ApiModelProperty(value = "处理人ID")
    private String dealuserid;

    @ApiModelProperty(value = "申请详细说明")
    private String detail;

    @ApiModelProperty(value = "审批状态wait,defeat,pass")
    private String statis;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;


}
