<template>
  <div>
    <div class="va-dropdown__anchor" @click="onAnchorClick()">
      <slot name="anchor" />
    </div>

    <div class="va-dropdown__content"
     v-if="showContent">
      <div
        v-if="keepAnchorWidth"
        ref="anchorWidthContainer"
        class="va-dropdown__anchor-width-container"
        :style="anchorWidthContainerStyles"
      >
        <slot />
      </div>
      <slot v-else />
    </div>
  </div>
</template>

<script>
export default {
  name: "va-dropdown",
  data() {
    return {
      isClicked: false,
      popperInstance: null,
      keepAnchorWidth: Boolean
    };
  },
  watch: {
    showContent: {
      immediate: true,
      handler(showContent) {
        this.handlePopperInstance();
      }
    }
  },
  prop: {
    disabled: Boolean,

    closeOnAnchorClick: {
      type: Boolean,
      default: true
    }
  },

  computed: {
    anchorWidthContainerStyles() {
      return {
        width: this.anchorWidth + "px",
        maxWidth: this.anchorWidth + "px"
      };
    }
  },

  methods: {
    handlePopperInstance() {
      if (!this.showContent) {
        return
      }
    },

    onAnchorClick() {
      alert("lasdfj");
      this.$emit("anchorClick");
      if (this.disabled) {
        return;
      }

      if (this.isClicked && !this.closeOnAnchorClick()) {
        return;
      }
      this.isClicked = !this.isClicked;
    }
  }
};
</script>

<style lang="scss">
.va-dropdown {
  &__content {
    z-index: 100;
  }
}
</style>