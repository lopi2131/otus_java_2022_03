import annotation.Log;

class TestLoggingImpl implements TestLoggingInterface {

    @Override
    @Log
    public void calculation(int param) {
        System.out.println(param);
    }

    @Override
    @Log
    public void calculation(int param1, int param2) {
        System.out.println(param1 + param2);
    }

    @Override
    @Log
    public void calculation(int param1, int param2, int param3) {
        System.out.println(param1 + param2 + param3);
    }
}
