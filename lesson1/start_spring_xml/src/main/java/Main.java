import executor.TestingApp;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        TestingApp console = context.getBean(TestingApp.class);
        console.start();
    }
}