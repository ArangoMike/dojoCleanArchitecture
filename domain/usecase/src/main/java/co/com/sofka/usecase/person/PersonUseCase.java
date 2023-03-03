package co.com.sofka.usecase.person;

import co.com.sofka.model.events.gateways.EventsGateway;
import co.com.sofka.model.person.Person;
import co.com.sofka.model.person.gateways.PersonRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PersonUseCase {

    private final PersonRepository repository;

    private final EventsGateway bus;


    public Mono<Person> getPerson(String id){

        return  repository.getPerson(id)
                .map(person -> {
                    bus.publish(person);
                    return person;
                });
    }

    public Mono<Person> createPerson(Person person){
        bus.publish(person);
        return repository.createPerson(person);

    }

}
