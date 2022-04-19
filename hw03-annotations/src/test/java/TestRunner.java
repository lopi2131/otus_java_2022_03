import ru.otus.annotations.AnnotationAnalyzer;

public class TestRunner {
    public static void main(String[] args) {
        var annotation = new AnnotationAnalyzer();
        annotation.test(AnnotationTest.class);
    }
}
