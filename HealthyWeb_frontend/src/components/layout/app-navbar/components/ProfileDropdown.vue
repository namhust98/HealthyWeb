<template>
  <div class="profile-dropdown">
    <div class="user-info">
      <img v-if="currentImgUser.length > 0" class="img-user" v-bind:src="currentImgUser"/>
      <default-avatar
        v-if="currentImgUser.length == 0"
        style="margin: auto;"
        :name="'S'"
        :size="15" :width='44'
        :radius="50" :height='44'
      >
      </default-avatar>
      <div class="name-user">
        <p>{{currentUser.firstName}}</p>
        <p class="type-user">{{currentUser.type}}</p>
      </div>
    </div>

    <div class="bottom-dropdown">
      <div class="sub-bottom" v-on:click ="accountSetting">{{$t('navbar.Account Settings')}}</div>
      <div class="sub-bottom" v-on:click ="logout">{{$t('navbar.Logout')}}</div>
    </div>
  </div>

</template>

<script>
import DefaultAvatar from '../../../../views/components/default-avatar/DefaultAvatar';
import VaDropdown from "../../../../views/components/va-dropdown/VaDropdown";
import Auth from '@/security/Authentication';
export default {
  components: {
    VaDropdown,
    DefaultAvatar
  },
  name: "profile-dropdown",
  data() {
    return {
      
    };
  },
  props: {
    options: {
      type: Array,
      default: () => [
        {
          name: 'profile',
          redirectTo: '',
        },
        {
          name: 'logout',
          redirectTo: 'login',
        },
      ],
    },
    isShowProfileDropdown : false,
    currentUser: {},
    currentImgUser:""
  },
  methods: {
    logout(){
      Auth.logout();
      this.$router.push({ path: '/login' });
    },
    accountSetting(){
      this.$emit("closeDropdown")
      this.$router.push({ path: '/account-settings' }).catch(err => {});
    }
  }
};
</script>

<style lang="scss">
.profile-dropdown {
  border: solid 0.5px #f3f3f3;
  width: 280px;
  background-color : #f3f3f3;
  position: absolute;
  right: 10px;
  top: 70px;
  float: right;
  text-align: center;

  .user-info{
    border-bottom: 0.5px #353535;
    
    
    .name-user{
      color: #353535;
      font-size: 15px;
      .type-user{
        color: #353535;
        font-size: 13px;
      }
    }
    .img-user {
      height: 44px;
      width: 44px;
      border-radius: 50%;
    }
    
  }

  .bottom-dropdown{
    color: #353535;
    font-size: 15px;
    .sub-bottom{
      line-height: 40px;
      margin: auto;
      display: block;
      height: 40px;

      &:hover {
        cursor: pointer;
      }
    }
    :hover{
      background-color: #36ACB2;
      color: white;
    }
  }
}

.title-dialog {
  display: flex;
  align-items: center;
  span {
    font-weight: bold;
    width: 50%;
    text-align: left;
    font-size: 16px;
  }

  img {
    float: right;
    cursor: pointer;
  }
}
</style>