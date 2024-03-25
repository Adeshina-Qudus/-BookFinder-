package africa.semicolon.BookFinder.service;

import africa.semicolon.BookFinder.dtos.request.BookFinderRequest;
import africa.semicolon.BookFinder.dtos.response.BookFinderResponse;

public interface BookFinderService {
    BookFinderResponse searchBook(BookFinderRequest request);
}
