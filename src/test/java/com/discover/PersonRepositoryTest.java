package com.discover;

import com.discover.entity.PersonDto;
import com.discover.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(MockitoExtension.class)
public class PersonRepositoryTest {

    @Mock
    private PersonRepository personRepository;

    @Test
    void testExistsById(){

        given(personRepository.existsById(101L))
                .willReturn(true);

        var person = personRepository.existsById(101L);
        assertThat(person).isEqualTo(true);
    }

    @Test
    void testFindById(){

        PersonDto dto = PersonDto.builder().personCity("city").personName("name").id(101L).
                        build();
        given(personRepository.findById(101L))
                .willReturn(Optional.of(dto));

        var person = personRepository.findById(101L);
        assertThat(person).isNotNull();
        assertThat(person.get().getPersonCity()).isEqualTo("city");
        assertThat(person.get().getPersonName()).isEqualTo("name");
        assertThat(person.get().getId()).isNotNull();
    }

    @Test
    void testFindAll(){

        PersonDto dto = PersonDto.builder().personCity("city").personName("name").id(101L).
                build();
        PersonDto dto1 = PersonDto.builder().personCity("city1").personName("name1").id(102L).
                build();
        PersonDto dto2 = PersonDto.builder().personCity("city2").personName("name2").id(103L).
                build();
        given(personRepository.findAll())
                .willReturn(List.of(dto,dto1,dto2));

        var personList = personRepository.findAll();
        assertThat(personList).isNotNull();
        assertThat(personList.size()).isEqualTo(3);
    }

    @Test
    void testCreatePerson(){

        PersonDto dto = PersonDto.builder().personCity("city").personName("name").
                build();

        given(personRepository.save(dto))
                .willReturn(dto);

        var person = personRepository.save(dto);
        assertThat(person).isNotNull();
        assertThat(person.getPersonName()).isEqualTo("name");
        assertThat(person.getPersonCity()).isEqualTo("city");

    }
}
