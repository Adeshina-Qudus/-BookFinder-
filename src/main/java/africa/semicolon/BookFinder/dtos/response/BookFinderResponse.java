package africa.semicolon.BookFinder.dtos.response;

import africa.semicolon.BookFinder.data.model.BookTemp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@ToString
public class BookFinderResponse {
    private int count;
    private String next;
    private String previous;
    private List<BookTemp> results;
}
