package co.com.sofka.api;

import co.com.sofka.model.person.Person;
import co.com.sofka.usecase.person.PersonUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;


@Component
@RequiredArgsConstructor
public class Handler {
  private  final PersonUseCase useCase;

    static Mono<ServerResponse> notFound = ServerResponse.notFound().build();

    public Mono<ServerResponse> getPerson(ServerRequest serverRequest) {

        String id = serverRequest.pathVariable("id");
        Mono<Person> itemMono = useCase.getPerson(id);
        return itemMono.flatMap(item ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(fromValue(item)))
                .switchIfEmpty(notFound);

    }

    public Mono<ServerResponse> createPerson(ServerRequest serverRequest) {
        var newPerson = serverRequest.bodyToMono(Person.class);

        return newPerson.flatMap(person ->
                ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(useCase.createPerson(person), Person.class));
    }


}
