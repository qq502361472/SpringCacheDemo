package com.example.springcachedemo.component;

import com.example.springcachedemo.bo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@CacheConfig(cacheNames = "person")
public class CacheComponent {

    @Cacheable(cacheNames = "person")
    public User cacheable1() {
        log.info("查询数据库读取testCacheable...");
        User user = new User("张三", 18);
        return user;
    }

    @Cacheable(cacheNames = "person-2")
    public User cacheable(User user) {
        log.info("查询数据库读取testCacheable...");
        return user;
    }

    @Cacheable(cacheNames = {"user", "staff"}, key = "#user.username")
    public User cacheableWithName(User user) {
        log.info("查询数据库读取testCacheable...");
        return user;
    }

    @CacheEvict(cacheNames = {"user", "staff"}, allEntries = true, beforeInvocation = true)
    public void cacheEvict() {
        log.info("数据库删除testCacheable...");
    }

    @CachePut(cacheNames = {"user", "staff"})
    public String cachePut() {
        log.info("数据库插入最新数据");
        return "最新数据1";
    }

    public User testCacheFailure(User user) {
//        AopContext.getProxyHolder().getProxy(this).can
        return this.cacheable(user);
    }

    //    @Cacheable(cacheNames = "user:", key = "#user.age")
//    @Cacheable(cacheNames = "user:", key = "#user.getAge()")
//    @Cacheable(cacheNames = "user:", condition = "#user.age>=18")
//    @Cacheable(cacheNames = "user:", unless = "#result.age<18")
    @Cacheable(cacheNames = "user:", condition = "#user.age>=18", unless = "#result.age<=18")
    public User cacheKey(User user) {
        log.info("访问数据库查询数据");
        return user;
    }

    @Caching(
            evict = {@CacheEvict(cacheNames = "user", key = "#user.getUsername()")},
            cacheable = @Cacheable(cacheNames = "staff", key = "#user.username"),
            put = @CachePut()
    )
    public User caching(User user) {
        log.info("读取数据库");
        return user;
    }
}
