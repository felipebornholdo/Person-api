package br.com.springboot.app.service;

import br.com.springboot.app.service.mapper.PersonMapper;
import br.com.springboot.app.model.Person;
import br.com.springboot.app.model.dto.PersonDTO;
import br.com.springboot.app.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Iterable<Person> listAll() {
        return personRepository.findAll();
    }

    public Person findById(Long id) throws Exception {
        try {
            return this.personRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new Exception("Erro ao buscar cadastro.");
        }
    }

    public void save(PersonDTO personDTO) throws Exception {
        try {
            Person person = PersonMapper.toPerson(personDTO);
            this.personRepository.save(person);
        } catch (Exception e) {
            throw new Exception("Erro ao efetuar o cadastro.");
        }
    }

    public void updateTask(PersonDTO personDTO) throws Exception {
        try {
            Person person = PersonMapper.toPerson(personDTO);
            this.personRepository.save(person);
        } catch (Exception e) {
            throw new Exception("Erro ao atualizar o cadastro.");
        }
    }

    public void delete(Long id) throws Exception {
        try {
            this.personRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Erro ao deletar o cadastro");
        }
    }
}
