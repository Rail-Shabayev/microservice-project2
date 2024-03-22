package org.rail.project.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.time.Duration;

@Configuration
@EnableRedisRepositories
public class RedisConfig {
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .entryTtl(Duration.ofMinutes(10));
    }

    @Bean
    @ConditionalOnProperty(prefix="service.docker", name = "environment", havingValue = "false")
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("localhost", 6379);
        return new JedisConnectionFactory(config);
    }
    @Bean
    @ConditionalOnProperty(prefix="service.docker", name = "environment", havingValue = "true")
    public JedisConnectionFactory jedisConnectionFactoryDocker() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("redis", 6379);
        return new JedisConnectionFactory(config);
    }
}
