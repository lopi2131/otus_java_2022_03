package ru.otus.jdbc.mapper;

import ru.otus.core.repository.DataTemplate;
import ru.otus.core.repository.DataTemplateException;
import ru.otus.core.repository.executor.DbExecutor;
import ru.otus.crm.model.Client;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Сохратяет объект в базу, читает объект из базы
 */
public class DataTemplateJdbc<T> implements DataTemplate<T> {

    private final DbExecutor dbExecutor;
    private final EntitySQLMetaData entitySQLMetaData;
    private final EntityClassMetaData entityClassMetaData;

    public DataTemplateJdbc(DbExecutor dbExecutor, EntitySQLMetaData entitySQLMetaData, EntityClassMetaData entityClassMetaData) {
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
        return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectByIdSql(), List.of(id), rs -> {
            try {
                if (rs.next()) {
                    return findObject(rs);
                }
                return null;
            } catch (SQLException e) {
                throw new DataTemplateException(e);
            }
        });
    }

    @Override
    public List<T> findAll(Connection connection) {
        return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectAllSql(), Collections.emptyList(), rs -> {
            var clientList = new ArrayList<T>();
            try {
                while (rs.next()) {
                    clientList.add(findObject(rs));
                }
                return clientList;
            } catch (SQLException e) {
                throw new DataTemplateException(e);
            }
        }).orElseThrow(() -> new RuntimeException("Unexpected error"));
    }

    @Override
    public long insert(Connection connection, T client) {
        try {
            List<Object> params = setParams(client, entityClassMetaData.getFieldsWithoutId());
            return dbExecutor.executeStatement(connection, entitySQLMetaData.getInsertSql(),
                    params);
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
    }

    @Override
    public void update(Connection connection, T client) {
        try {
            List<Object> params = setParams(client, entityClassMetaData.getAllFields());
            dbExecutor.executeStatement(connection, entitySQLMetaData.getUpdateSql(),
                    params);
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
    }

    private List<Object> setParams(T client, List<Field> fields) {
        try {
            List<Object> params = new ArrayList<>();
            for (Field field : fields) {
                field.setAccessible(true);
                params.add(field.get(client));
            }
            return params;
        } catch (IllegalAccessException e) {
            throw new DataTemplateException(e);
        }
    }

    private T findObject(ResultSet rs) {
        try {
            T obj = (T) entityClassMetaData.getConstructor().newInstance();
            List<Field> fields = entityClassMetaData.getAllFields();
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(obj, rs.getObject(field.getName()));
            }
            return obj;
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }

    }
}
