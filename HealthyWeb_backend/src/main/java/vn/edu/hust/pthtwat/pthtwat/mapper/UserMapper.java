package vn.edu.hust.pthtwat.pthtwat.mapper;

import org.springframework.stereotype.Service;
import vn.edu.hust.pthtwat.pthtwat.dto.BaseDto;
import vn.edu.hust.pthtwat.pthtwat.dto.UserDto;
import vn.edu.hust.pthtwat.pthtwat.entity.BaseEntity;
import vn.edu.hust.pthtwat.pthtwat.entity.User;

@Service
public class UserMapper {

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setType(user.getType());

        return userDto;
    }

    public User toEntity(BaseDto D) {
        return null;
    }
}
