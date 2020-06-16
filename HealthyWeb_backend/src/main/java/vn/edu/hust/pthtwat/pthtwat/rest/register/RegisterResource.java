package vn.edu.hust.pthtwat.pthtwat.rest.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hust.pthtwat.pthtwat.request.RegisterRequest;
import vn.edu.hust.pthtwat.pthtwat.response.RegisterResponse;
import vn.edu.hust.pthtwat.pthtwat.response.UserResponse;
import vn.edu.hust.pthtwat.pthtwat.service.RegisterService;
import vn.edu.hust.pthtwat.pthtwat.service.UserService;

@RestController
public class RegisterResource {

    @Autowired
    private UserService userService;

    @Autowired
    private RegisterService registerService;

    @PostMapping("api/v1/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        if (registerRequest.getType().equals("counselor")) {
            RegisterResponse response = registerService.registerCounselor(registerRequest);
            return ResponseEntity.ok().body(response);
        }
        RegisterResponse response = registerService.register(registerRequest);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("api/v1/confirm-user-counselor")
    public ResponseEntity<UserResponse> confirmRegister(@RequestParam(name = "email") String email,
                                                        @RequestParam(name = "code") String code) {
        UserResponse response = registerService.confirmRegisterCode(email, code);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("api/v1/forgot-password")
    public ResponseEntity<UserResponse> forgotPassword(@RequestParam(name = "email") String email) {
        UserResponse response = registerService.forgotPassword(email);

        return ResponseEntity.ok().body(response);
    }
}
