package co.com.sofka.usecase.person;

import co.com.sofka.model.person.Person;
import co.com.sofka.model.person.gateways.PersonRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PersonUseCase {

    private final PersonRepository repository;


    public Mono<Person> getPerson(String id){
        return  repository.getPerson(id);
    }

    public Mono<Person> createPerson(Person person){
        return repository.createPerson(person);
    }

}
