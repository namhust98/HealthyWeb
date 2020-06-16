<template>
  <div>
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="120px"
      class="demo-ruleForm"
    >
      <el-form-item label="Subject" prop="subject">
        <el-input v-model="ruleForm.subject"></el-input>
      </el-form-item>
      <el-form-item label="Content" prop="content">
        <el-input type="textarea" v-model="ruleForm.content"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">Create</el-button>
        <el-button @click="resetForm('ruleForm')">Reset</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import PostApi from "@/api/post/PostApi.js";

export default {
  name: "app-create-post",
  data() {
    return {
      ruleForm: {
        subject: "",
        content: ""
      },
      rules: {
        subject: [
          {
            required: true,
            message: "Please input subject",
            trigger: "blur"
          }
        ],
        content: [
          {
            required: true,
            message: "Please input content",
            trigger: "blur"
          }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          PostApi.createPost(this.ruleForm.content, this.ruleForm.subject).then(
            res => {
              if (!res.isSuccess) {
                this.$message({ type: "error", message: "Post error" });
                return;
              }
              this.$message({ type: "success", message: "Post complete" });
              this.resetForm(formName);
              console.log(res.post);
              this.$emit("createPost", res.post);
              this.$emit("close");
            }
          );
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
};
</script>
<style lang="scss" scoped>
</style>