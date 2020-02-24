package io.seata.service;

import Tcc.OrderService.Order;
import Tcc.OrderService.OrderServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class OrderGrpcClientService {

    @GrpcClient("OrderService")
    private OrderServiceGrpc.OrderServiceBlockingStub orderServiceBlockingStub;

    public void add(String requestId) {

        Order.Response response = orderServiceBlockingStub.add(Order.Request.newBuilder()
                .setRequestId(requestId).build());

        System.out.println(response.getRequestId());
    }

}
