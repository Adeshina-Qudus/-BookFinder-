package africa.semicolon.BookFinder.dtos.response;

import africa.semicolon.BookFinder.model.Book;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class BookFinderResponse {
    private int count;
    private String next;
    private String previous;
    private List<Book> results;
}
