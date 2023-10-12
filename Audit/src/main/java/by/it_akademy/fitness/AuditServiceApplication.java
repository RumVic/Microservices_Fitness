package by.it_akademy.fitness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
//(scanBasePackages = "builder, controller, enams, mapper, odto, service, storage.api, storage.entity")
@SpringBootApplication
@ComponentScan(basePackages = "by.it_akademy.fitness" )
//was caused of  access problem.
// codnt  find my endpoint of controller because veseable problem
// org.springframework.web.servlet.handler looking for endpoint in resources
// call ResourceHttpRequestHandler
//org.springframework.web.servlet.DispatcherServlet : Completed 404 NOT_FOUND
// you need ScanComponents in package where your @SpringBootApplication lie
@EntityScan(basePackages = {"org.springframework.security.core.userdetails","by.it_akademy.fitness.storage.entity"})//"
    public class AuditServiceApplication {
        public static void main(String[] args)
        {
            SpringApplication.run(AuditServiceApplication.class, args);
        }
    }
