package africa.semicolon.BookFinder.service;

import africa.semicolon.BookFinder.exception.BooKNotFoundException;
import africa.semicolon.BookFinder.model.Book;
import africa.semicolon.BookFinder.model.BookTemp;
import africa.semicolon.BookFinder.model.Person;
import africa.semicolon.BookFinder.dtos.request.BookFinderRequest;
import africa.semicolon.BookFinder.dtos.response.BookFinderResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import africa.semicolon.BookFinder.config.ModelMapperConfig.*;

import java.util.List;

@Service
public class AppBookFinderService implements BookFinderService{
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private PersonService personService;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public BookFinderResponse searchBook(BookFinderRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://gutendex.com/books/?search= " + request.getTitle();
        BookFinderResponse response =
                restTemplate.getForEntity(url, BookFinderResponse.class).getBody();
        if (response == null){
            throw new BooKNotFoundException(request.getTitle()+" Not Found");
        }
        BookTemp book = response.getResults().get(0);
        authors(book.getAuthors());
        Book book1 = new Book();
        modelMapper.map(book1,BookTemp.class);
        saveBook(book1);
        addToReadingList(book1,request);
        return response;
    }
    private void authors(List<Person> person) {
        personService.savePerson(person);
    }
    private void addToReadingList(Book book,BookFinderRequest bookFinderRequest) {
       userService.addToBookToReadingList(book,bookFinderRequest);
    }
    private void saveBook(Book book) {
        bookService.save(book);
    }
}
