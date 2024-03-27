package africa.semicolon.BookFinder.service;

import africa.semicolon.BookFinder.dtos.request.BookFinderRequest;
import africa.semicolon.BookFinder.dtos.request.SignInRequest;
import africa.semicolon.BookFinder.dtos.request.SignUpRequest;
import africa.semicolon.BookFinder.dtos.response.BookFinderResponse;
import africa.semicolon.BookFinder.data.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
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
    @Autowired
    private UserRepository userRepository;
    @BeforeEach
    public void deleteAll(){
        userRepository.deleteAll();
    }
    @Test
    public void bookFinderTest(){
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setName("name");
        signUpRequest.setMail("qudusa55@gmail.com");
        signUpRequest.setPassword("password");
        signUpRequest.setConfirmPassword("password");
        userService.signUp(signUpRequest);

        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setMail("qudusa55@gmail.com");
        signInRequest.setPassword("password");
        userService.signIn(signInRequest);

        BookFinderRequest request = new BookFinderRequest();
        request.setMail("qudusa55@gmail.com");
        request.setTitle("romeo and juliet");
        BookFinderResponse response = bookFinderService.searchBook(request);

        assertThat(response).isNotNull();
    }

    @Test
    public void userReadingListTest(){

    }
}
