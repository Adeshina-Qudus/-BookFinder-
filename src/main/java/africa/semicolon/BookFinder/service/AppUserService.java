package africa.semicolon.BookFinder.service;

import africa.semicolon.BookFinder.dtos.request.BookFinderRequest;
import africa.semicolon.BookFinder.dtos.request.SignInRequest;
import africa.semicolon.BookFinder.dtos.request.SignUpRequest;
import africa.semicolon.BookFinder.dtos.response.SignInResponse;
import africa.semicolon.BookFinder.dtos.response.SignUpResponse;
import africa.semicolon.BookFinder.exception.InvalidDetailsException;
import africa.semicolon.BookFinder.exception.PasswordDoesNotMatch;
import africa.semicolon.BookFinder.exception.UserAlreadyExistException;
import africa.semicolon.BookFinder.exception.UserDoesNotExistException;
import africa.semicolon.BookFinder.data.model.Book;
import africa.semicolon.BookFinder.data.model.User;
import africa.semicolon.BookFinder.data.repositories.UserRepository;
import africa.semicolon.BookFinder.utils.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserService{

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Override
    public void addToBookToReadingList(Book book, BookFinderRequest bookFinderRequest) {
        if (!userExist(bookFinderRequest.getMail())) throw new UserDoesNotExistException(
                bookFinderRequest.getMail()+" doesn't exist");
        User user = userRepository.findByMail(bookFinderRequest.getMail());
        user.getReadingList().add(book);
        userRepository.save(user);
    }

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
        response.setMessage(user.getName()+"Account Created");
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
        signInResponse.setMessage("u don login ");
        return signInResponse;
    }

    private boolean userExist(String mail) {
        User foundUser = userRepository.findByMail(mail);
        return foundUser != null;
    }
}
