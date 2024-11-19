package com.discover.repository;

import com.discover.entity.PersonDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonDto, Long> {
    boolean existsById(Long Id);
}
