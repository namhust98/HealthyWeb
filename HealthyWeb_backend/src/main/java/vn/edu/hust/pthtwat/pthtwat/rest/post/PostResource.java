package vn.edu.hust.pthtwat.pthtwat.rest.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hust.pthtwat.pthtwat.entity.Answer;
import vn.edu.hust.pthtwat.pthtwat.entity.User;
import vn.edu.hust.pthtwat.pthtwat.response.PostResponse;
import vn.edu.hust.pthtwat.pthtwat.service.AnswerService;
import vn.edu.hust.pthtwat.pthtwat.service.PostService;
import vn.edu.hust.pthtwat.pthtwat.service.UserService;

@RestController
public class PostResource {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private AnswerService answerService;

    @GetMapping("api/v1/posts")
    public ResponseEntity<PostResponse> testRestApi(@RequestHeader(name = "Access-Token") String accessToken) {

        PostResponse response = new PostResponse();
        User user = userService.getUserFromAccessToken(accessToken);
        if(user == null) {
            response.setIsSuccess(false);
            response.setMessage("can't find user from accessToken");

            return ResponseEntity.ok().body(response);
        }
        response = postService.getPosts();

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("api/v1/post")
    public ResponseEntity<PostResponse> createPost(@RequestHeader(name = "Access-Token") String accessToken,
                                                   @RequestParam(name = "content") String content,
                                                   @RequestParam(name = "subject") String subject) {
        PostResponse response = new PostResponse();
        User user = userService.getUserFromAccessToken(accessToken);
        if(user == null) {
            response.setIsSuccess(false);
            response.setMessage("can't find user from accessToken");

            return ResponseEntity.ok().body(response);
        }
        response = postService.createPost(user, content, subject);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("api/v1/answer")
    public ResponseEntity<PostResponse> answer(@RequestHeader(name = "Access-Token") String accessToken,
                                         @RequestParam(name = "content") String content,
                                         @RequestParam(name = "postId") Long postId) {
        PostResponse response = new PostResponse();
        User user = userService.getUserFromAccessToken(accessToken);
        if(user == null) {
            response.setIsSuccess(false);
            response.setMessage("can't find user from accessToken");

            return ResponseEntity.ok().body(response);
        }
        response = answerService.createAnswer(user, content, postId);

        return ResponseEntity.ok().body(response);
    }
}
