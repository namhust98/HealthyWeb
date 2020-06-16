<template>
  <el-alert
    :title=title
    :type=type
    :description=description
    show-icon
    center
  ></el-alert>
</template>

<script>
export default {
    name: "alert-vue",
    props: {
        title: String,
        type: String,
        description: String,
    }
};
</script>

<style lang="scss">
</style>