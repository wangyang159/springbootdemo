package com.wy.scjg;

import com.wy.scjg.bean.User;
import com.wy.scjg.repository.UserRepository;
import com.wy.scjg.service.UserService;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ESTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void imports(){
        List<User> list = userService.list();
        for(User user:list){
            userRepository.save(user);
        }
    }

}