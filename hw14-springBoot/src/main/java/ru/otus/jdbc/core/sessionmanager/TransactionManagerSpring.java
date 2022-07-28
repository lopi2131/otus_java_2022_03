package ru.otus.jdbc.core.sessionmanager;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionManagerSpring implements TransactionManager {

    @Override
    @Transactional
    public <T> T doInTransaction(TransactionAction<T> action) {
        return (T) action.get();
    }

}