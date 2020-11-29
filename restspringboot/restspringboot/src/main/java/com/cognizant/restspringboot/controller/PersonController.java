package com.cognizant.restspringboot.controller;

import com.cognizant.restspringboot.data.vo.PersonVo;
import com.cognizant.restspringboot.exception.ResourceNotFoundException;
import com.cognizant.restspringboot.model.DozerConverter;
import com.cognizant.restspringboot.model.Person;
import com.cognizant.restspringboot.repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private IPersonRepository personRepository;

    public PersonController(@Autowired IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping(value = "/add", produces = {"application/json","application/xml"},
    consumes = {"application/json","application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    public PersonVo create(@RequestBody @Validated PersonVo personVo) {
        Person person = DozerConverter.parseObject(personVo, Person.class);
        return DozerConverter.parseObject(personRepository.save(person), PersonVo.class);
    }

    @GetMapping(value = "person/{id}",produces = {"application/json","application/xml"})
    public PersonVo findOne(@PathVariable("id") Long id) {
        Person entity=personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no records found for this Id"));
        return DozerConverter.parseObject(entity, PersonVo.class);
    }

    @GetMapping( produces = {"application/json","application/xml"})
    public List<PersonVo> findAll() {
        List<Person> people = personRepository.findAll();
        return DozerConverter.parseObject(people, PersonVo.class);
    }

    @PutMapping(value = "/person/{id}",
            produces = {"application/json","application/xml"},
            consumes = {"application/json","application/xml"})
    public ResponseEntity<PersonVo> update(@PathVariable(value = "id") Long id,
                                                   @Validated @RequestBody PersonVo personVo) throws ResourceNotFoundException {
        Person person= personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));

        Person personUpdated=DozerConverter.parseObject(personVo, Person.class);
        person.setFirstName(personUpdated.getFirstName());
        person.setAddress(personUpdated.getAddress());
        person.setGender(personUpdated.getGender());
        person.setLastName(personUpdated.getLastName());
        return ResponseEntity.ok(DozerConverter.parseObject(personRepository.save(person), PersonVo.class));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        personRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
