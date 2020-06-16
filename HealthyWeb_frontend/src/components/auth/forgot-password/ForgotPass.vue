<template>
  <div>
    <div class="body-form-forgot-pass">
      <div class="title">
        <img src="@/assets/img/forgotpass.png" />
      </div>
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="demo-ruleForm">
        <el-form-item label="Email" prop="email">
          <el-input v-model="ruleForm.email"></el-input>
        </el-form-item>
        <el-form-item label="Password (the last password you remember)" prop="password">
          <el-input v-model="ruleForm.password"></el-input>
        </el-form-item>
      </el-form>
      <el-button class="btn-send" type="primary" @click="submitForgotPass">
        Send to you mail
        <i class="el-icon-message el-icon-right"></i>
      </el-button>
    </div>
  </div>
</template>
<script>
import AccountApi from "@/api/auth/AccountApi";
export default {
  name: "app-forgot-pass",
  data() {
    return {
      ruleForm: {
        email: "",
        password: ""
      },
      rules: {
        email: [
          {
            required: true,
            message: "Please input Email",
            trigger: "blur"
          }
        ]
      }
    };
  },
  methods: {
    submitForgotPass() {
      AccountApi.forgotPass(this.ruleForm.email).then(res => {
        if (res.isSuccess == true) {
          this.$router.push({ path: "/login" }).catch(res => {});
          this.$message({
            type: "success",
            message: "Check your email to get new pass which we sent !"
          });
        } else {
          this.$message({
            type: "error",
            message: "Failing ! " + res.message
          });
        }
      });
    }
  }
};
</script>
<style lang="scss" scoped>
.body-form-forgot-pass {
  width: 90%;
  background-color: #fff;
  margin: auto;
  padding: 30px;
  color: #777;
  border: solid 1px #e2e3e5;
  .title {
    text-align: center;
    margin-top: 20px;
    margin-bottom: 25px;
    font-weight: bold;
  }
  .btn-send {
    float: right;
  }
}
@media (min-width: 992px) {
  .body-form-forgot-pass {
    width: 600px;
    height: 640px;
  }
}
</style>