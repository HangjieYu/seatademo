package io.seata.controller;

import io.seata.service.OrderGrpcClientService;
import io.seata.service.StorageGrpcClientService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author mingdao
 */
@RestController
public class BusinessController {

    @Autowired
    private StorageGrpcClientService storageGrpcClientService;

    @Autowired
    private OrderGrpcClientService orderGrpcClientService;

    /**
     * 模拟全局事务提交
     *
     * @return
     */
    @GlobalTransactional
    @RequestMapping(value = "/purchase", produces = "application/json")
    public void purchase(@RequestParam(name = "user") String user,
                         @RequestParam(name = "callback") Boolean callback) {

        // 减库存，下订单
        orderGrpcClientService.add(user);
        storageGrpcClientService.add(user);

        if (callback) {
            throw new RuntimeException("执行回滚");
        }
    }
}
