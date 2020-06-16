<template>
  <div class="form-box">
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="120px"
      label-position="left"
      class="ruleForm"
    >
      <div class="wrap-email">
        <div class="label">Email</div>
        <div class="email">{{ruleForm.email}}</div>
      </div>
      <el-form-item label="First name" prop="firstName">
        <el-input v-model="ruleForm.firstName"></el-input>
      </el-form-item>
      <el-form-item label="Last name" prop="lastName">
        <el-input v-model="ruleForm.lastName"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button class="btn-change" type="primary" @click="submitForm('ruleForm')">Change</el-button>
        <el-button @click="resetForm('ruleForm')">Reset</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import Auth from "@/security/Authentication";
import UserApi from "@/api/user/UserApi.js";
export default {
  name: "app-profile",
  data() {
    return {
      ruleForm: {
        firstName: "",
        lastName: "",
        email: "",
        password: ""
      },
      rules: {
        firstName: [
          {
            required: true,
            message: "Please input first name",
            trigger: "blur"
          }
        ],
        lastName: [
          {
            required: true,
            message: "Please select last name",
            trigger: "blur"
          }
        ],
        email: [
          {
            required: true,
            message: "Please input your email",
            trigger: "blur"
          }
        ]
      }
    };
  },
  mounted() {
    let user = Auth.getCurrentUser();
    user = JSON.parse(user);
    this.fillForm(user);
  },
  methods: {
    fillForm(user) {
      this.ruleForm.firstName = user.firstName;
      this.ruleForm.lastName = user.lastName;
      this.ruleForm.email = user.email;
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          let param = {
            firstName: this.ruleForm.firstName,
            lastName: this.ruleForm.lastName
          };
          UserApi.getChecklistDetail(param).then(res => {
            if (res.isSuccess == true) {
              this.$message({
                type: "success",
                message: "Update complete"
              });
            } else {
              this.$message({
                type: "error",
                message: "Update failure"
              });
            }
          });
        } else {
          console.log("error submit!!");
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
.form-box {
  width: 90%;
  background-color: #fff;
  margin: auto;
  padding: 30px;
  color: #777;
  border: solid 1px #e2e3e5;
  .wrap-email {
    padding-bottom: 25px;
    display: flex;
    .label {
      float: left;
      padding: 5px;
    }
    .email {
      margin-left: 40%;
      font-size: 15px;
      font-weight: bold;
      padding: 5px;
      border: 1px solid white;
      border-radius: 5px;
    }
  }
  // .btn-change {
  //   span {
  //     color: black;
  //   }
  // }
}

@media (min-width: 992px) {
  .form-box {
    background: black;
    width: 600px;
    height: 250px;
    border-radius: 6px;
  }
}
</style>