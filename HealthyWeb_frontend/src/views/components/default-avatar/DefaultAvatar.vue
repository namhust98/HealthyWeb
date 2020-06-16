<template>
  <div
    class="default-avatar"
    v-bind:style="{ 'border-radius': radius + '%', 
      fontSize: size + 'px', 
      'height': height+'px', 
      'width': width+'px' }"
  >{{ name }}</div>
</template>

<script>
export default {
  name: "default-avatar",
  props: {
    name: {
      type: String,
      required: false
    },
    size: {
      type: Number,
      required: false
    },
    radius: {
      type: Number,
      required: false
    },
    height: {
      type: Number,
      required: false
    },
    width: {
      type: Number,
      required: false
    }
  },
  mounted() {}
};
</script>

<style lang="scss" scoped>
.default-avatar {
  text-transform: uppercase;
  width: 100%;
  height: 100%;
  background-color: white;
  display: flex;
  justify-content: center;
  align-items: center;
  color: black;
}
</style>