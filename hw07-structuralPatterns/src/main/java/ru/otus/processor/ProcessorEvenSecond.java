package ru.otus.processor;

import ru.otus.model.Message;

public class ProcessorEvenSecond implements Processor  {
    private final CurrentTime currentTime;

    public ProcessorEvenSecond(CurrentTime currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public Message process(Message message) {
        if ((currentTime.getTime() & 1) == 0) {
            throw new RuntimeException("Четная секунда");
        }
        return message;
    }
}