package com.informanage.usermain.entity.VO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author LQ
 * @since 2020-08-05
 */
@Data
@Accessors(chain = true)
public class UserWorkInfoPlus {

    private String userid;

    private String name;

    private Integer age;

    private String sex;

    private String education;

    private String department;

    private String workinfo;

    private String awardsinfo;

    private String punishmentinfo;

    private String phone;

    private String address;

    private Integer salary;


}
