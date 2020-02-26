package io.seata.service;

import Tcc.StorageService.Storage;
import Tcc.StorageService.StorageServiceGrpc;
import io.grpc.Channel;
import io.grpc.ClientInterceptors;
import io.seata.integration.grpc.interceptor.client.ClientTransactionInterceptor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * @author mingdao
 */
@Service
public class StorageGrpcClientService {

//    @GrpcClient("StorageService")
//    private StorageServiceGrpc.StorageServiceBlockingStub storageServiceBlockingStub;

    @GrpcClient("StorageService")
    private Channel channel;

    public String add(String requestId) {

        StorageServiceGrpc.StorageServiceBlockingStub stub = StorageServiceGrpc.newBlockingStub(ClientInterceptors.intercept(channel, new ClientTransactionInterceptor()));

        Storage.Response response = stub.add(Storage.Request.newBuilder()
                .setRequestId(requestId).build());

        return response.getTxXid();
    }

}
