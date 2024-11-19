package com.discover;

import com.discover.entity.PersonDto;
import com.discover.model.Person;
import com.discover.model.PersonMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PersonTest {

	@Test
	void contextLoads() {
	}

	@Test
	void testPersonToPersonDto(){

		Person person = new Person(101L, "kiran", "kumar");
		PersonDto personDto = PersonMapper.INSTANCE.personToPersonDto(person);

		assertThat(personDto).isNotNull();
		assertThat(personDto.getId()).isEqualTo(101L);
		assertThat(personDto.getPersonName()).isEqualTo("kiran");
		assertThat(personDto.getPersonCity()).isEqualTo("kumar");
	}

	@Test
	void testPersonDtoToPerson(){

		PersonDto dto = new PersonDto(101L, "kiran", "kumar");
		Person person = PersonMapper.INSTANCE.personDtoToPerson(dto);

		assertThat(person).isNotNull();
		assertThat(person.getId()).isEqualTo(101L);
		assertThat(person.getPersonName()).isEqualTo("kiran");
		assertThat(person.getPersonCity()).isEqualTo("kumar");
	}


}
