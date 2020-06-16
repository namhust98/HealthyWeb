export const navigationRoutes = {
  root: {
    name: '/',
    displayName: 'navigationRoutes.home',
  },
  routes: [
    // {
    //   name: 'home',
    //   displayName: 'Home',
    //   meta: {
    //     iconClass: 'el-icon-s-home',
    //   },
    // },
    {
      name: 'profile',
      displayName: 'Your Profile',
      meta: {
        iconClass: 'el-icon-s-custom',
      },
    },
    {
      name: 'paper',
      displayName: 'Paper',
      meta: {
        iconClass: 'el-icon-menu',
      },
    },
    {
      name: 'news',
      displayName: 'News',
      meta: {
        iconClass: 'el-icon-chat-line-round',
      },
    },
    {
      name: 'groups',
      displayName: 'Groups',
      meta: {
        iconClass: 'el-icon-office-building',
      },
    },
    {
      name: 'logout',
      displayName: 'Logout',
      meta: {
        iconClass: 'el-icon-switch-button',
      }
    },
  ],
  routesAdmin: [
    {
      name: 'management',
      displayName: 'Management',
      meta: {
        iconClass: 'el-icon-s-operation',
      }
    }
  ]
}