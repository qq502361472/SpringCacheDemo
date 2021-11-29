package com.example.springcachedemo;

import com.example.springcachedemo.bo.User;
import com.example.springcachedemo.component.CacheComponent;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class SpringCacheDemoApplicationTests {
    @Autowired
    CacheComponent cacheComponent;

    @Test
    public void cacheable1() {
        User result = cacheComponent.cacheable1();
        log.info("cacheable读取结果：{}", result);
    }
    @Test
    public void cacheable() {
        User result = cacheComponent.cacheable(new User("张三", 18));
        log.info("cacheable读取结果：{}", result);
    }

    @Test
    public void cacheableWithName() {
        User result = cacheComponent.cacheableWithName(new User("李四", 12));
        log.info("cacheable读取结果：{}", result);
    }

    @Test
    public void cacheEvict() {
        cacheComponent.cacheEvict();
    }

    @Test
    public void cachePut() {
        String result = cacheComponent.cachePut();
        log.info("cachePut读取结果：{}", result);
    }

    @Test
    public void testCacheFailure() {
        User result = cacheComponent.testCacheFailure(new User("张三", 18));
        log.info("testCacheFailure读取结果：{}", result);
    }


    @Test
    public void caching() {
        User result = cacheComponent.caching(new User("李四", 18));
        log.info("cacheable读取结果：{}", result);
    }

    @Test
    public void cacheKey() {
        User result = cacheComponent.cacheKey(new User("李四", 18));
        log.info("cacheable读取结果：{}", result);
    }
}
