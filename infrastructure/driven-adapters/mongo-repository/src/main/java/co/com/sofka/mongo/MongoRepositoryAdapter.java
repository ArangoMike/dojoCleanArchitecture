package co.com.sofka.mongo;

import co.com.sofka.model.person.Person;
import co.com.sofka.mongo.document.PersonDocument;
import co.com.sofka.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;

import org.springframework.stereotype.Repository;
@Repository
public class MongoRepositoryAdapter extends AdapterOperations<Person, PersonDocument, String, MongoDBRepository>
// implements ModelRepository from domain
{

    public MongoRepositoryAdapter(MongoDBRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Person.class));
    }


}
