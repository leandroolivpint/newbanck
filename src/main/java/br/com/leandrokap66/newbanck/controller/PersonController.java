package br.com.leandrokap66.newbanck.controller;

import br.com.leandrokap66.newbanck.exception.ResourceNotFoundException;
import br.com.leandrokap66.newbanck.model.Person;
import br.com.leandrokap66.newbanck.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    // get all people
    @GetMapping("/person")
    public List<Person> getAllPerson(){
        return personRepository.findAll();
    }

    // add new person
    @PostMapping("/person")
    public Person createPerson(@Valid @RequestBody Person person) {
            return personRepository.save(person);
    }
    //get person by id
    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Person person = personRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Person not found" + id)
        );
        return ResponseEntity.ok().body(person);
    }

    // update person
    @PutMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable(value = "id") long id, @RequestBody Person personDetails) throws ResourceNotFoundException {
        Person person = personRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Person not found" + id)
        );
        person.setCpf(personDetails.getCpf());
        person.setDateOfBirth(personDetails.getDateOfBirth());
        person.setName(personDetails.getName());
        personRepository.save(person);
        return  ResponseEntity.ok().body(person);

    }
    // delete person
    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Person person = personRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Person not found" + id)
        );
        personRepository.delete(person);
    }

    @RequestMapping(value = "/person/findByName", method = RequestMethod.GET)
    public ResponseEntity<Person> getPersonByName(@RequestParam("name") String name) throws ResourceNotFoundException{
        Person person = personRepository.findByName(name).orElseThrow(()
                -> new ResourceNotFoundException("Person not found" + name)
        );
        return  ResponseEntity.ok().body(person);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
