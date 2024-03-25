package africa.semicolon.BookFinder.dtos.request;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
public class BookFinderRequest {
    private String title;
    private String mail;
}
