import annotation.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

class Ioc {

    private Ioc() {
    }

    static Object createTestLoggingClass() {
        InvocationHandler handler = new LogInvocationHandler(new TestLoggingImpl());
        return Proxy.newProxyInstance(TestLoggingImpl.class.getClassLoader(),
                TestLoggingImpl.class.getInterfaces(), handler);
    }

    static class LogInvocationHandler implements InvocationHandler {
        private final Object testLoggingClass;

        LogInvocationHandler(Object testLoggingClass) {
            this.testLoggingClass = testLoggingClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            var logMethod = testLoggingClass.getClass().getMethod(method.getName(), method.getParameterTypes()).isAnnotationPresent(Log.class);
            if (logMethod) {
                System.out.println("executed method: " + method.getName() + " param: " + Arrays.toString(args));
            }
            return method.invoke(testLoggingClass, args);
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                    "myClass=" + testLoggingClass +
                    '}';
        }
    }
}