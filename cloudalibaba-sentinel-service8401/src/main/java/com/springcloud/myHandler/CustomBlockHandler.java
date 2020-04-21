package com.springcloud.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.entities.CommonResult;

public class CustomBlockHandler {
    public static CommonResult  handlerException(BlockException exception){
        return new CommonResult(444,"global handlerException-----1");
    }

    public static CommonResult  handlerException2(BlockException exception){
        return new CommonResult(444,"global handlerException-----2");
    }
}
