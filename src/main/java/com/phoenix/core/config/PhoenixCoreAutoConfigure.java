package com.phoenix.core.config;
import com.phoenix.core.dao.BasicDaoMybatisImpl;
import com.phoenix.core.filter.ReqRespLogFilter;
import com.phoenix.core.utils.GlobalConfig;
import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class PhoenixCoreAutoConfigure {
    @Autowired
    private ApplicationContext appContext;

    public PhoenixCoreAutoConfigure() {
    }

    @Bean
    public GlobalConfig globalConfig() {
        Environment envm = this.appContext.getEnvironment();
        return new GlobalConfig(envm);
    }

    @Bean
    public BasicDaoMybatisImpl casicDaoMybatisImpl() {
        return new BasicDaoMybatisImpl();
    }

    @Bean
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(20);
        threadPool.setMaxPoolSize(500);
        threadPool.setQueueCapacity(20);
        threadPool.setWaitForTasksToCompleteOnShutdown(true);
        threadPool.initialize();
        return threadPool;
    }

    @Bean
    public FilterRegistrationBean requestLogFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        ReqRespLogFilter filter = new ReqRespLogFilter();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns(new String[]{"/*"});
        return registrationBean;
    }
}
