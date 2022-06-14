package ru.otus.processor;

import ru.otus.model.Message;

public class ProcessorEvenSecond implements Processor{
    DateTimeProvider dateTimeProvider;

    public ProcessorEvenSecond(DateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }

    @Override
    public Message process(Message message) {
        if ((dateTimeProvider.getTime() & 1) == 0) {
            throw new RuntimeException("Четная секунда");
        }
        return message;
    }
}