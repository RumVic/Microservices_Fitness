package by.it_akademy.fitness.listeners;

import by.it_akademy.fitness.idto.InputDTO;
import by.it_akademy.fitness.service.api.IAuditService;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@EnableRabbit //нужно для активации обработки аннотаций @RabbitListener
@Component
public class RabbitMqListener {
    //Logger logger = Logger.getLogger(RabbitMqListener.class);

    private final IAuditService auditService;

    @Autowired
    public RabbitMqListener( IAuditService auditService) {//entityType Logger logger,
        //this.logger = logger;
        this.auditService = auditService;
    }

    @RabbitListener(queues = "product_audit")
    public void listenProduct(@RequestBody InputDTO inputDto)  {
        auditService.create(inputDto);
    }
}

