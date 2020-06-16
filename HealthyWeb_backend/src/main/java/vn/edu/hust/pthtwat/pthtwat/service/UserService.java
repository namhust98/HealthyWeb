package vn.edu.hust.pthtwat.pthtwat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.hust.pthtwat.pthtwat.common.CommonService;
import vn.edu.hust.pthtwat.pthtwat.common.EmailTemplate;
import vn.edu.hust.pthtwat.pthtwat.dto.UserDto;
import vn.edu.hust.pthtwat.pthtwat.entity.User;
import vn.edu.hust.pthtwat.pthtwat.mapper.UserMapper;
import vn.edu.hust.pthtwat.pthtwat.repository.UserRepository;
import vn.edu.hust.pthtwat.pthtwat.request.RegisterRequest;
import vn.edu.hust.pthtwat.pthtwat.request.UserRequest;
import vn.edu.hust.pthtwat.pthtwat.response.RegisterResponse;
import vn.edu.hust.pthtwat.pthtwat.response.UserResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommonService commonService;

    @Autowired
    private EmailService emailService;


    public UserResponse getUsers(Pageable pageable) {
        UserResponse userResponse = new UserResponse();
        Page<User> userPage = userRepository.findAll(pageable);
        List<User> users = userPage.getContent();
        List<UserDto> userDto = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            userDto.add(userMapper.toDto(users.get(i)));
        }

        userResponse.setUserDtos(userDto);

        return userResponse;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Boolean checkEmailExist(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }


    public User refreshLastTimeActive(User user) {
        user.setLastActiveTime(new Date());

        return user;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByEmailAndTypeAndEnabelTrue(String email, String type) {
        return userRepository.findByEmailAndTypeAndEnabelTrue(email, type);
    }

    public User findByEmailAndEnabelTrue(String email) {
        return userRepository.findByEmailAndEnabelTrue(email);
    }

    public UserResponse changeInfor(UserRequest request, String accessToken) {
        UserResponse response = new UserResponse();
        // get old User Infor
        User user = userRepository.findByAccessToken(accessToken);
        if(user == null) {
            response.setIsSuccess(false);
            response.setMessage("User not exist");

            return response;
        }
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        userRepository.save(user);
        response.setUser(userMapper.toDto(user));

        return response;
    }

    public User getUserFromAccessToken(String accessToken) {
        User user = userRepository.getByAccessTokenAndEnabelTrue(accessToken);

        return user;
    }
}
