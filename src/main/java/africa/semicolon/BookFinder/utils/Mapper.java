package africa.semicolon.BookFinder.utils;

import africa.semicolon.BookFinder.dtos.request.SignUpRequest;
import africa.semicolon.BookFinder.data.model.Book;
import africa.semicolon.BookFinder.data.model.BookTemp;
import africa.semicolon.BookFinder.data.model.User;

public class Mapper {

    public static User map(SignUpRequest signUpRequest) {
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setMail(signUpRequest.getMail());
        user.setPassword(signUpRequest.getPassword());
        return user;
    }

    public static Book mapBook(BookTemp bookTemp) {
        Book  book = new Book();
        book.setTitle(bookTemp.getTitle());
        book.setAuthors(bookTemp.getAuthors());
        book.setCopyright(bookTemp.isCopyright());
        book.setSubjects(bookTemp.getSubjects());
        book.setMedia_type(bookTemp.getMedia_type());
        return book;
    }
}
