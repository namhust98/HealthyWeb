<template>
  <div>
    <div v-if="!isConfirm">
      <form class="authen-box-register" @submit.prevent="register" v-loading="isLoading">
        <div class="title">
          <img src="@/assets/img/register.png" />
        </div>

        <div class="control-box">
          <div class="field" style="display: flex">
            <input v-model="firstName" class="input-first-name" placeholder="First Name" />
            <input v-model="lastName" class="input-last-name" placeholder="Last Name" />
          </div>
          <div class="field">
            <input
              v-model="email"
              type="email"
              class="input__container__input"
              placeholder="example@gmail.com"
            />
          </div>

          <div class="field">
            <input
              v-model="password"
              type="password"
              placeholder="Password"
              class="input__container__input"
            />
          </div>
          <div class="field">
            <input
              v-model="retypePassword"
              type="password"
              placeholder="Confirm your password"
              class="input__container__input"
            />
          </div>
          <el-row>
            <el-col>
              <div class="type-register">
                <el-select v-model="type" placeholder="Type of User">
                  <el-option label="User" value="user"></el-option>
                  <el-option label="Counselor" value="counselor"></el-option>
                </el-select>
              </div>
            </el-col>
          </el-row>

          <div class="submit">
            <el-checkbox
              class="el-checkbox"
              v-model="agreeTerms"
              size="medium"
            >I agree with terms of services</el-checkbox>
            <button class="btn-submit" tabindex="3">Register</button>
            <br clear="all" />
          </div>
        </div>
        <div class="register-form">
          <span>Already has an account?</span>
          <a v-on:click="login" class="customA">Login</a>
        </div>
      </form>
    </div>
    <div v-if="isConfirm">
      <div class="confirm-box">
        <div class="title">Check your mail ({{email}}) to get code !</div>
        <el-input placeholder="Please input the code" v-model="confirmCode"></el-input>
        <el-button class="btn-confirm" type="primary" plain @click="confirm">Confirm</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import RegisterApi from "@/api/auth/RegisterApi.js";
// import Auth from "@/security/Authentication";
export default {
  name: "register-manager",
  data() {
    return {
      email: "",
      password: "",
      keepLoggedIn: false,
      isLoading: false,
      retypePassword: "",
      firstName: "",
      lastName: "",
      type: "",
      agreeTerms: false,
      isConfirm: false,
      confirmCode: ""
    };
  },
  methods: {
    redirect() {
      this.$router.push({ path: "/login" });
    },
    redirectToConfirm() {
      this.$router.push({ path: "/confirmRegister" });
    },
    login() {
      this.$router.push({ path: "/login" });
    },
    haveNullInput() {
      if (this.firstName.trim() == 0) {
        return "First name must be not null";
      }
      if (this.lastName.trim() == 0) {
        return "Last name must be not null";
      }
      if (this.email.trim() == 0) {
        return "Email must be not null";
      }
      if (this.type.trim() == 0) {
        return "Type must be not null";
      }
      if (this.password.trim() == 0) {
        return "Password must be not null";
      }
      if (this.retypePassword.trim() == 0) {
        return "Confirm password must be not null";
      }
      if (!this.agreeTerms) {
        return "You must agree with terms of service";
      }
      return false;
    },
    async register() {
      this.isLoading = true;
      if (!this.haveNullInput()) {
        if (this.retypePassword.trim() !== this.password.trim()) {
          this.isLoading = false;
          this.$notify({
            title: "Error",
            message: "Confirm password not equal with password",
            type: "error"
          });
          this.password = "";
          this.retypePassword = "";
        } else {
          let userRegister = {
            email: this.email,
            password: this.password,
            firstName: this.firstName,
            lastName: this.lastName,
            type: this.type
          };
          try {
            let response = await RegisterApi.register(userRegister);
            if (response.isSuccess) {
              this.isLoading = false;
              if (this.type == "counselor") {
                this.$message({
                  type: "success",
                  message: "Check your email to get code !"
                });
                this.isConfirm = true;
              } else {
                this.$message({
                  type: "success",
                  message: "You can login now !"
                });
                this.redirect();
              }
            } else {
              this.isLoading = false;
              this.$notify({
                title: "Error",
                message: "Register error",
                type: "error"
              });
            }
          } catch (e) {
            console.log(e);
          } finally {
          }
        }
      } else {
        this.isLoading = false;
        this.$notify({
          title: "Error",
          message: this.haveNullInput(),
          type: "error"
        });
      }
    },
    async confirm() {
      if (this.confirmCode.trim() != "") {
        await RegisterApi.confirmCode(this.email, this.confirmCode).then(
          res => {
            if (res.isSuccess) {
              this.$message({
                type: "success",
                message: "Now, you can login !"
              });
              this.$router.push({ path: "/login" });
            } else {
              this.$message({
                type: "error",
                message: "Confirm register code failure"
              });
            }
          }
        );
      } else {
        this.$message({
          type: "error",
          message: "Check your mail to get the code then input here to confirm"
        });
      }
    }
  },
  mounted() {}
};
</script>

