<template>
  <div>
    <h1>News</h1>
    <el-row class="add-subject-btn">
      <el-col :span="24">
        <el-button
          type="primary"
          icon="el-icon-edit"
          @click="dialogVisible = true"
          size="small"
        >Add New Subject</el-button>
      </el-col>
    </el-row>
    <el-row class="body">
      <el-col :span="24">
        <app-post
          v-for="post of posts"
          :key="post.id"
          class="post"
          :post="post"
          @addAnswer="addAnswer"
        ></app-post>
      </el-col>
    </el-row>
    <el-dialog title="Create New Post" :visible.sync="dialogVisible" width="30%">
      <span>
        <app-create-post @close="dialogVisible = false" @createPost="addPost"></app-create-post>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import PostApi from "@/api/post/PostApi.js";
import AppPost from "./components/Post";
import AppCreatePost from "./components/CreatePost";

export default {
  name: "app-chat",
  components: {
    AppPost,
    AppCreatePost
  },
  data() {
    return {
      posts: [],
      dialogVisible: false
    };
  },
  methods: {
    addPost(post) {
      this.posts.push(post);
    },
    addAnswer(value) {
      for (let i = 0; i < this.posts.length; i++) {
        if (this.posts[i].id == value.postId) {
          if (!this.posts[i].answers) {
            this.posts[i].answers = [];
          }
          this.posts[i].answers.push(value);
        }
      }
    }
  },
  async mounted() {
    await PostApi.getPosts().then(res => {
      if (!res.isSuccess) {
        this.$message({ type: "error", message: "Load data error" });
        return;
      }
      this.posts = res.posts;
      if (!this.posts) {
        this.posts = [];
      }
    });
  }
};
</script>
<style lang="scss" scoped>
.header {
  font-weight: bold;
  color: black;
}
.add-subject-btn {
  text-align: right;
  margin-bottom: 10px;
}
.body {
  max-height: 600px;
  overflow-y: scroll;
  padding-right: 10px;
}

.body::-webkit-scrollbar {
  width: 6px;
  background-color: white;
  border-radius: 5px;
}
.body::-webkit-scrollbar-thumb {
  border-radius: 5px;
  background-color: #475058;
}

.post {
  margin-bottom: 10px;
}
</style>