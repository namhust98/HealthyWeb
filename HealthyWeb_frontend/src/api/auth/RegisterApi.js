import { AuthAppRequest as request } from '@/api/Request';

const registerUrl = "/api/v1/register";
export default {
    register(data) {
        console.log(JSON.stringify(data))
        return request({
            url: registerUrl,
            method: "POST",
            data: JSON.stringify(data),
            headers: {
                'Content-Type': 'application/json'
            }
        });
    },
    confirmCode(email, code) {
        return request.post(`/api/v1/confirm-user-counselor?email=${email}&code=${code}`)
    }
}