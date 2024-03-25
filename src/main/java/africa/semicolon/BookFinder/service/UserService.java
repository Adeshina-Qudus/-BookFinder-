package africa.semicolon.BookFinder.service;

import africa.semicolon.BookFinder.dtos.request.BookFinderRequest;
import africa.semicolon.BookFinder.model.Book;

public interface UserService {

    void addToBookToReadingList(Book book, BookFinderRequest bookFinderRequest);
}
