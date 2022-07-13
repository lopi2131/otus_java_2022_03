package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;
import ru.otus.exception.ContextCreationException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) {
        try {
            checkConfigClass(configClass);
            var methods = getOrderedMethods(configClass);
            var constructor = configClass.getDeclaredConstructor();
            var configClazz = constructor.newInstance();

            for (Method method : methods) {
                var annotation = method.getAnnotation(AppComponent.class);
                checkComponent(annotation.name());
                var args = initParameters(method);
                var resultInvokeMethod = method.invoke(configClazz, args);
                appComponents.add(resultInvokeMethod);
                appComponentsByName.put(annotation.name(), resultInvokeMethod);
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
            System.out.println("Context cannot be configured");
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        List<C> components = new ArrayList<>();
        for (Object appComponent : appComponents) {
            if (componentClass.isAssignableFrom(appComponent.getClass())) {
                components.add((C) appComponent);
            }
        }
        checkComponent(components);
        return components.get(0);
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        var component = (C) appComponentsByName.get(componentName);
        checkComponent(component);
        return component;
    }

    private <C> void checkComponent(C component) {
        if (component == null) {
            throw new ContextCreationException("Component not found");
        } else if (appComponentsByName.containsKey(component)) {
            throw new ContextCreationException(String.format("Component with id: %s already exists", component));
        }
    }

    private <C> void checkComponent(List<C> components) {
        if (components.size() > 1) {
            throw new IllegalArgumentException("Duplicate components");
        } else if (components.isEmpty()) {
            throw new ContextCreationException("Component not found");
        }
    }

    private List<Method> getOrderedMethods(Class<?> configClass) {
        return Arrays.stream(configClass.getDeclaredMethods()).sorted(Comparator.comparing(method -> method.getAnnotation(AppComponent.class).order())).toList();
    }

    private Object[] initParameters(Method method) throws ClassNotFoundException {
        var i = 0;
        var args = new Object[method.getParameterTypes().length];
        for (Class component : method.getParameterTypes()) {
            var parameter = getAppComponent(component);
            args[i] = parameter;
            i++;
        }
        return args;
    }
}
