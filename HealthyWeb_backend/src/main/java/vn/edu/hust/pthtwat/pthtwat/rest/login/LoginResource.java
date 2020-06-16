package vn.edu.hust.pthtwat.pthtwat.rest.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hust.pthtwat.pthtwat.request.LogInRequest;
import vn.edu.hust.pthtwat.pthtwat.response.UserResponse;
import vn.edu.hust.pthtwat.pthtwat.service.LogInService;
import vn.edu.hust.pthtwat.pthtwat.service.UserService;

@RestController
public class LoginResource {

    @Autowired
    private UserService userService;

    @Autowired
    private LogInService logInService;

    @PostMapping("api/v1/sign-in")
    public ResponseEntity<UserResponse> signIn(@RequestBody LogInRequest request) {
        UserResponse response = logInService.signIn(request.getEmail(), request.getPassword(), request.getType());

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("api/v1/confirm-code-login")
    public ResponseEntity<UserResponse> confirmLoginCode(@RequestHeader(name = "Access-Token") String accessToken,
                                                         @RequestParam(name = "email") String email,
                                                         @RequestParam(name = "code") String code) {
        UserResponse response = logInService.confirmLoginCode(email, code);

        return ResponseEntity.ok().body(response);
    }

}
