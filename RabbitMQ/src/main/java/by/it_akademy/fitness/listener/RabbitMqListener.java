//package by.it_akademy.fitness.listener;
//
//import org.springframework.amqp.rabbit.annotation.EnableRabbit;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//import org.apache.log4j.Logger;
////
////@EnableRabbit //нужно для активации обработки аннотаций @RabbitListener
////@Component
////public class RabbitMqListener {
////    Logger logger = Logger.getLogger(RabbitMqListener.class);
////
////    @RabbitListener(queues = "query-example-2")
////    public void worker3(String message) throws InterruptedException {
////        logger.info(" worker 2: " + message);
////    }
////}
////
