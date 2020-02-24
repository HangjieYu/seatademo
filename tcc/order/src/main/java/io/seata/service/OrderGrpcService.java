package io.seata.service;

import Tcc.OrderService.Order;
import Tcc.OrderService.OrderServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class OrderGrpcService extends OrderServiceGrpc.OrderServiceImplBase {

    @Autowired
    private OrderAction orderAction;

    @Override
    public void add(Order.Request request, StreamObserver<Order.Response> responseObserver) {
        orderAction.createOrder(null, request.getRequestId());

        Order.Response response = Order.Response.newBuilder().build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
