package com.discover;

import com.discover.entity.PersonDto;
import com.discover.model.Person;
import com.discover.model.PersonMapper;
import com.discover.repository.PersonRepository;
import com.discover.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;
    @Test
    void testGetAllPersons(){
        PersonDto person = new PersonDto(101L,"Ravi Kiran", "Hyderabad");
        PersonDto person1 = new PersonDto(102L,"Ranjith Kumar", "Hyderabad");

        given(personRepository.findAll())
                .willReturn(List.of(person, person1));
        var personList = personService.getAllPerson();
        assertThat(personList).isNotNull();
        assertThat(personList.size()).isEqualTo(2);
        assertThat(personList.get(0) instanceof Person);
        assertThat(personList.get(1) instanceof Person);
    }

    @Test
    void testGetPerson(){

        PersonDto person = new PersonDto(101L,"Ravi Kiran", "Hyderabad");

        given(personRepository.findById(101L))
                .willReturn(Optional.of(person));

        var p = personService.getPerson(101L);

        assertThat( p.getPersonCity() ).isEqualTo("Hyderabad");
        assertThat( p.getPersonName() ).isEqualTo("Ravi Kiran");
        assertThat( p.getId() ).isEqualTo( 101L );
    }

    @Test
    public void testCreatePerson(){

        Person p = new Person(null, "name", "city");

        PersonDto personDto = PersonDto.builder()
                                                .personName("name")
                                                    .personCity("city").build();

        given( personRepository.save(PersonMapper.INSTANCE.personToPersonDto(p)))
                .willReturn(personDto);
        var person = personService.createPerson(p);
        assertThat(person).isNotNull();
        assertThat(person.getPersonName()).isEqualTo("name");
        assertThat(person.getPersonCity()).isEqualTo("city");
    }
}
