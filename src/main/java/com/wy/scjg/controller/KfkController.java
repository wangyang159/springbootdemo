package com.wy.scjg.controller;

import com.wy.scjg.bean.User;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

//@Controller
//@RequestMapping("/kfk")
public class KfkController {
    @Resource
    private KafkaTemplate kafkaTemplate;

    @RequestMapping("/send")
    @ResponseBody
    public String pro_test() throws ExecutionException, InterruptedException, TimeoutException {
        User user = new User();
        user.setName("张三");
        user.setAge(20);
        kafkaTemplate.send("test", user.toString());
        return "发送完成";
    }
}
