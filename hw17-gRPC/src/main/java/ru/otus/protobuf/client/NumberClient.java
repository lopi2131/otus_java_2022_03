package ru.otus.protobuf.client;

import io.grpc.ManagedChannelBuilder;
import ru.otus.protobuf.generated.NumberRequest;
import ru.otus.protobuf.generated.NumbersServiceGrpc;

public class NumberClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8190;
    private long value = 0;
    private static final int LOOP_LIMIT = 50;

    public static void main(String[] args) {
        var managedChannel = ManagedChannelBuilder.forAddress(SERVER_HOST, SERVER_PORT)
                .usePlaintext()
                .build();
        var asyncClient = NumbersServiceGrpc.newStub(managedChannel);

        new NumberClient().clientAction(asyncClient);

        managedChannel.shutdown();
    }

    private void clientAction(NumbersServiceGrpc.NumbersServiceStub asyncClient) {
        var numberRequest = makeNumberRequest();
        var clientStreamObserver = new ClientStreamObserver();
        asyncClient.getSequenceOfNumber(numberRequest, clientStreamObserver);

        for (var i = 0; i < LOOP_LIMIT; i++) {
            var valForPrint = getNextValue(clientStreamObserver);
            System.out.println("currentValue: " + valForPrint);
            sleep();
        }
    }

    private long getNextValue(ClientStreamObserver clientStreamObserver) {
        value = value + clientStreamObserver.getAndResetLastValue() + 1;
        return value;
    }

    private NumberRequest makeNumberRequest() {
        return NumberRequest.newBuilder()
                .setFirstValue(0)
                .setLastValue(30)
                .build();
    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
