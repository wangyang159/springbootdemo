package com.wy.scjg;

import com.wy.scjg.bean.User;
import com.wy.scjg.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;

    @Test
    void test(){
        List<User> list = userService.list();
        for(User user:list){
//            redisTemplate.opsForValue().set(user.getId().toString(),user);
            System.out.println(redisTemplate.opsForValue().get(user.getId().toString()));

//            stringRedisTemplate.opsForValue().set("1","1");
//            System.out.println(stringRedisTemplate.opsForValue().get(user.getId().toString()));
        }
    }

}
