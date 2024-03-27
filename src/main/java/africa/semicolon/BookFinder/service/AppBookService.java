package africa.semicolon.BookFinder.service;

import africa.semicolon.BookFinder.model.Book;
import africa.semicolon.BookFinder.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppBookService implements BookService{

    @Autowired
    private BookRepository bookRepository;
    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }
}
