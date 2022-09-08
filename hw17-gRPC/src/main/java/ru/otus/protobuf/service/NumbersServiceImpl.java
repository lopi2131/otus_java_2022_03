package ru.otus.protobuf.service;

import io.grpc.stub.StreamObserver;
import ru.otus.protobuf.generated.NumberRequest;
import ru.otus.protobuf.generated.NumberResponse;
import ru.otus.protobuf.generated.NumbersServiceGrpc;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class NumbersServiceImpl extends NumbersServiceGrpc.NumbersServiceImplBase {

    @Override
    public void getSequenceOfNumber(NumberRequest request, StreamObserver<NumberResponse> responseObserver) {
        var currentValue = new AtomicLong(request.getFirstValue());

        var executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            var value = currentValue.incrementAndGet();
            var response = NumberResponse
                    .newBuilder()
                    .setNumber(value)
                    .build();
            responseObserver.onNext(response);
            if (value == request.getLastValue()) {
                executor.shutdown();
                responseObserver.onCompleted();
            }
        };

        executor.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

    }
}
