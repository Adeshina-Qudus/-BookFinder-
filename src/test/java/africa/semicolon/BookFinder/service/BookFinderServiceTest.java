package africa.semicolon.BookFinder.service;

import africa.semicolon.BookFinder.dtos.request.BookFinderRequest;
import africa.semicolon.BookFinder.dtos.response.BookFinderResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class BookFinderServiceTest {

    @Autowired
    private BookFinderService bookFinderService;


    @Test
    public void bookFinderTest(){
        BookFinderRequest request = new BookFinderRequest();
        request.setTitle("ode");
        BookFinderResponse response = bookFinderService.searchBook(request);
        System.out.println(response);
        assertThat(response).isNotNull();
    }
}
