import { AuthAppRequest as request } from "@/api/Request";

export default {
    getPosts() {
        return request.get(`/api/v1/posts`);
    },
    createPost(content, subject) {
        return request.post(`/api/v1/post?content=${content}&subject=${subject}`);
    },
    answer(content, postId) {
        return request.post(`/api/v1/answer?content=${content}&postId=${postId}`);
    }
}