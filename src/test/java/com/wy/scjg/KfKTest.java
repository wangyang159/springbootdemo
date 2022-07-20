package com.wy.scjg;

import com.wy.scjg.config.MyPartitionTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@SpringBootTest
public class KfKTest {


    @Autowired
    private MyPartitionTemplate myPartitionTemplate;

    @Test
    void pro_test() throws ExecutionException, InterruptedException, TimeoutException {
        myPartitionTemplate.getKafkaTemplate().send("test",0,"0", "0开头的数据");
    }

}
