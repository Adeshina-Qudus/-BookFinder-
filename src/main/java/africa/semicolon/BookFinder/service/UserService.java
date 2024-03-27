package africa.semicolon.BookFinder.service;

import africa.semicolon.BookFinder.dtos.request.BookFinderRequest;
import africa.semicolon.BookFinder.dtos.request.SignInRequest;
import africa.semicolon.BookFinder.dtos.request.SignUpRequest;
import africa.semicolon.BookFinder.dtos.response.SignInResponse;
import africa.semicolon.BookFinder.dtos.response.SignUpResponse;
import africa.semicolon.BookFinder.model.Book;

public interface UserService {

    void addToBookToReadingList(Book book, BookFinderRequest bookFinderRequest);

    SignUpResponse signUp(SignUpRequest signUpRequest);

    SignInResponse signIn(SignInRequest signInRequest);
}
