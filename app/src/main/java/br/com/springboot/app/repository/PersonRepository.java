package br.com.springboot.app.repository;

import br.com.springboot.app.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

}
