package vn.edu.hust.pthtwat.pthtwat.rest.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hust.pthtwat.pthtwat.dto.UserDto;
import vn.edu.hust.pthtwat.pthtwat.entity.User;
import vn.edu.hust.pthtwat.pthtwat.request.RegisterRequest;
import vn.edu.hust.pthtwat.pthtwat.request.UserRequest;
import vn.edu.hust.pthtwat.pthtwat.response.RegisterResponse;
import vn.edu.hust.pthtwat.pthtwat.response.UserResponse;
import vn.edu.hust.pthtwat.pthtwat.service.UserService;

import java.util.List;

@RestController
public class UserResource {
    private static final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    private UserService userService;

    @GetMapping("api/v1/getUsers")
    public UserResponse getUsers(@RequestHeader(name = "Access-Token") String accessToken,
                                 @RequestParam(name = "page", defaultValue = "1") int page,
                                 @RequestParam(name = "size", defaultValue = "10") int size) {
        //TODO check accessToken
        Pageable pageable = new PageRequest(page - 1, size);

        return userService.getUsers(pageable);
    }

    @PutMapping("api/v1/changeInfor")
    public ResponseEntity<UserResponse> changeInfor(@RequestHeader(name = "Access-Token") String accessToken,
                                    @RequestBody UserRequest request) {
        UserResponse response = userService.changeInfor(request, accessToken);
        return ResponseEntity.ok().body(response);
    }

}
