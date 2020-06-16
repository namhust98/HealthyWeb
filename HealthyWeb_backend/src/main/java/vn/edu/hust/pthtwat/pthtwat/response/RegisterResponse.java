package vn.edu.hust.pthtwat.pthtwat.response;

import vn.edu.hust.pthtwat.pthtwat.dto.UserDto;

public class RegisterResponse extends BaseResponse {
    private UserDto user;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
