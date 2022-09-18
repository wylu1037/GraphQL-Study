package cn.edu.hust.graphqlstudy.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.edu.hust.graphqlstudy.bean.entity.ChainNode;

@Mapper
public interface ChainNodeMapper extends BaseMapper<ChainNode> {
    
}
