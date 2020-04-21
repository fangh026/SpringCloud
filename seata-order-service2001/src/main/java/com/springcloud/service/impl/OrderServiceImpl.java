package com.springcloud.service.impl;

import com.springcloud.dao.OrderDao;
import com.springcloud.domain.Order;
import com.springcloud.service.AccountService;
import com.springcloud.service.OrderService;
import com.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("------>新建订单");
        orderDao.create(order);
        log.info("------>调用库存开始");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("------>扣减结束");
        log.info("------>扣减账户开始");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("------>扣减结束");

        //修改订单状态 1 or 0
        log.info("------>修改订单状态");
        orderDao.update(order.getId(),0);
        log.info("------>修改订单结束");
    }
}
