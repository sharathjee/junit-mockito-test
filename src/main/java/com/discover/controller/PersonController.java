package com.discover.controller;

import com.discover.model.Person;
import com.discover.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PersonController {

    @Autowired
    private PersonService service;

    @PostMapping("/create")
    public ResponseEntity<Person> createPerson(@RequestBody Person person){

        return ResponseEntity.ok().body( service.createPerson(person) );
    }

    @GetMapping("/get/{personId}")
    public ResponseEntity<Person> getPerson(@PathVariable Long personId){

        return ResponseEntity.ok().body(service.getPerson(personId) );
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Person>> getAllPersons(){
        return ResponseEntity.ok().body( service.getAllPerson() );
    }
}
