package africa.semicolon.BookFinder.controller;

import africa.semicolon.BookFinder.dtos.request.SignInRequest;
import africa.semicolon.BookFinder.dtos.request.SignUpRequest;
import africa.semicolon.BookFinder.dtos.response.ApiResponse;
import africa.semicolon.BookFinder.dtos.response.SignInResponse;
import africa.semicolon.BookFinder.dtos.response.SignUpResponse;
import africa.semicolon.BookFinder.exception.BookFinderException;
import africa.semicolon.BookFinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest){
        SignUpResponse response = new SignUpResponse();
        try {
            response.setMessage(userService.signUp(signUpRequest).getMessage());
            return new ResponseEntity<>(new ApiResponse(true,response),
                    HttpStatus.CREATED);
        }catch (BookFinderException exception){
            response.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,response),
                    HttpStatus.NOT_IMPLEMENTED);
        }
    }
    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest){
        SignInResponse response = new SignInResponse();
        try {
            response.setMessage(userService.signIn(signInRequest).getMessage());
            return new ResponseEntity<>(new ApiResponse(true,response),
                    HttpStatus.ACCEPTED);
        }catch (BookFinderException exception){
            response.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,response),
                    HttpStatus.NOT_ACCEPTABLE);
        }
    }
//    @GetMapping("/searchBook")
//    public ResponseEntity<?> searchBook(@RequestBody BookFinderRequest bookFinderRequest){
//        BookFinderResponse response = new BookFinderResponse();
//        try {
//            bookFinderService.searchBook(bookFinderRequest);
//            return new ResponseEntity<>(new ApiResponse(true,response),
//                    HttpStatus.FOUND);
//        }catch (BookFinderException exception){
//
//        }
//    }
}
