package africa.semicolon.BookFinder.service;

import africa.semicolon.BookFinder.dtos.request.BookFinderRequest;
import africa.semicolon.BookFinder.dtos.request.SignInRequest;
import africa.semicolon.BookFinder.dtos.request.SignUpRequest;
import africa.semicolon.BookFinder.dtos.response.BookFinderResponse;
import africa.semicolon.BookFinder.dtos.response.SignInResponse;
import africa.semicolon.BookFinder.dtos.response.SignUpResponse;
import africa.semicolon.BookFinder.exception.InvalidDetailsException;
import africa.semicolon.BookFinder.exception.PasswordDoesNotMatch;
import africa.semicolon.BookFinder.exception.UserAlreadyExistException;
import africa.semicolon.BookFinder.exception.UserDoesNotExistException;
import africa.semicolon.BookFinder.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


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
    public void signUpTest(){
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setName("tolu");
        signUpRequest.setMail("tolu63@gmail.com");
        signUpRequest.setPassword("ranking");
        signUpRequest.setConfirmPassword("ranking");
        userService.signUp(signUpRequest);
        assertThat(signUpRequest).isNotNull();
    }

    @Test
    public void signUpWithExistingMailTest(){
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setName("tolu");
        signUpRequest.setMail("tolu63@gmail.com");
        signUpRequest.setPassword("ranking");
        signUpRequest.setConfirmPassword("ranking");
        userService.signUp(signUpRequest);
        assertThrows(UserAlreadyExistException.class,()-> userService.signUp(signUpRequest));
    }

    @Test
    public void signUpWithConfirmPasswordNotMatchingPasswordTest(){
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setName("tolu");
        signUpRequest.setMail("tolu63@gmail.com");
        signUpRequest.setPassword("ranking");
        signUpRequest.setConfirmPassword("rankin");
        assertThrows(PasswordDoesNotMatch.class,()->userService.signUp(signUpRequest));
    }

    @Test
    public void signInWithUnExistingMailTest(){
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setName("tolu");
        signUpRequest.setMail("tolu63@gmail.com");
        signUpRequest.setPassword("ranking");
        signUpRequest.setConfirmPassword("ranking");
        userService.signUp(signUpRequest);
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setMail("tol63@gmail.com");
        signInRequest.setPassword("ranking");
        assertThrows(UserDoesNotExistException.class,()->userService.signIn(signInRequest));
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
}