<style lang="scss">
.confirm-box {
  width: 600px;
  height: 200px;
  background-color: #fff;
  margin: auto;
  padding: 30px;
  color: #777;
  border: solid 1px #e2e3e5;
  .title {
    color: #007feb;
    margin-bottom: 30px;
  }
  .btn-confirm {
    float: right;
    margin-top: 20px;
  }
}

.type-register {
  float: left;
}

.authen-box-register {
  width: 600px;
  height: 700px;
  background-color: black;
  margin: auto;
  padding: 30px;
  color: #777;
  border: solid 1px #e2e3e5;
  border-radius: 6px;
  .title {
    text-align: center;
    margin-top: 20px;
    margin-bottom: 25px;
    font-weight: bold;
  }
  .control-box {
    margin-top: 50px;
    padding-left: 20px;
    padding-right: 30px;
    .user-type {
      &__selected {
        width: 260px;
        margin-top: 10px;
        text-align: left;
        font-size: 15px;
        display: block;
        position: relative;
        padding-left: 35px;
        margin-bottom: 12px;
        cursor: pointer;
        font-size: 15px;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
        .typeUser {
          font-weight: 400;
          margin-top: 3px;
        }
        input {
          position: absolute;
          opacity: 0;
          cursor: pointer;
        }
        input:checked ~ .checkmark {
          background-color: #2196f3;
        }
        input:checked ~ .checkmark:after {
          display: block;
        }
        .checkmark:after {
          top: 9px;
          left: 9px;
          width: 8px;
          height: 8px;
          border-radius: 50%;
          background: #fff;
        }
        .checkmark {
          position: absolute;
          top: 0;
          left: 0;
          height: 25px;
          width: 25px;
          background-color: #eee;
          border-radius: 50%;
        }
      }
      .checkmark:after {
        content: "";
        position: absolute;
        display: none;
      }
      margin-bottom: 35px;
      label {
        float: left;
        max-width: 100%;
        margin-bottom: 5px;
        font-weight: 700;
      }
    }
    .field {
      margin-bottom: 50px;
      label {
        float: left;
        max-width: 100%;
        margin-bottom: 5px;
        font-weight: 700;
      }
      .input__container__input {
        width: 93%;
        padding: 13px 20px;
        border: 0;
        border: solid 1px #e2e3e5;
        border-radius: 6px;
      }
      .input-last-name {
        width: 50%;
        padding: 13px 20px;
        border: 0;
        border: solid 1px #e2e3e5;
        border-radius: 6px;
      }
      .input-first-name {
        margin-right: 30px;
        width: 50%;
        padding: 13px 20px;
        border: 0;
        border: solid 1px #e2e3e5;
        border-radius: 6px;
      }
    }
    .submit {
      position: relative;
      margin-bottom: 25px;
      margin-top: 25px;
      .el-checkbox {
        float: left;
        font-size: 30px;
      }
      .btn-submit {
        margin-right: -5px;
        float: right;
        cursor: pointer;
        font-size: 15px;
        background-color: #007feb;
        color: #fff;
        font-weight: bold;
        padding: 15px;
        width: 150px;
        border: 0;
        border-radius: 5px;
      }
    }
  }
  .register-form {
    margin-top: 0px;
    .customA {
      font-weight: bold;
      color: #007feb;
      cursor: pointer;
    }
  }
}
</style>