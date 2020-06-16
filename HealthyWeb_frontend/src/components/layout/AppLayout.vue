<template>
  <va-page-layout @toggleSidebar="toggleSidebar" :mobileWidth="mobileWidth">
    <app-navbar 
      :minimized="minimized"
      :currentUser.sync="currentUser"
      :currentImgUser="currentImgUser"/>
    <app-sidebar :minimized="minimized" />
    <main
      slot="content"
      id="content"
      class="layout gutter--xl fluid"
      :class="{'app-layout__main--full-width-sidebar': !minimized}"
      role="main"
    >
      <router-view />
    </main>
  </va-page-layout>
</template>

<script>
import VaPageLayout from "../../views/components/va-page-layout/VaPageLayout";
import AppNavbar from "./app-navbar/AppNavbar";
import AppSidebar from "./app-sidebar/AppSidebar";
import ProfileDropdown from "./app-navbar/components/ProfileDropdown";
import Auth from "../../security/Authentication";
import { mapGetters } from "vuex";
import { mapState} from "vuex";
export default {
  name: "app-layout",
  components: {
    VaPageLayout,
    AppNavbar,
    AppSidebar,
    ProfileDropdown
  },
  data() {
    return {
      baseS3Url: "https://s3-ap-northeast-1.amazonaws.com/osouji-help/",
      // minimized: false,
      mobileWidth: 767,
      circleUrl :"https://www.w3schools.com/howto/img_avatar.png",
      currentUser: {},
      currentImgUser: ""
    };
  },
  computed: {
    ...mapGetters(["isLoading"]),
    ...mapState(["minimized"])
  },
  methods: {
    toggleSidebar(minimized) {
      this.minimized = !minimized;
    }
  },
  mounted(){
    this.currentUser = JSON.parse(Auth.getCurrentUser());
    // if(this.currentUser.avatarUrl){
    //   this.currentImgUser = this.baseS3Url + this.currentUser.avatarUrl;
    // }
  },
};
</script>

<style lang="scss">
.body-form {
  margin-bottom: 70px;
}

#content {
  margin: 30px;
  font-size: 14px;
}
.bottom-dialog{
  float: right;
}

.img-avatar{
  display: flex;
  margin-left: 45%;
  margin-top: -45px;
  margin-bottom: 25px;
  #upload-photo {
   opacity: 0;
   position: absolute;
   z-index: -1;
  }
}
.el-dialog-profile > .el-dialog{
  height: 60%;
}

.el-dialog-change-password > .el-dialog{
  height: 35%;
}

.user-admin-image{
  cursor: pointer;
  width: 80px;
  height: 80px;
  border-radius: 50%;
  background-color: #fff;
  background-size: cover;
  background-position: 50% 50%;
  background-image: url("https://www.w3schools.com/howto/img_avatar.png");
}
// .app-layout {
//   &__main {
//     &--full-width-sidebar {
//       @include media-breakpoint-down(xs) {
//         display: none;
//       }
//     }
//   }
// }
</style>