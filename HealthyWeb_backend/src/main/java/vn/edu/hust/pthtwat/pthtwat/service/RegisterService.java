package vn.edu.hust.pthtwat.pthtwat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hust.pthtwat.pthtwat.common.CommonService;
import vn.edu.hust.pthtwat.pthtwat.common.EmailTemplate;
import vn.edu.hust.pthtwat.pthtwat.dto.UserDto;
import vn.edu.hust.pthtwat.pthtwat.entity.User;
import vn.edu.hust.pthtwat.pthtwat.mapper.UserMapper;
import vn.edu.hust.pthtwat.pthtwat.request.RegisterRequest;
import vn.edu.hust.pthtwat.pthtwat.response.RegisterResponse;
import vn.edu.hust.pthtwat.pthtwat.response.UserResponse;

@Service
public class RegisterService {

    @Autowired
    private UserService userService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmailService emailService;

    // register user
    public RegisterResponse register(RegisterRequest request) {
        RegisterResponse response = new RegisterResponse();
        String checkValid = this.validateRegisterRequest(request);
        if (!checkValid.equals("valid")) {
            response.setIsSuccess(false);
            response.setMessage(checkValid);

            return response;
        }
        User user = new User();
        String accessToken = commonService.genStringRandom();
        String hashPass = commonService.getSha256hex(request.getPassword());
        user.setAccessToken(accessToken);
        user.setEmail(request.getEmail());
        user.setPassword(hashPass);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setType(request.getType());
        user.setEnabel(true);
        user.setStatus(0);
        userService.save(user);

        UserDto userDto = userMapper.toDto(user);
        userDto.setAccessToken(accessToken);

        response.setUser(userDto);

        return response;
    }

    // register counselor
    public RegisterResponse registerCounselor(RegisterRequest request) {
        RegisterResponse response = new RegisterResponse();
        String checkValid = this.validateRegisterRequest(request);
        if (!checkValid.equals("valid")) {
            response.setIsSuccess(false);
            response.setMessage(checkValid);

            return response;
        }
        User user = new User();
        String accessToken = commonService.genStringRandom();
        String hashPass = commonService.getSha256hex(request.getPassword());
        String randomCode = commonService.genCodeRandom();
        user.setAccessToken(accessToken);
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(hashPass);
        user.setType(request.getType());
        user.setEnabel(false);
        user.setStatus(0);
        user.setConfirmCode(randomCode);
        // TODO send code to user register email
        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setSubject("Confirm Register Code Pthtwat");
        emailTemplate.setReceiver(request.getEmail());
        emailTemplate.setContent(randomCode);
        emailService.sendEmail(emailTemplate);

        userService.save(user);

        UserDto userDto = userMapper.toDto(user);
        userDto.setAccessToken(accessToken);

        response.setUser(userDto);

        return response;
    }

    // Confirm registerCode
    public UserResponse confirmRegisterCode(String email, String code) {
        UserResponse response = new UserResponse();
        User user = userService.findByEmail(email);
        if (user == null) {
            response.setIsSuccess(false);
            response.setMessage("user have not register yet");

            return response;
        }

        if (!user.getConfirmCode().equals(code)) {
            response.setIsSuccess(false);
            response.setMessage("code not match");
        }
        user.setEnabel(true);
        user.setConfirmCode("");
        userService.save(user);
        UserDto userDto = userMapper.toDto(user);
        response.setUser(userDto);

        return response;
    }


    private String validateRegisterRequest(RegisterRequest request) {
        if (request.getPassword().isEmpty() || request.getEmail().isEmpty()) {
            return "email and password must not empty";
        }
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if (!request.getEmail().matches(regex)) {
            return "email form invalid";
        }
        if (request.getPassword().length() <= 8) {
            return "password too short";
        }
        if (!request.getType().equals("user") && !request.getType().equals("counselor") && !request.getType().equals("admin")) {
            return "type user invalid";
        }
        if (userService.checkEmailExist(request.getEmail())) {
            return "email exist";
        }

        return "valid";
    }

    public UserResponse forgotPassword(String email) {
        UserResponse response = new UserResponse();
        User user = userService.findByEmailAndEnabelTrue(email);
        if (user == null) {
            response.setIsSuccess(false);
            response.setMessage("email is not exist");

            return response;
        }
        String newPass = commonService.genStringRandomWithLength(12);
        String newHashPass = commonService.getSha256hex(newPass);
        user.setPassword(newHashPass);

        userService.save(user);
        response.setMessage("check your email to get a new Password");

        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setSubject("Forgot Password");
        emailTemplate.setReceiver(user.getEmail());
        emailTemplate.setContent("You forgot pass? We sent to you a new pass to login our system. The pass is : " + "<b>" + newPass + "</b>");
        emailService.sendEmail(emailTemplate);

        return response;
    }
}
