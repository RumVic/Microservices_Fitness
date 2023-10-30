package by.it_akademy.fitness;

import by.it_akademy.fitness.configuration.RabbitConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(RabbitConfiguration.class)
public class ExampleConfiguration {
    public static  void main(String [] args){
        SpringApplication.run(ExampleConfiguration.class,args);
    }
}
