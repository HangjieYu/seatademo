package io.seata.service;

import Tcc.OrderService.Order;
import Tcc.OrderService.OrderServiceGrpc;
import io.grpc.Channel;
import io.grpc.ClientInterceptors;
import io.seata.integration.grpc.interceptor.client.ClientTransactionInterceptor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * @author mingdao
 */
@Service
public class OrderGrpcClientService {

//    @GrpcClient("OrderService")
//    private OrderServiceGrpc.OrderServiceBlockingStub orderServiceBlockingStub;

    @GrpcClient("OrderService")
    private Channel channel;

    public void add(String requestId) {

        OrderServiceGrpc.OrderServiceBlockingStub stub = OrderServiceGrpc.newBlockingStub(ClientInterceptors.intercept(channel, new ClientTransactionInterceptor()));

        Order.Response response = stub.add(Order.Request.newBuilder()
                .setRequestId(requestId).build());

        System.out.println(response.getTxXid());
    }

}
