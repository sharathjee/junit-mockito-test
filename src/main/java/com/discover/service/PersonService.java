package com.discover.service;

import com.discover.entity.PersonDto;
import com.discover.model.Person;
import com.discover.model.PersonMapper;
import com.discover.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository){
        this.repository = repository;
    }

    public List<Person> getAllPerson(){
        return repository
                .findAll()
                .stream()
                .map( PersonMapper.INSTANCE::personDtoToPerson )
                .collect(Collectors.toList());
    }

    public Person getPerson(Long id){
        return repository
                .findById(id)
                .map(PersonMapper.INSTANCE::personDtoToPerson)
                .orElseThrow( () -> { throw new NoSuchElementException("person does not exist with id : "+id );
                });
    }

    public Person createPerson(Person person){
        PersonDto dto = repository
                .save(PersonMapper.INSTANCE.personToPersonDto(person));
        return PersonMapper.INSTANCE.personDtoToPerson(dto);
    }
}
