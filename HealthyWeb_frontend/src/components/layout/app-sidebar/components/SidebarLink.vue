<template>
  <router-link
    :class="computedLinkClass"
    @mouseenter.native="updateHoverState(true)"
    @mouseleave.native="updateHoverState(false)"
    :style="computedLinkStyles"
    active-class="va-sidebar-link--active"
    :to="to"
    :target="target"
  >
    <i class="va-sidebar-link__content__icon" :class="icon" :style="computedIconStyles" :name="icon"></i>
    <!-- <b-icon :icon="iconClass" class="va-sidebar-link__content__icon" :style="computedIconStyles" ></b-icon> -->
    <!-- <va-icon
      v-if="icon"
      class="va-sidebar-link__content__icon"
      :style="computedIconStyles"
      :name="icon"
    />-->
    <div class="va-sidebar-link__content__title">
      <slot name="title" />
      {{title}}
    </div>
  </router-link>
</template>

<script>
import { getHoverColor } from "./../../../../services/color-functions";
import { ColorThemeMixin } from "../../../../services/ColorThemePlugin";
// import VaIcon from '../../../../vuestic-theme/vuestic-components/va-icon/VaIcon'
export default {
  name: "sidebar-link",
  components: {},
  mixins: [ColorThemeMixin],
  props: {
    to: {
      type: [Object, String],
      default: ""
    },
    target: {
      type: String,
      default: "_self"
    },
    icon: {
      type: [String, Array]
    },
    title: {
      type: String
    },
    activeByDefault: {
      type: Boolean
    },
    minimized: {
      type: Boolean
    }
  },
  data() {
    return {
      isHovered: false,
      isActive: this.activeByDefault
    };
  },
  watch: {
    $route(route) {
      this.updateActiveState();
    }
  },
  computed: {
    computedLinkClass() {
      return {
        "va-sidebar-link": true,
        "va-sidebar-link--minimized": this.minimized
      };
    },
    computedLinkStyles() {
      if (this.isHovered || this.isActive) {
        return {
          color: "#fff",
          backgroundColor: getHoverColor("#000"),
          borderColor: this.isActive ? "#36ACB2" : "transparent"
        };
      } // else <- controlled by CSS (color in rgba)
    },
    computedIconStyles() {
      return this.isHovered || this.isActive
        ? { color: "#fff" }
        : { color: "black" };
    }
  },
  methods: {
    updateHoverState(isHovered) {
      this.isHovered = isHovered;
    },
    updateActiveState() {
      this.$nextTick(() => {
        this.isActive = this.$route.name === this.to.name;
        if(this.$route.name == 'orderDetail' && this.to.name == 'orderManager'){
          this.isActive = true;
        }
      });
    }
  },
  mounted() {
    this.updateActiveState();
  }
};
</script>

<style lang="scss">
.va-sidebar-link {
  position: relative;
  min-height: 2.5rem;
  cursor: pointer;
  padding-left: 0.75rem;
  padding-top: 0.75rem;
  padding-bottom: 0.75rem;
  display: flex;
  align-items: center;
  text-decoration: none;
  border-left: 0.25rem solid transparent;
  color: #353535;
  &__content {
    &__icon {
      width: 1.5rem;
      min-width: 1.5rem;
      text-align: center;
      font-size: 18px;
      margin-right: 0.5rem;
    }
    &__title {
      line-height: 1.5em;
    }
  }
  &--minimized {
    .va-sidebar-link__content {
      &__title {
        display: none;
      }
    }
  }
}
</style>