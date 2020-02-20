package br.com.springboot.app.resources;

import br.com.springboot.app.service.PersonService;
import br.com.springboot.app.model.Person;
import br.com.springboot.app.model.dto.PersonDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/people", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonResource {

    private final PersonService personService;

    @Autowired
    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    @ApiOperation(value = "Lista todas as pessoas cadastradas.")
    public ResponseEntity<Iterable<Person>> listAll() {
        return ResponseEntity.ok(personService.listAll());
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca pessoa por ID.")
    public ResponseEntity<Person> findById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(personService.findById(id));
    }

    @PostMapping
    @ApiOperation(value = "Cadastra pessoa.")
    public ResponseEntity<Void> save(@RequestBody PersonDTO personDTO) throws Exception {
        this.personService.save(personDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @ApiOperation(value = "Atualiza o cadastro de pessoa.")
    public ResponseEntity<Void> updateTask(@RequestBody PersonDTO personDTO) throws Exception {
        this.personService.updateTask(personDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deleta o cadastro de pessoa.")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        this.personService.delete(id);
        return ResponseEntity.ok().build();
    }

}
