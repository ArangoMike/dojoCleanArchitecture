package co.com.sofka.events;

import co.com.sofka.events.data.Notification;
import co.com.sofka.model.events.gateways.EventsGateway;

import co.com.sofka.model.person.Person;
import co.com.sofka.serializer.JSONMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ReactiveEventsGateway implements EventsGateway {

    public static final String EXCHANGE = "core-posts-events";
    public static final String ROUTING_KEY = "events.routing.key";
    private final RabbitTemplate rabbitTemplate;
    private final JSONMapper serializer;


    public ReactiveEventsGateway(RabbitTemplate rabbitTemplate,  JSONMapper serializer) {
        this.serializer = serializer;
        this.rabbitTemplate = rabbitTemplate;
    }
    @Override
    public void publish(Person event){
        log.info("entre aqui");
        var notification = new Notification(
                event.getClass().getTypeName(),
                serializer.writeToJson(event)
        );
        rabbitTemplate.convertAndSend(
                this.EXCHANGE, this.ROUTING_KEY, notification.serialize().getBytes()
        );
    }
}
