package africa.semicolon.BookFinder.data.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class BookTemp {
    private Long id;
    private String title;
    private List<String> subjects;
    private List<Person> authors;
    private boolean copyright;
    private String media_type;
}
