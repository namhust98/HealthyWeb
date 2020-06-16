import { AuthAppRequest as request } from '@/api/Request';

const loginUrl = "/api/v1/sign-in";
export default {
    login(data) {
        console.log(JSON.stringify(data))
        return request({
            url: loginUrl,
            method: "POST",
            data: JSON.stringify(data),
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }
}