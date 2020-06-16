<template>
  <div class="wrap">
    <div class="body">
      <el-row class="title">
        <el-col :span="4" class="user-name">{{post.userName}}</el-col>

        <el-col :span="20" class="subject">
          <span>
            <i class="el-icon-question"></i>
          </span>
          {{post.subject}}
        </el-col>
      </el-row>
      <el-row class="row-content">
        <el-col :span="24" class="content">{{post.content}}</el-col>
        <div class="title-answer" v-if="post.answer != undefined">
          <span>
            <i class="el-icon-info"></i>
          </span>Reply
        </div>
        <app-answer
          class="answer-group"
          v-for="answer of post.answers"
          :key="answer.id"
          :answer="answer"
        ></app-answer>
        <el-input
          v-if="addAnswer"
          class="text-area"
          type="textarea"
          :rows="2"
          placeholder="Please input"
          v-model="answer"
        ></el-input>
      </el-row>
      <el-row class="add-answer-btn">
        <el-col :span="24">
          <el-button
            v-if="!addAnswer"
            type="primary"
            icon="el-icon-edit"
            @click="addAnswer = true"
            size="mini"
          >Add Answer</el-button>
          <el-button
            v-if="addAnswer"
            type="primary"
            icon="el-icon-edit"
            @click="postAnswer"
            size="mini"
          >Post Answer</el-button>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
import PostApi from "@/api/post/PostApi.js";
import AppAnswer from "./Answer";
export default {
  name: "app-post",
  components: {
    AppAnswer
  },
  props: {
    post: {}
  },
  data() {
    return {
      addAnswer: false,
      answer: ""
    };
  },
  methods: {
    async postAnswer() {
      if (!this.answer) {
        return;
      }
      await PostApi.answer(this.answer, this.post.id).then(res => {
        if (!res.isSuccess) {
          this.$message({ type: "error", message: "answer failure" });
        }
        this.$emit("addAnswer",res.answer);
        this.answer = "";
      });
    }
  }
};
</script>
<style lang="scss" scoped>
.wrap {
  border: solid 1px rgb(213, 213, 213);
  border-radius: 6px;
  padding: 15px;
  .body {
    .title {
      border-bottom: 1px solid rgb(213, 213, 213);
      padding-bottom: 5px;
      .user-name {
        text-align: left;
        font-weight: bold;
      }
      .subject {
        text-align: left;
        font-weight: bold;
        border-left: 1px rgb(213, 213, 213);
      }
    }
    .row-content {
      padding-top: 5px;
      .title-answer {
        margin-top: 10px;
        margin-left: 17%;
        text-align: left;
      }
      .content {
        padding-left: 17%;
        text-align: left;
      }
      .answer-group {
        padding-left: 17%;
      }
      .text-area {
        margin-left: 17%;
        width: 83%;
      }
    }
    .add-answer-btn {
      text-align: right;
      margin-top: 10px;
    }
  }
}
</style>