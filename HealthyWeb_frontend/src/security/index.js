import router from '@/router/index';
import Auth from '@/security/Authentication';

router.beforeEach((to, from, next) => {
    const publicPages = ['/login', '/register', '/forgotPass'];
    if (Auth.isAuthenticated()) {
        next();
    } else {
        if (!publicPages.includes(to.path)) {
            next({ name: 'login' });
        }
    }

    next();
})