package vn.edu.hust.pthtwat.pthtwat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hust.pthtwat.pthtwat.dto.AnswerDto;
import vn.edu.hust.pthtwat.pthtwat.entity.Answer;
import vn.edu.hust.pthtwat.pthtwat.entity.User;
import vn.edu.hust.pthtwat.pthtwat.mapper.AnswerMapper;
import vn.edu.hust.pthtwat.pthtwat.repository.AnswerRepository;
import vn.edu.hust.pthtwat.pthtwat.response.PostResponse;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AnswerMapper answerMapper;

    public PostResponse createAnswer(User user, String content, Long postId) {
        PostResponse response = new PostResponse();
        Answer answer = new Answer();
        answer.setUserId(user.getId());
        answer.setPostId(postId);
        answer.setContent(content);
        Answer result = answerRepository.save(answer);
        AnswerDto answerDto = answerMapper.toDto(result);
        answerDto.setUserName(user.getFirstName() + " " + user.getLastName());
        response.setAnswer(answerDto);

        return response;
    }
}
