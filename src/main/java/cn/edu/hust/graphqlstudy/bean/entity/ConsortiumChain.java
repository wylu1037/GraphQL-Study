package cn.edu.hust.graphqlstudy.bean.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 联盟链
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("consortium_chain")
public class ConsortiumChain {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    // 链名称
    @TableField(value = "name")
    private String name;

    // 盟主id
    @TableField(value = "championId")
    private Long championId;

    // 邮箱
    @TableField(value = "email")
    private String email;

    // 链描述
    @TableField(value = "description")
    private String description;

    // 创建时间
    @TableField(value = "createAt")
    @JsonFormat(timezone = "UTF-8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createAt;

    // 更新时间
    @TableField(value = "updateAt")
    private Date updateAt;
}
