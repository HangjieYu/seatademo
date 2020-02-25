package io.seata;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;
import io.seata.integration.grpc.interceptor.server.ServerTransactionInterceptor;
import io.seata.service.StorageGrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mingdao
 */
@SpringBootApplication
public class StorageServerApplication implements CommandLineRunner {

    @Value("${storage.server.port}")
    int port;

    @Autowired
    private StorageGrpcService storageGrpcService;

    public static void main(String[] args) {
        SpringApplication.run(StorageServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(port);
        Server server = serverBuilder
                .addService(ServerInterceptors.intercept(storageGrpcService, new ServerTransactionInterceptor()))
                .build();
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                if (server != null) {
                    server.shutdown();
                }
                System.err.println("*** server shut down");
            }
        });
    }
}
