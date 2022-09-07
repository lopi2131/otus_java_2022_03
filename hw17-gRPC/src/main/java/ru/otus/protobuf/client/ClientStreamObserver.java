package ru.otus.protobuf.client;

import io.grpc.stub.StreamObserver;
import ru.otus.protobuf.generated.NumberResponse;

public class ClientStreamObserver implements StreamObserver<ru.otus.protobuf.generated.NumberResponse> {

    private long lastValue = 0;

    @Override
    public void onNext(NumberResponse numberResponse) {
        setLastValue(numberResponse.getNumber());
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("onError");
    }

    @Override
    public void onCompleted() {
        System.out.println("onCompleted");
    }

    private synchronized void setLastValue(long value) {
        this.lastValue = value;
    }

    public synchronized long getAndResetLastValue() {
        var value = this.lastValue;
        this.lastValue = 0;
        return value;
    }
}
