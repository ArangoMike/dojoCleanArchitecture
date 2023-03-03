package co.com.sofka.events;


import co.com.sofka.events.data.Notification;
import co.com.sofka.model.person.Person;
import co.com.sofka.serializer.JSONMapper;
import co.com.sofka.serializer.JSONMapperImpl;
import co.com.sofka.usecase.person.PersonUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Component
public class HandlerRegistryConfiguration {

    // see more at: https://reactivecommons.org/reactive-commons-java/#_handlerregistry_2

        public static final String EVENTS_QUEUE = "events.queue";
        public static final String GENERAL_QUEUE = "general.queue";
        private final Logger logger = Logger.getLogger("RabbitMqEventHandler");
        private final JSONMapper mapper = new JSONMapperImpl();

        private final PersonUseCase useCase;

        public HandlerRegistryConfiguration(PersonUseCase useCase) {
            this.useCase = useCase;
        }

    @RabbitListener(queues = EVENTS_QUEUE)
    public void listener(String message) throws ClassNotFoundException {


        Notification notification = Notification.from(message);
       if(notification.getType()
                .equals("co.com.alpha.bcb.model.post.events.PostCreated")){
            logger.info("1 entrando: " + notification.toString());
            /*var newPerson = Mono.just(notification.getBody());
            this.useCase.createPerson(Mono
                            .just(mapper.readFromJson(notification.getBody(),
                                    Person.class)))
                    .subscribe();
                    */

        }else{
            logger.info("1: " + "we currently don't have a listener for that event " +notification.toString());
        }

    }

    @RabbitListener(queues = GENERAL_QUEUE)
    public void listenerGeneral(String message) throws ClassNotFoundException {
        Notification notification = Notification.from(message);
        if(notification.getType()
                .equals("co.com.alpha.bcb.model.post.events.PostCreated")){
            logger.info("2:" + notification.toString());
            /*this.useCase.apply(Mono
                    .just((PostCreated) mapper.readFromJson(notification.getBody(),
                            PostCreated.class)))
                    .subscribe();*/
        }else{
            logger.info("2:" + "we currently don't have a listener for that event " +notification.toString());
        }
    }


    }