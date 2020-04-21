package com.springcloud.Controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        log.info(Thread.currentThread().getName()+"\t"+">>>>>testA");
        return "--------testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "--------testB";
    }

    @GetMapping("/testD")
    public String testD(){
        log.info("RT>>>testD");
        int age=10/0;
        return "--------testD";
    }

    @GetMapping("/testE")
    public String testE(){
        log.info("RT>>>testE");
        int age=10/0;
        return "--------testE";
    }

    @GetMapping("/testHotkey")
    @SentinelResource(value = "testHotkey",blockHandler = "deal_testHotkey")
    public String testHotkey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        return "--------testHotkey";
    }

    public String deal_testHotkey (String p1, String p2, BlockException exception){
        return "--------deal_testHotkey,/(ㄒoㄒ)/~~";
    }
}
