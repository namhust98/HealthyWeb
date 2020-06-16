<template>
  <aside
    style="background-color: #F6F6F6; border-right: 1px solid rgb(213, 213, 213)"
    :class="computedClass"
  >
    <div class="va-sidebar__menu">
      <div v-if="isAdmin">
        <template v-for="item in itemsAdmin">
          <sidebar-link
            :key="item.name"
            :minimized="minimized"
            :activeByDefault="item.name === $route.name"
            :icon="[ 'sidebar-menu-item-icon vuestic-iconset', item.meta.iconClass ]"
            :iconClass="item.meta.iconClass"
            :to="{ name: item.name }"
          >
            <span slot="title">{{item.displayName}}</span>
          </sidebar-link>
        </template>
      </div>
      <template v-for="(item, key) in items">
        <sidebar-link
          :key="key"
          :minimized="minimized"
          :activeByDefault="item.name === $route.name"
          :icon="[ 'sidebar-menu-item-icon vuestic-iconset', item.meta.iconClass ]"
          :iconClass="item.meta.iconClass"
          :to="{ name: item.name }"
        >
          <span slot="title">{{item.displayName}}</span>
        </sidebar-link>
      </template>
    </div>
  </aside>
</template>

<script>
// import VaSidebar
//   from '../../../vuestic-theme/vuestic-components/va-sidebar/VaSidebar'
import SidebarLink from "./components/SidebarLink";
import { navigationRoutes } from "./NavigationRoutes";
import Auth from "../../../security/Authentication";

export default {
  name: "app-sidebar",
  components: {
    // VaSidebar,
    SidebarLink
    // SidebarLinkGroup,
  },
  props: {
    minimized: {
      type: Boolean,
      required: true
    }
  },
  data() {
    return {
      isAdmin: false,
      items: navigationRoutes.routes,
      itemsAdmin: navigationRoutes.routesAdmin,
    };
  },
  mounted() {
    let user = JSON.parse(Auth.getCurrentUser());
    if(user.type == "admin") {
      this.isAdmin = true;
    }
  },
  computed: {
    computedClass() {
      return {
        "va-sidebar": true,
        "va-sidebar--minimized": this.minimized
      };
    }
  }
};
</script>

<style lang="scss">
.va-sidebar {
  min-height: calc(100vh - 4.0625rem);
  height: calc(100% - 4.0625rem);
  position: absolute;
  width: 18.75rem;
  top: 4.0625rem;
  left: 0;
  -webkit-transition: all 0.3s ease;
  transition: all 0.3s ease;
  overflow-y: auto;

  &__menu {
    max-height: 100%;
    margin-bottom: 0;
    padding-top: 2.5625rem;
    padding-bottom: 2.5rem;
    list-style: none;
    padding-left: 0;

    a:hover {
      text-decoration: none;
    }
  }

  &--minimized {
    left: 0;
    width: 3.5rem;
    .va-sidebar-link-group {
      .va-sidebar-link__content {
        padding-right: 0;
      }
    }
    & + .content-wrap {
      margin-left: 3.5rem;
    }
  }
}
</style>