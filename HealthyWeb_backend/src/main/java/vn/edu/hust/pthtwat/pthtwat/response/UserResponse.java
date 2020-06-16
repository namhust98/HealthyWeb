package vn.edu.hust.pthtwat.pthtwat.response;

import vn.edu.hust.pthtwat.pthtwat.dto.UserDto;
import vn.edu.hust.pthtwat.pthtwat.entity.User;

import java.util.List;

public class UserResponse extends BaseResponse {
    private UserDto user;
    private List<UserDto> userDtos;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<UserDto> getUserDtos() {
        return userDtos;
    }

    public void setUserDtos(List<UserDto> userDtos) {
        this.userDtos = userDtos;
    }
}
