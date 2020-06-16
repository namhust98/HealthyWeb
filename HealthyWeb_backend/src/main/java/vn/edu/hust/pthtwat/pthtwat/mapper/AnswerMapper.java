package vn.edu.hust.pthtwat.pthtwat.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hust.pthtwat.pthtwat.dto.AnswerDto;
import vn.edu.hust.pthtwat.pthtwat.entity.Answer;
import vn.edu.hust.pthtwat.pthtwat.entity.User;
import vn.edu.hust.pthtwat.pthtwat.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerMapper {
    @Autowired
    private UserRepository userRepository;

    public List<AnswerDto> toDtos(List<Answer> answers) {
        List<AnswerDto> answerDtos = new ArrayList<>();
        for (Answer answer : answers) {
            answerDtos.add(this.toDto(answer));
        }

        return answerDtos;
    }

    public AnswerDto toDto(Answer answer) {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setId(answer.getId());
        answerDto.setUserId(answer.getUserId());
        answerDto.setPostId(answer.getPostId());
        answerDto.setContent(answer.getContent());
        User user = userRepository.findById(answer.getUserId());
        answerDto.setUserName(user.getFirstName()+ " " + user.getLastName());

        return answerDto;
    }
}
