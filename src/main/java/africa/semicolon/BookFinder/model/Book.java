package africa.semicolon.BookFinder.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
public class Book {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long Id;
    private String title;
    @ElementCollection
    private  List<String> subjects;
    @OneToMany
    private List<Person> authors;
    private boolean copyright;
    private String media_type;

}
