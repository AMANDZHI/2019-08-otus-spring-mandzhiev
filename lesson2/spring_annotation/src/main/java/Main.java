import config.AppConfig;
import executor.TestingApp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@ComponentScan(basePackages = {"dao", "executor", "service", "util"})
@Configuration
@PropertySource("classpath:application.properties")
@Import(AppConfig.class)
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        TestingApp console = context.getBean(TestingApp.class);
        console.start();
    }
}