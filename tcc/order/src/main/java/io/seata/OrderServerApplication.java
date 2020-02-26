package io.seata;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;
import io.seata.integration.grpc.interceptor.server.ServerTransactionInterceptor;
import io.seata.service.OrderGrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mingdao
 */
@SpringBootApplication
public class OrderServerApplication implements CommandLineRunner {

    @Value("${order.server.port}")
    int port;

    @Autowired
    private OrderGrpcService orderGrpcService;

    public static void main(String[] args) {
        SpringApplication.run(OrderServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(port);
        Server server = serverBuilder
                .addService(ServerInterceptors.intercept(orderGrpcService, new ServerTransactionInterceptor()))
                .build();
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("server shut down");
                if (server != null) {
                    server.shutdown();
                }
            }
        });
    }
}
