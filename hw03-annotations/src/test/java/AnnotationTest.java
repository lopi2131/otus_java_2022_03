import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import static org.assertj.core.api.Fail.fail;

public class AnnotationTest {

    @Test
    void test1() {
        System.out.println("Test1");
    }

    @Test
    void test2() {
        System.out.println("Test2");
    }

    @Test
    void test3() {
        fail("Тест не прошел!");
    }

    @After
    void after() {
        System.out.println("After");
    }

    @Before
    void before() {
        System.out.println("Before");
    }
}
