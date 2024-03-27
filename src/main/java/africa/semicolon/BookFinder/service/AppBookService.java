package africa.semicolon.BookFinder.service;

import africa.semicolon.BookFinder.data.model.Book;
import africa.semicolon.BookFinder.data.model.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppBookService implements BookService{

    @Autowired
    private BookRepository bookRepository;
    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }
}
