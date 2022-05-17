public class Main {
    public static void main(String[] args) {
        TestLoggingInterface testLoggingInterface = (TestLoggingInterface) Ioc.createTestLoggingClass();
        testLoggingInterface.calculation(6);
        testLoggingInterface.calculation(6, 5);
        testLoggingInterface.calculation(6, 5, 4);
    }
}
