package africa.semicolon.BookFinder.service;

import africa.semicolon.BookFinder.data.model.BookTemp;
import africa.semicolon.BookFinder.data.model.Person;
import africa.semicolon.BookFinder.dtos.request.BookFinderRequest;
import africa.semicolon.BookFinder.dtos.request.SignInRequest;
import africa.semicolon.BookFinder.dtos.request.SignUpRequest;
import africa.semicolon.BookFinder.dtos.response.BookFinderResponse;
import africa.semicolon.BookFinder.dtos.response.SignInResponse;
import africa.semicolon.BookFinder.dtos.response.SignUpResponse;
import africa.semicolon.BookFinder.exception.*;
import africa.semicolon.BookFinder.data.model.Book;
import africa.semicolon.BookFinder.data.model.User;
import africa.semicolon.BookFinder.data.repositories.UserRepository;
import africa.semicolon.BookFinder.utils.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AppUserService implements UserService{

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookService bookService;
    @Autowired
    private PersonService personService;
    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        SignUpResponse response = new SignUpResponse();
        if (userExist(signUpRequest.getMail())) throw new UserAlreadyExistException(
                "Account with {"+signUpRequest.getMail()+"} already exist"
        );
//        User user = new User();
//        modelMapper.map(user,SignUpRequest.class);
        if (!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())) throw new PasswordDoesNotMatch(
                "Password Doesn't Match"
        );
        User user = Mapper.map(signUpRequest);
        userRepository.save(user);
        response.setMessage("Account for {"+user.getMail()+"} created");
        return response;
    }
    @Override
    public SignInResponse signIn(SignInRequest signInRequest){
        SignInResponse signInResponse = new SignInResponse();
        if (!userExist(signInRequest.getMail())) throw new UserDoesNotExistException(
                "Account with this {"+signInRequest.getMail()+"} Does not exist"
        );
        User user = userRepository.findByMail(signInRequest.getMail());
        if (!user.getPassword().equals(signInRequest.getPassword())){
            throw new InvalidDetailsException("Invalid Details");
        }
        user.setLocked(false);
        signInResponse.setMessage("You have successfully signed in");
        return signInResponse;
    }

    @Override
    public BookFinderResponse searchBook(BookFinderRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://gutendex.com/books/?search= " + request.getTitle();
        BookFinderResponse response =
                restTemplate.getForEntity(url, BookFinderResponse.class).getBody();
        if (response == null){
            throw new BooKNotFoundException(request.getTitle()+" Not Found");}
        BookTemp bookTemp = response.getResults().get(0);
        authors(bookTemp.getAuthors());
//        Book book = new Book();
//        modelMapper.map(book,BookTemp.class);
//        saveBook(book);
        Book book = Mapper.mapBook(bookTemp);
        saveBook(book);
        addToBookToReadingList(book,request);
        return response;
    }
    private void addToBookToReadingList(Book book, BookFinderRequest bookFinderRequest) {
        if (!userExist(bookFinderRequest.getMail())) throw new UserDoesNotExistException(
                bookFinderRequest.getMail()+" doesn't exist");
        User user = userRepository.findByMail(bookFinderRequest.getMail());
        user.getReadingList().add(book);
        userRepository.save(user);
    }
    private void authors(List<Person> person) {
        personService.savePerson(person);
    }
    private void saveBook(Book book) {
        bookService.save(book);
    }
    private boolean userExist(String mail) {
        User foundUser = userRepository.findByMail(mail);
        return foundUser != null;
    }
}
