package vn.edu.hust.pthtwat.pthtwat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hust.pthtwat.pthtwat.common.CommonService;
import vn.edu.hust.pthtwat.pthtwat.common.EmailTemplate;
import vn.edu.hust.pthtwat.pthtwat.dto.UserDto;
import vn.edu.hust.pthtwat.pthtwat.entity.User;
import vn.edu.hust.pthtwat.pthtwat.mapper.UserMapper;
import vn.edu.hust.pthtwat.pthtwat.response.UserResponse;

@Service
public class LogInService {

    @Autowired
    private CommonService commonService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserMapper userMapper;

    public UserResponse signIn(String email, String pass, String type) {
        UserResponse response = new UserResponse();
        // check request
        if (!this.checkValidSignIn(email, pass, type)) {
            response.setIsSuccess(false);
            response.setMessage("Invalid user or password");

            return response;
        }
        User user = userService.findByEmailAndTypeAndEnabelTrue(email, type);
        // check user email not exist
        if (user == null) {
            response.setIsSuccess(false);
            response.setMessage("Invalid user or password");

            return response;
        }
        // check status
        if (user.getStatus() >= 3) {
            response.setIsSuccess(false);
            response.setMessage("Invalid user or password");

            return response;
        }
        //hash and check pass
        if (!user.getPassword().equals(commonService.getSha256hex(pass))) {
            response.setIsSuccess(false);
            response.setMessage("Invalid user or password");
            user.setStatus(user.getStatus() + 1);
            userService.save(user);

            return response;
        }
        if (type.equals("counselor") || type.equals("admin")) {
            // TODO send code to mail
            String randomCode = commonService.genCodeRandom();
            EmailTemplate emailTemplate = new EmailTemplate();
            emailTemplate.setSubject("Confirm Login Code Pthtwat");
            emailTemplate.setReceiver(user.getEmail());
            emailTemplate.setContent(randomCode);
            emailService.sendEmail(emailTemplate);

            user.setConfirmCode(randomCode);
            userService.save(user);
            response.setIsSuccess(true);
            response.setMessage("need confirm login code");

            return response;
        }
        //login success => gen a new accessToken
        user.setAccessToken(commonService.genStringRandom());
        userService.refreshLastTimeActive(user);
        userService.save(user);
        UserDto userDto = userMapper.toDto(user);
        userDto.setAccessToken(user.getAccessToken());

        response.setUser(userDto);

        return response;
    }

    private boolean checkValidSignIn(String email, String pass, String type) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if (!email.matches(regex) || pass.length() < 8 || (!type.equals("user") && !type.equals("counselor") && !type.equals("admin"))) {
            return false;
        }

        return true;
    }

    public UserResponse confirmLoginCode(String email, String code) {
        UserResponse response = new UserResponse();
        User user = userService.findByEmail(email);
        if (user == null || ( user.getConfirmCode()==null || user.getConfirmCode().equals(""))) {
            response.setIsSuccess(false);
            response.setMessage("user have not request login yet");

            return response;
        }

        if (!user.getConfirmCode().equals(code)) {
            response.setIsSuccess(false);
            response.setMessage("code not match");
        }
        user.setConfirmCode("");
        userService.save(user);
        UserDto userDto = userMapper.toDto(user);
        userDto.setAccessToken(user.getAccessToken());
        response.setUser(userDto);
        response.setMessage("login success");

        return response;
    }
}
