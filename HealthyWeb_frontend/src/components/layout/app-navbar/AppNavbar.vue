<template>
  <va-navbar class="app-navbar">
    <template slot="logo">
      <div class="logo">
        <img style="width: 100px; height: 25px" src="@/assets/img/healthyweb.png" />
      </div>
    </template>
    <template slot="center">
      <div class="row flex-nowrap align--center justify--space-between"></div>
    </template>
    <div class="app-navbar__actions row flex-nowrap align--center">
      <div class="right-title">
        <div class="wrap-profile-user">
          <div class="profile-user">
            <div class="name-user">
              <p>
                {{currentUser.firstName}}
                {{currentUser.lastName}}
              </p>
              <p class="type-user">{{currentUser.type}}</p>
            </div>
            <img v-if="currentImgUser.length > 0" class="img-user" v-bind:src="currentImgUser" />
            <default-avatar
              v-if="currentImgUser.length == 0"
              style="margin: auto;margin-right: 10px;"
              :name="firstCharacterName"
              :size="15"
              :width="44"
              :radius="50"
              :height="44"
            ></default-avatar>
          </div>
        </div>
      </div>
    </div>
    <profile-dropdown
      v-if="isShowProfileDropdown"
      :currentUser.sync="currentUser"
      :currentImgUser="currentImgUser"
      @closeDropdown="isShowProfileDropdown = false;"
    ></profile-dropdown>
  </va-navbar>
</template>

<script>
import VaIconVuestic from "../../../views/components/va-icon/va-iconset/VaIconVuestic";
import VaNavbar from "../../../views/components/va-navbar/VaNavbar";
import ProfileDropdown from "../app-navbar/components/ProfileDropdown";
import DefaultAvatar from "../../../views/components/default-avatar/DefaultAvatar";
import i18n from "@/i18n/index.js";

export default {
  name: "app-navbar",
  components: {
    VaIconVuestic,
    VaNavbar,
    ProfileDropdown,
    DefaultAvatar
  },
  props: {
    minimized: {
      type: Boolean,
      required: true
    },
    currentUser: {},
    currentImgUser: ""
  },
  data() {
    return {
      firstCharacterName: "",
      userName: "Vasili S",
      isShowProfileDropdown: false,
      language: "en",
      languages: [
        {
          value: "en",
          label: "English"
        },
        {
          value: "jp",
          label: "Japan"
        }
      ]
    };
  },
  computed: {
    minimizedProxy: {
      get() {
        return this.minimized;
      },
      set(minimized) {
        this.$emit("update:minimized", minimized);
      }
    }
  },
  mounted() {
    if (this.currentUser.lastName) {
      this.firstCharacterName = this.currentUser.lastName.charAt(0);
    }
    console.log(this.currentUser);
    this.language = i18n.getCurrentLang();
  },
  methods: {
    // showHideProfile() {
    //   if (this.isShowProfileDropdown) {
    //     this.isShowProfileDropdown = false;
    //   } else {
    //     this.isShowProfileDropdown = true;
    //   }
    // },
    changeLang(langValue) {
      i18n.changeLang(langValue);
    }
  }
};
</script>
<style lang="scss">
.va-navbar .row {
  height: 100%;
}

.app-navbar {
  .logo {
    bottom: 0;
    left: 3.5rem;
    width: 9.5rem;
    height: 1rem;
    margin: auto;
    z-index: 2;
  }

  .right-title {
    height: 100%;
    display: flex;
    margin-right: 0px;

    .wrap-friend-request {
      height: 100%;
      width: 55px;
      // :hover {
      //   background-color: #36acb2;
      //   cursor: pointer;
      // }
      .friend-request {
        height: 100%;
        width: 100%;
        .el-icon-user-solid {
          color: #ffffff;
          padding: 7px;
          margin-top: 17px;
        }

        .label-danger {
          color: white;
          margin-left: -15px;
          border-radius: 3px;
          position: absolute;
          top: 14px;
          text-align: center;
          font-size: 9px;
          padding: 2px 3px;
          line-height: 0.9;
          background-color: #dd4b39;
        }
      }
    }
    .wrap-notification {
      height: 100%;
      width: 55px;
      // :hover {
      //   background-color: #36acb2;
      //   cursor: pointer;
      // }
      .notification {
        height: 100%;
        width: 100%;
        .el-icon-message-solid {
          color: #ffffff;
          padding: 7px;
          margin-top: 17px;
        }

        .label-danger {
          color: white;
          margin-left: -15px;
          border-radius: 3px;
          position: absolute;
          top: 14px;
          text-align: center;
          font-size: 9px;
          padding: 2px 3px;
          line-height: 0.9;
          background-color: #dd4b39;
        }
      }
    }

    .wrap-profile-user {
      // :hover {
      //   background-color: #2b6f73;
      // }
      .profile-user {
        display: flex;
        height: 100%;
        width: 100%;
        cursor: pointer;
        .name-user {
          margin: auto;
          color: #ffffff;
          width: 150px;
          font-size: 15px;
          .type-user {
            margin-top: -12px;
            color: #f6f6f6;
            font-size: 13px;
            margin-right: -19px;
          }
        }
        .img-user {
          margin: auto;
          height: 44px;
          width: 44px;
          border-radius: 50%;
          margin-right: 10px;
        }
      }
    }
    .wrap-select-lang {
      width: 100px;
      .select-lang {
        margin-top: 13px;

        .el-input__inner {
          background-color: #36acb2;
          color: white;
          border-color: white;
        }
      }
    }
  }

  &__button {
    width: 10.8125rem;
    .va-button__content__icon-left.fa-github {
      font-size: 1.5rem;
    }
  }
  &__actions {
    justify-content: flex-end;
  }
  //   @include media-breakpoint-down(lg) {
  //     &__button {
  //       display: none !important;
  //     }
  //     .profile-dropdown {
  //       margin: auto 0 auto .75rem;
  //     }
  //   }
  //   @include media-breakpoint-down(md) {
  //     @at-root .va-navbar__text {
  //       display: none !important;
  //     }
  //   }
  //   @include media-breakpoint-down(sm) {
  //     &__button {
  //       display: none !important;
  //     }
  //     &__actions {
  //       justify-content: space-around;
  //       padding: 0 .5rem;
  //     }
  //     .profile-dropdown {
  //       position: absolute;
  //       right: .75rem;
  //       top: 1.25rem;
  //       height: fit-content;
  //       margin: auto;
  //     }
  //   }
}
</style>