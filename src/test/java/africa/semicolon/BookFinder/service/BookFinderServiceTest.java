package africa.semicolon.BookFinder.service;

import africa.semicolon.BookFinder.dtos.request.BookFinderRequest;
import africa.semicolon.BookFinder.dtos.request.SignUpRequest;
import africa.semicolon.BookFinder.dtos.response.BookFinderResponse;
import africa.semicolon.BookFinder.dtos.response.SignUpResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class BookFinderServiceTest {

    @Autowired
    private BookFinderService bookFinderService;
    @Autowired
    private UserService userService;


    @Test
    public void bookFinderTest(){
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setName("name");
        signUpRequest.setMail("qudusa55@gmail.com");
        signUpRequest.setPassword("password");
        signUpRequest.setConfirmPassword("password");
        SignUpResponse signUpResponse = userService.signUp(signUpRequest);
        assertThat(signUpResponse).isNotNull();
        BookFinderRequest request = new BookFinderRequest();
        request.setMail("qudusa55@gmail.com");
        request.setTitle("ode");
        BookFinderResponse response = bookFinderService.searchBook(request);
        System.out.println(response);
        assertThat(response).isNotNull();
    }
}
