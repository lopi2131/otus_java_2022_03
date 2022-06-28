package ru.otus.jdbc.mapper;

import ru.otus.crm.model.Id;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {
    private final Class<T> clazz;

    public EntityClassMetaDataImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String getName() {
        return clazz.getSimpleName();
    }

    @Override
    public Constructor<T> getConstructor() throws NoSuchMethodException {
        return clazz.getDeclaredConstructor();
    }

    @Override
    public Field getIdField() {
        List<Field> fields = getAllFields();
        return fields.stream().filter(field -> field.isAnnotationPresent(Id.class)).findFirst().get();
    }

    @Override
    public List<Field> getAllFields() {
        return List.of(clazz.getDeclaredFields());
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        List<Field> fields = getAllFields();
        return fields.stream().filter(field -> !field.isAnnotationPresent(Id.class)).toList();
    }
}
