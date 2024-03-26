package africa.semicolon.BookFinder.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpRequest {
    private String name;
    private String mail;
    private String password;
    private String confirmPassword;
}
