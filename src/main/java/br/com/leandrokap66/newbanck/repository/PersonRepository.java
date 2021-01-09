package br.com.leandrokap66.newbanck.repository;

import br.com.leandrokap66.newbanck.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByName(String var1);
}
