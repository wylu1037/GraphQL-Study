package cn.edu.hust.graphqlstudy.config;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;

/**
 * MybatisPlus配置
 */
@Configuration
public class MybatisPlusConfig {

    @Bean("mybatisPlusInterceptor")
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        List<InnerInterceptor> list = new ArrayList<>();
        list.add(new PaginationInnerInterceptor());
        list.add(new BlockAttackInnerInterceptor());

        interceptor.setInterceptors(list);
        return interceptor;
    }

    public SqlSessionFactory sqlSessionFactory(@Qualifier("masterDataSource")DataSource dataSource,
    @Qualifier("mybatisPlusInterceptor")MybatisPlusInterceptor mybatisPlusInterceptor) throws Exception {
        MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        MybatisConfiguration configuration = new MybatisConfiguration();
        // configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setCacheEnabled(true);
        // 解决属性大写变小写加下划线的问题
        configuration.setMapUnderscoreToCamelCase(false);
        configuration.setCallSettersOnNulls(true);
        // 正式环境需要屏蔽：MybatisPlus执行SQL时，将其打印出来
        // configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        sessionFactory.setConfiguration(configuration);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath*:mappers/*.xml"));

        // 设置插件
        sessionFactory.setPlugins(mybatisPlusInterceptor);
        return sessionFactory.getObject();
    }
}
