package amandzhi.springjdbc;

import amandzhi.springjdbc.repository.AuthorRepositoryDaoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringjdbcApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(SpringjdbcApplication.class, args);
        AuthorRepositoryDaoImpl bean = context.getBean(AuthorRepositoryDaoImpl.class);
        System.out.println(bean.findAll().size());
    }
}