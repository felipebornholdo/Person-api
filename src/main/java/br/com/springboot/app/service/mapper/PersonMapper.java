package br.com.springboot.app.service.mapper;

import br.com.springboot.app.model.Person;
import br.com.springboot.app.model.dto.PersonDTO;

import java.util.Date;
import java.util.Objects;

public class PersonMapper {

    private PersonMapper() {
    }

    public static Person toPerson(PersonDTO personDTO) {
        final Person person = new Person();
        if (Objects.nonNull(personDTO.getId())) {
            person.setId(personDTO.getId());
        }
        person.setNome(personDTO.getNome());
        person.setCpf(personDTO.getCpf());
        person.setSexo(personDTO.getSexo());
        person.setEmail(personDTO.getEmail());
        person.setNacionalidade(personDTO.getNacionalidade());
        person.setNaturalidade(personDTO.getNaturalidade());
        person.setDataCadastro(personDTO.getDataCadastro());
        person.setDataNascimento(personDTO.getDataNascimento());
        person.setUltimaEdicao(new Date());

        return person;
    }
}
