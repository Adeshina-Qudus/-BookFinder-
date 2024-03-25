package africa.semicolon.BookFinder.service;

import africa.semicolon.BookFinder.dtos.request.BookFinderRequest;
import africa.semicolon.BookFinder.exception.UserDoesNotExistException;
import africa.semicolon.BookFinder.model.Book;
import africa.semicolon.BookFinder.model.User;
import africa.semicolon.BookFinder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public void addToBookToReadingList(Book book, BookFinderRequest bookFinderRequest) {
        User user = userRepository.findByMail(bookFinderRequest.getMail());
        if (user == null){
            throw new UserDoesNotExistException(bookFinderRequest.getMail()+" doesn't exist");
        }
        user.getReadingList().add(book);
        userRepository.save(user);
    }
}
