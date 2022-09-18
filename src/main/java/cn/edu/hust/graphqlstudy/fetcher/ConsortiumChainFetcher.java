package cn.edu.hust.graphqlstudy.fetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import cn.edu.hust.graphqlstudy.bean.entity.ConsortiumChain;
import cn.edu.hust.graphqlstudy.mapper.ConsortiumChainMapper;

@DgsComponent
public class ConsortiumChainFetcher {

    private final ConsortiumChainMapper consortiumChainMapper;

    public ConsortiumChainFetcher(ConsortiumChainMapper consortiumChainMapper) {
        this.consortiumChainMapper = consortiumChainMapper;
    }

    @DgsQuery
    public ConsortiumChain findConsortiumChainById(@InputArgument Long id) {
        return consortiumChainMapper.selectById(id);
    }
}
