package vn.edu.hust.pthtwat.pthtwat.response;

import vn.edu.hust.pthtwat.pthtwat.dto.AnswerDto;
import vn.edu.hust.pthtwat.pthtwat.dto.PostDto;

import java.util.List;

public class PostResponse extends BaseResponse {
    private PostDto post;
    private List<PostDto> posts;
    private AnswerDto answer;

    public PostDto getPost() {
        return post;
    }

    public void setPost(PostDto post) {
        this.post = post;
    }

    public List<PostDto> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDto> posts) {
        this.posts = posts;
    }

    public AnswerDto getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerDto answer) {
        this.answer = answer;
    }
}
