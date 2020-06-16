<template>
  <div id="body"></div>
</template>
<script>
import CrawlApi from "@/api/crawl/CrawlApi";
export default {
  name: "home",
  data() {
    return {
      info: ""
    };
  },
  mounted() {
    CrawlApi.getInfo().then(res => {
      document.getElementById("body").innerHTML = res;
    });
  }
};
</script>
<style lang="scss" scoped>
.body {
  max-height: 600px;
  overflow-y: scroll;
}
</style>