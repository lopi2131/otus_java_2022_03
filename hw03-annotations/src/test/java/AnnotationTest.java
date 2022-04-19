import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class AnnotationTest {

    @Test
    public static void test1() {
        System.out.println("Test1");
    }

    @Test
    public static void test2() {
        System.out.println("Test2");
    }

    @Test
    public static void test3() {
        System.out.println("Test3");
    }

    @After
    public static void after() {
        System.out.println("After");
    }

    @Before
    public static void before() {
        System.out.println("Before");
    }
}
