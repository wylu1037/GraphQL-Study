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

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("chain_node")
public class ChainNode {
    
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "userId")
    private Long userId;

    private String name;

    @TableField(value = "chainId")
    private Long chainId;

    private Integer type;

    private String ip;

    private Integer port;

    private String remark;

    // 创建时间
    @TableField(value = "createAt")
    @JsonFormat(timezone = "UTF-8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createAt;

    // 更新时间
    @TableField(value = "updateAt")
    private Date updateAt;
}
