package com.discover;

import com.discover.controller.PersonController;
import com.discover.model.Person;
import com.discover.repository.PersonRepository;
import com.discover.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {

    @Mock
    private PersonService personService;
    @InjectMocks
    private PersonController personController;

    @Test
    void testCreatePerson(){
        Person person = new Person(101L, "kiran", "Hyd");

        given(personService.createPerson(person))
                .willReturn(person);

        var responseEntity = personController.createPerson(person);
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getBody()).isEqualTo(person);
    }

    @Test
    void testGetPerson(){

        Person person = new Person(101L, "kiran", "Hyd");
        given(personService.getPerson(101L))
                .willReturn(person);
        var p = personController.getPerson(101L);
        assertThat(p).isNotNull();
        assertThat(p instanceof ResponseEntity<Person>).isEqualTo(true);
        assertThat(p.getBody()).isEqualTo(person);
    }

    @Test
    void testGetAll(){

        Person person = new Person(101L, "kiran", "Hyd");
        Person person1 = new Person(102L, "karthi", "Bom");
        given(personService.getAllPerson())
                .willReturn(List.of(person, person1));
        var p = personController.getAllPersons();
        assertThat(p).isNotNull();
        assertThat(p instanceof ResponseEntity<List<Person>>).isEqualTo(true);
        assertThat(p.getBody().size()).isEqualTo(2);
    }

}
