package africa.semicolon.BookFinder.service;

import africa.semicolon.BookFinder.data.model.Person;

import java.util.List;

public interface PersonService {
    void savePerson(List<Person> person);
}
