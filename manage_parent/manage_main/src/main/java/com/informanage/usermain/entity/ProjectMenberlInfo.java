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
 * 项目成员详细信息
 * </p>
 *
 * @author LQ
 * @since 2020-08-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ProjectMenberlInfo对象", description="项目成员详细信息")
public class ProjectMenberlInfo implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer projectid;

    private String memberid;

    private String statis;

    private String membername;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;


}
