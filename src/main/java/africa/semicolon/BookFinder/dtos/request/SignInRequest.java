package africa.semicolon.BookFinder.dtos.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
public class SignInRequest {
    private String mail;
    private String password;
}
