package by.it_akademy.fitness.configuration;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;

public class RabbitConfiguration {
    Logger logger = Logger.getLogger(RabbitConfiguration.class);


    // Define the connection factory to RabbitMQ
    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        // You can configure more properties of the connection factory here, such as credentials and other settings.
        return connectionFactory;
    }

    // Define a RabbitTemplate bean for sending and receiving messages
    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    // Define a queue
    @Bean
    public Queue myQueue1() {
        return new Queue("product_audit");
    }

    //объявляем контейнер, который будет содержать листенер для сообщений
//    @Bean V1
//    public SimpleMessageListenerContainer messageListenerContainer1() {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory());
//        container.setQueueNames("queue1");
//        container.setMessageListener(new MessageListener() {
//            //тут ловим сообщения из queue1
//            public void onMessage(Message message) {
//                logger.info("received from queue1 : " + new String(message.getBody()));
//            }
//        });
//        return container;
//    }
}

