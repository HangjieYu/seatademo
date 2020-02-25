package io.seata.service;

import Tcc.StorageService.Storage;
import Tcc.StorageService.StorageServiceGrpc;
import io.grpc.stub.StreamObserver;
import io.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mingdao
 */
@Component
public class StorageGrpcService extends StorageServiceGrpc.StorageServiceImplBase {

    @Autowired
    private StorageAction storageAction;

    @Override
    public void add(Storage.Request request, StreamObserver<Storage.Response> responseObserver) {
        String xid = RootContext.getXID();
        System.out.println(xid);

        storageAction.prepare(null, request.getRequestId());

        Storage.Response response = Storage.Response.newBuilder().setTxXid(xid).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
