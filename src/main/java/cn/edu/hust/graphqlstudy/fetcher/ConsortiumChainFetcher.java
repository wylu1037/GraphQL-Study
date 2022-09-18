package cn.edu.hust.graphqlstudy.fetcher;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import cn.edu.hust.graphqlstudy.bean.entity.ChainNode;
import cn.edu.hust.graphqlstudy.bean.entity.ConsortiumChain;
import cn.edu.hust.graphqlstudy.bean.entity.User;
import cn.edu.hust.graphqlstudy.mapper.ChainNodeMapper;
import cn.edu.hust.graphqlstudy.mapper.ConsortiumChainMapper;
import cn.edu.hust.graphqlstudy.mapper.UserMapper;

@DgsComponent
public class ConsortiumChainFetcher {

    private final ConsortiumChainMapper consortiumChainMapper;
    private final ChainNodeMapper chainNodeMapper;
    private final UserMapper userMapper;

    public ConsortiumChainFetcher(ConsortiumChainMapper consortiumChainMapper, 
    ChainNodeMapper chainNodeMapper,
    UserMapper userMapper) {
        this.consortiumChainMapper = consortiumChainMapper;
        this.chainNodeMapper = chainNodeMapper;
        this.userMapper = userMapper;
    }

    @DgsQuery
    public ConsortiumChain findConsortiumChainById(@InputArgument Long id) {
        return consortiumChainMapper.selectById(id);
    }

    @DgsData(parentType = "ConsortiumChain", field = "user")
    public User findUserById(DgsDataFetchingEnvironment dfe) {
        ConsortiumChain chain = dfe.getSource();
        return userMapper.selectById(chain.getChampionId());
    }

    @DgsData(parentType = "ConsortiumChain", field = "nodeList")
    public List<ChainNode> findNodeListByChainId(DgsDataFetchingEnvironment dfe) {
        ConsortiumChain chain = dfe.getSource();
        return chainNodeMapper.selectList(Wrappers.<ChainNode>lambdaQuery()
            .eq(ChainNode::getChainId, chain.getId())
        );
    }
}
