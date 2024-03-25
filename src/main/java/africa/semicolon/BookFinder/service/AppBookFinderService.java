package africa.semicolon.BookFinder.service;

import africa.semicolon.BookFinder.exception.BooKNotFoundException;
import africa.semicolon.BookFinder.model.Book;
import africa.semicolon.BookFinder.model.User;
import africa.semicolon.BookFinder.dtos.request.BookFinderRequest;
import africa.semicolon.BookFinder.dtos.response.BookFinderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AppBookFinderService implements BookFinderService{
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Override
    public BookFinderResponse searchBook(BookFinderRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://gutendex.com/books/?search= " + request.getTitle();
        BookFinderResponse response =
                restTemplate.getForEntity(url, BookFinderResponse.class).getBody();
        if (response == null){
            throw new BooKNotFoundException(request.getTitle()+" Not Found");
        }
        Book book = saveBook(response.getResults().get(0));
        addToReadingList(book,request);
        return response;
    }
    private void addToReadingList(Book book,BookFinderRequest bookFinderRequest) {
       userService.addToBookToReadingList(book,bookFinderRequest);
    }
    private Book saveBook(Book book) {
        bookService.save(book);
        return book;
    }
}
