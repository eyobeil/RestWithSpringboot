package com.cognizant.restspringboot.repository;

import com.cognizant.restspringboot.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends JpaRepository<Person ,Long> {
}
