import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication//(scanBasePackages = "builder, controller, enams, mapper, odto, service, storage.api, storage.entity")
@ComponentScan(basePackages = "java")
    public class AuditServiceApplication {
        public static void main(String[] args) {
            SpringApplication.run(AuditServiceApplication.class, args);
        }
    }
