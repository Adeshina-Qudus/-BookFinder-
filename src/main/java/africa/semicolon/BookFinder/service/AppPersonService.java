package africa.semicolon.BookFinder.service;

import africa.semicolon.BookFinder.model.Person;
import africa.semicolon.BookFinder.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppPersonService implements PersonService{
    @Autowired
    private PersonRepository personRepository;
    @Override
    public void savePerson(List<Person> person) {
        personRepository.saveAll(person);
    }
}
