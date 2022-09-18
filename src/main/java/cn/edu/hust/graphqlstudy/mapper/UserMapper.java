package cn.edu.hust.graphqlstudy.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.edu.hust.graphqlstudy.bean.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    
}
