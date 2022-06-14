package ru.otus.processor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.model.Message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProcessorEvenSecondTest {
    @Test
    @DisplayName("Тестируем вызовы в четную секунду")
    void processEven() {
        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.getTime()).thenReturn(2L);
        var even = new ProcessorEvenSecond(currentTime);

        var message = new Message.Builder(1L)
                .build();
        var msg = Assertions.assertThrows(RuntimeException.class, () -> even.process(message)).getMessage();
        assertThat(msg).isEqualTo("Четная секунда");
    }

    @Test
    @DisplayName("Тестируем вызовы в нечетную секунду")
    void processOdd() {
        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.getTime()).thenReturn(1L);
        var even = new ProcessorEvenSecond(currentTime);

        var message = new Message.Builder(1L)
                .build();
        even.process(message);
        Assertions.assertDoesNotThrow(() -> even.process(message));
    }
}
