package co.com.sofka.model.events.gateways;

import co.com.sofka.model.person.Person;
import reactor.core.publisher.Mono;

public interface EventsGateway {
    void publish(Person event);
}
