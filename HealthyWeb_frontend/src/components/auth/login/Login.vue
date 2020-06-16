<template>
  <div>
    <form class="authen-box" @submit.prevent="login" v-loading="isLoading" v-if="!isConfirm">
      <div class="title">
        <img src="@/assets/img/healthyweb.png" />
      </div>

      <div class="control-box">
        <div class="field">
          <label for="email">Email</label>
          <input
            v-model="email"
            type="email"
            class="input__container__input"
            placeholder="example@gmail.com"
          />
        </div>
        <div class="field">
          <label for="password">Password</label>
          <input
            v-model="password"
            type="password"
            placeholder="Password"
            class="input__container__input"
          />
        </div>
        <el-row>
          <el-col>
            <div class="type-login">
              <el-select v-model="type" placeholder="Type of User">
                <el-option label="User" value="user"></el-option>
                <el-option label="Counselor" value="counselor"></el-option>
                <el-option label="Admin" value="admin"></el-option>
              </el-select>
            </div>
          </el-col>
        </el-row>

        <div class="submit">
          <el-checkbox class="el-checkbox" v-model="rememberMe" size="medium">Remember me</el-checkbox>
          <button class="btn-submit" tabindex="3">Login</button>
          <br clear="all" />
        </div>
        <div class="forgot">
          <a v-on:click="forgotPasswordClick">Forgot password</a>
        </div>
      </div>
      <div class="register">
        <span>Don't have account?</span>
        <a v-on:click="register">Register Now</a>
      </div>
    </form>
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
import AccountApi from "@/api/auth/AccountApi.js";
import LoginApi from "@/api/auth/LoginApi.js";
import Auth from "@/security/Authentication";
export default {
  name: "login",
  data() {
    return {
      isConfirm: false,
      email: "",
      password: "",
      keepLoggedIn: false,
      isLoading: false,
      rememberMe: false,
      type: "",
      confirmCode: ""
    };
  },

  methods: {
    confirm() {
      if (this.confirmCode.trim() != "") {
        AccountApi.confirmCodeLogin(this.email, this.confirmCode).then(response => {
          if (response.isSuccess) {
            Auth.setToken(response.user.accessToken);
            Auth.setCurrentUser(response.user);
            this.isLoading = false;
            this.redirect();
          } else {
            this.isLoading = false;
            this.$notify({
              title: this.$t("common.Error"),
              //TODO translate message
              message: response.message,
              type: "error"
            });
          }
        });
      }
    },
    haveNullInput() {
      if (this.email.trim() == 0) {
        return "Email must be not empty";
      }
      if (this.password.trim() == 0) {
        return "Password must be not empty";
      }

      return false;
    },
    async login() {
      this.isLoading = true;
      if(this.email.includes("<script>") || this.password.includes("<script>")) {
        this.$message({
          type: "error",
          message: "Do not use XSS! Character"
        })
      }
      let loginData = {
        email: this.email,
        password: this.password,
        // remember: this.rememberMe
        type: this.type
      };
      console.log(loginData);
      if (!this.haveNullInput()) {
        try {
          let response = await LoginApi.login(loginData);
          if (response.isSuccess) {
            if (response.message == "need confirm login code") {
              this.isConfirm = true;
              return;
            }
            console.log(response);
            Auth.setToken(response.user.accessToken);
            Auth.setCurrentUser(response.user);
            this.isLoading = false;
            this.redirect();
          } else {
            this.isLoading = false;
            this.$notify({
              title: this.$t("common.Error"),
              //TODO translate message
              message: response.message,
              type: "error"
            });
          }
        } catch (e) {
          console.log(e.response.data);
          this.isLoading = false;
          this.$notify({
            title: this.$t("common.Error"),
            //TODO translate message
            message: e.response.data.message,
            type: "error"
          });
        } finally {
        }
      } else {
        this.isLoading = false;
        this.$notify({
          title: this.$t("common.Error"),
          message: this.haveNullInput(),
          type: "error"
        });
      }
    },

    redirect() {
      this.$router.push({ path: "/profile" }).catch(res => {});
    },
    forgotPasswordClick() {
      this.$router.push({ path: "/forgotPass" }).catch(res => {});
    },
    register() {
      this.$router.push({ path: "/register" });
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
.authen-box {
  width: 90%;
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

  .type-login {
    float: left;
    margin-right: 10px;
  }

  .control-box {
    margin-top: 50px;
    padding-left: 20px;
    padding-right: 30px;
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
        border-radius: 6px;
        border: solid 1px #e2e3e5;
      }
    }

    .submit {
      .el-checkbox {
        float: left;
        font-size: 30px;
      }

      position: relative;
      margin-bottom: 35px;
      margin-top: 35px;

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
    .forgot {
      float: left;
      margin-left: 5px;
      font-weight: bold;
      color: #007feb;
      cursor: pointer;
    }
  }
  .register {
    margin-top: 37px;
    a {
      font-weight: bold;
      color: #007feb;
      cursor: pointer;
    }
  }
}

@media (min-width: 992px) {
  .authen-box {
    width: 600px;
    height: 640px;
  }
}
</style>