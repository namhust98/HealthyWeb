<template>
  <div class="container">
    <el-row>
      <div class="user-name">{{answer.userName}}</div>
    </el-row>
    <el-row>
      <div class="content">{{answer.content}}</div>
    </el-row>
  </div>
</template>
<script>
export default {
  name: "app-answer",
  props: {
    answer: {}
  },
  data() {
    return {
      userName: "masterboss",
      content:
        "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using"
    };
  }
};
</script>
<style lang="scss" scoped>
.container {
  margin-top: 10px;
  .user-name {
    font-weight: bold;
    text-align: left;
  }
  .content {
    margin-left: 10%;
    text-align: left;
  }
}
</style>