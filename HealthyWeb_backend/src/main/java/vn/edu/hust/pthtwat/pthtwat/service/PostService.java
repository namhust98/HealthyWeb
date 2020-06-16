package vn.edu.hust.pthtwat.pthtwat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hust.pthtwat.pthtwat.dto.AnswerDto;
import vn.edu.hust.pthtwat.pthtwat.dto.PostDto;
import vn.edu.hust.pthtwat.pthtwat.entity.Answer;
import vn.edu.hust.pthtwat.pthtwat.entity.Post;
import vn.edu.hust.pthtwat.pthtwat.entity.User;
import vn.edu.hust.pthtwat.pthtwat.mapper.AnswerMapper;
import vn.edu.hust.pthtwat.pthtwat.mapper.PostMapper;
import vn.edu.hust.pthtwat.pthtwat.repository.AnswerRepository;
import vn.edu.hust.pthtwat.pthtwat.repository.PostRepository;
import vn.edu.hust.pthtwat.pthtwat.response.PostResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private AnswerMapper answerMapper;

    public PostResponse getPosts() {
        PostResponse response = new PostResponse();
        List<PostDto> postDtos = new ArrayList<>();
        List<Post> posts = postRepository.findAll();
        for(Post post : posts) {
            List<Answer> answers = answerRepository.findByPostId(post.getId());
            PostDto postDto = postMapper.toDto(post);
            List<AnswerDto> answerDtos = answerMapper.toDtos(answers);
            postDto.setAnswers(answerDtos);
            postDtos.add(postDto);
        }
        response.setPosts(postDtos);

        return response;
    }

    public PostResponse createPost(User user, String content, String subject) {
        PostResponse response = new PostResponse();
        Post post = new Post();
        post.setUserId(user.getId());
        post.setSubject(subject);
        post.setContent(content);
        Post result = postRepository.save(post);
        response.setPost(postMapper.toDto(result));

        return response;
    }
}
