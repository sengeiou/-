package com.informanage.usermain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="UserBasicInfo对象", description="")
public class UserBasicInfo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "userid", type = IdType.INPUT)
    private String userid;

    private String name;

    private String sex;

    private Integer age;

    private Double height;

    private String weight;

    @ApiModelProperty(value = "视力")
    private String eyes;

    @ApiModelProperty(value = "民族")
    private String nation;

    @ApiModelProperty(value = "身份证号")
    private String identitycard;

    @ApiModelProperty(value = "婚姻状况")
    private String ismarry;

    @ApiModelProperty(value = "户口所在地")
    private String bornplace;

    @ApiModelProperty(value = "政治面貌")
    private String politicaloutlook;

    @ApiModelProperty(value = "现居住地")
    private String nowplace;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "健康状况")
    private String healthy;

    @ApiModelProperty(value = "紧急联系人")
    private String emergencypeople;

    @ApiModelProperty(value = "与本人关系")
    private String relationship;

    @ApiModelProperty(value = "紧急联系人电话")
    private String contactphone;


}
