package ru.otus.processor;

import java.time.Instant;

public class CurrentTime implements DateTimeProvider{
    @Override
    public Long getTime() {
        return Instant.now().getEpochSecond();
    }
}