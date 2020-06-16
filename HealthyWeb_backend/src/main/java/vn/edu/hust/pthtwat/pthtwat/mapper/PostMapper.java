package vn.edu.hust.pthtwat.pthtwat.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hust.pthtwat.pthtwat.dto.PostDto;
import vn.edu.hust.pthtwat.pthtwat.entity.Post;
import vn.edu.hust.pthtwat.pthtwat.entity.User;
import vn.edu.hust.pthtwat.pthtwat.repository.PostRepository;
import vn.edu.hust.pthtwat.pthtwat.repository.UserRepository;

@Service
public class PostMapper {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public PostDto toDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setSubject(post.getSubject());
        postDto.setContent(post.getContent());
        postDto.setUserId(post.getUserId());
        User user = userRepository.findOne(post.getUserId());
        postDto.setUserName(user.getFirstName() + " " + user.getLastName());

        return postDto;
    }
}
