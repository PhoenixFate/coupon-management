package com.phoenix.core.redis;


import javax.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
    private static Logger logger = LoggerFactory.getLogger(RedisConfig.class);
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.pool.max-wait}")
    private long maxWaitMillis;
    @Value("${spring.redis.password}")
    private String password;

    public RedisConfig() {
    }

    @Bean
    public JedisPool wxToolredisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(this.maxIdle);
        jedisPoolConfig.setMaxWaitMillis(this.maxWaitMillis);
        JedisPool jedisPool = null;
        if (this.password != null && !"".equals(this.password)) {
            jedisPool = new JedisPool(jedisPoolConfig, this.host, this.port, this.timeout, this.password);
        } else {
            jedisPool = new JedisPool(jedisPoolConfig, this.host, this.port, this.timeout);
        }

        new JedisUtil(jedisPool);
        logger.info("redis连接正常: " + this.host);
        return jedisPool;
    }

    @PreDestroy
    public void destroy() {
        JedisUtil.destroy();
    }
}

