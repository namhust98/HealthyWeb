import Vue from 'vue';
import Router from 'vue-router';
import AppLayout from '@/components/layout/AppLayout';
import AuthLayout from '@/components/auth/AuthLayout';
Vue.use(Router)

const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '',
      redirect: { name: 'home' },
    },
    {
      path: '/',
      name: 'AppLayout',
      component: AppLayout,
      children: [
        {
          name: 'home',
          path: 'home',
          component: () => import('../components/home/Home'),
          default: true,
        },
        {
          name: 'profile',
          path: 'profile',
          component: () => import('../components/profile/Profile'),
          default: true,
        },
        {
          name: 'paper',
          path: 'paper',
          component: () => import('../components/menu/Menu'),
          default: true
        },
        {
          name: 'news',
          path: 'news',
          component: () => import('../components/news/News'),
          default: true
        },
        {
          name: 'groups',
          path: 'groups',
          component: () => import('../components/group/Group'),
          default: true
        },
        {
          name: 'management',
          path: 'management',
          component: () => import('../components/management/Management'),
          default: true,
        },
        {
          name: 'logout',
          path: 'logout',
          component: () => import('../components/auth/logout/Logout'),
          default: true,
        },
      ],
    },
    {
      path: '/',
      name: 'AuthLayout',
      component: AuthLayout,
      children: [
        {
          name: 'login',
          path: 'login',
          component: () => import('../components/auth/login/Login'),
          meta: {
            title: 'Login'
          },
          default: true,
        },
        {
          name: 'register',
          path: 'register',
          component: () => import('../components/auth/register-manager/RegisterManager'),
          meta: {
            title: 'Register'
          },
          default: true,
        },
        {
          name: 'forgotPass',
          path: 'forgotPass',
          component: () => import('../components/auth/forgot-password/ForgotPass'),
          meta: {
            title: 'Forgot'
          },
          default: true,
        }
      ],
    },
  ]
})

export default router;