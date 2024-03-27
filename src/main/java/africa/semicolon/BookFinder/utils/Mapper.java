package africa.semicolon.BookFinder.utils;

import africa.semicolon.BookFinder.dtos.request.SignUpRequest;
import africa.semicolon.BookFinder.model.User;

public class Mapper {

    public static User map(SignUpRequest signUpRequest) {
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setMail(signUpRequest.getMail());
        user.setPassword(signUpRequest.getPassword());
        return user;
    }
}
