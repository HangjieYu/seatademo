package io.seata.service;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mingdao
 */
@Service
public class BusinessService {

    @Autowired
    private OrderGrpcClientService orderGrpcClientService;

    /**
     * 减库存，下订单
     *
     * @param userId
     */
    @GlobalTransactional
    public void purchase(String userId) {

//        storageFeignClient.deduct(commodityCode, orderCount);
        orderGrpcClientService.add(userId);

        throw new RuntimeException("执行回滚");
    }
}
