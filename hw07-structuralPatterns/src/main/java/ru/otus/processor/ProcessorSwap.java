package ru.otus.processor;

import ru.otus.model.Message;

public class ProcessorSwap implements Processor{
    @Override
    public Message process(Message message) {
        var value = message.getField11();
        message.toBuilder()
                .field11(message.getField12())
                .field12(value)
                .build();
        return null;
    }
}