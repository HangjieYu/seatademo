package io.seata.service;

import Tcc.OrderService.Order;
import Tcc.OrderService.OrderServiceGrpc;
import io.grpc.stub.StreamObserver;
import io.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mingdao
 */
@Component
public class OrderGrpcService extends OrderServiceGrpc.OrderServiceImplBase {

    @Autowired
    private OrderAction orderAction;

    @Override
    public void add(Order.Request request, StreamObserver<Order.Response> responseObserver) {

        String xid = RootContext.getXID();
        System.out.println(xid);

        orderAction.prepare(null, request.getRequestId());

        Order.Response response = Order.Response.newBuilder().setTxXid(xid).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
