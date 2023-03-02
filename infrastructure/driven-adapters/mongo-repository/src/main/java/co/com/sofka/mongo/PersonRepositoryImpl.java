package co.com.sofka.mongo;

import co.com.sofka.model.person.Person;
import co.com.sofka.model.person.gateways.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {

    private final MongoRepositoryAdapter dto;

    private MongoDBRepository repository;

    @Override
    public Mono<Person> getPerson(String id) {
        return dto.findById(id);
    }

    @Override
    public Mono<Person> createPerson(Person person) {
        return dto.save(person);
    }

}
