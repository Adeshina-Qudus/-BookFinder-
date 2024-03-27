package africa.semicolon.BookFinder.service;

import africa.semicolon.BookFinder.dtos.request.BookFinderRequest;
import africa.semicolon.BookFinder.dtos.request.SignInRequest;
import africa.semicolon.BookFinder.dtos.request.SignUpRequest;
import africa.semicolon.BookFinder.exception.InvalidDetailsException;
import africa.semicolon.BookFinder.exception.PasswordDoesNotMatch;
import africa.semicolon.BookFinder.exception.UserAlreadyExistException;
import africa.semicolon.BookFinder.exception.UserDoesNotExistException;
import africa.semicolon.BookFinder.data.model.Book;
import africa.semicolon.BookFinder.data.model.User;
import africa.semicolon.BookFinder.data.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserServiceTest {

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
    public void signInTest(){
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setName("tolu");
        signUpRequest.setMail("tolu63@gmail.com");
        signUpRequest.setPassword("ranking");
        signUpRequest.setConfirmPassword("ranking");
        userService.signUp(signUpRequest);
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setMail("tolu63@gmail.com");
        signInRequest.setPassword("ranking");
        assertThat(signInRequest).isNotNull();
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
    public void signInWithWrongPasswordTest(){
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setName("tolu");
        signUpRequest.setMail("tolu63@gmail.com");
        signUpRequest.setPassword("ranking");
        signUpRequest.setConfirmPassword("ranking");
        userService.signUp(signUpRequest);
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setMail("tolu63@gmail.com");
        signInRequest.setPassword("raning");
        assertThrows(InvalidDetailsException.class,()->userService.signIn(signInRequest));
    }
    @Test
    public void userReadingListTest(){
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
        userService.searchBook(request);

        User user = userRepository.findByMail("qudusa55@gmail.com");
        List<Book> books = user.getReadingList();
        assertEquals(1,books.size());
    }

}
