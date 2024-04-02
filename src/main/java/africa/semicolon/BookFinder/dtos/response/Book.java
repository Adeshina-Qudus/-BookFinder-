package africa.semicolon.BookFinder.dtos.response;

import africa.semicolon.BookFinder.data.model.BookTemp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class Book {
    private int count;
    private String next;
    private String previous;
    private List<BookTemp> results;
}
