import axios from 'axios';
import auth from '@/security/Authentication';
import router from '@/router';

let createRequest = (baseURL) => {

    const request = axios.create({
        baseURL: baseURL,
        timeout: 10000,
    });

    request.interceptors.response.use(response => {
        return response.data;
    }, error => {

        /**
         * perform redirect to login page when server response with status 401 ( un authorization )
         *
         */
        if (error && error.response && error.response.status == 401 ) {
            auth.logout(); // call logout to remove current user & token.
            router.push({path: "login"});
        }

        if (error && error.response && error.response.status == 400 ) {
            if (error.response.data.code == 1001) {
                auth.logout(); // call logout to remove current user & token.
                router.push({path: "auth/login"});
            }
        }
        
        return Promise.reject(error);
    });

    request.interceptors.request.use(function (config) {

        /**
         * add Authorization header to request if user authenticated, run before sent request
         */
        if (auth.isAuthenticated()) {
            const accessToken = auth.getAccessToken();
            config.headers = {
                'Access-Token': `${accessToken}`,
                'Content-Type': 'application/json'
            }
        }
        return config;

    }, function (error) {
        return Promise.reject(error);
    });

    return request;
};
const AuthAppRequest = createRequest(process.env.ROOT_API);

export {AuthAppRequest};
