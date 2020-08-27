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
 * 报销申请表
 * </p>
 *
 * @author LQ
 * @since 2020-08-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MApplySpend对象", description="报销申请表")
public class MApplySpend implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "报销金额")
    private Integer money;

    @ApiModelProperty(value = "申请人ID")
    private String applicationid;

    @ApiModelProperty(value = "处理人ID")
    private String dealuserid;

    @ApiModelProperty(value = "处理状态")
    private String statis;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "申请详细原因")
    private String detail;

    @ApiModelProperty(value = "申报类型")
    private String type;

    private String sourceurl;


}
