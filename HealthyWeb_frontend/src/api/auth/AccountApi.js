import {AuthAppRequest as request} from "@/api/Request";
export default {
    updateUser(user) {
        return request.put(`/update-user`, user);
    },
    changePassword(oldPass, newPass) {
        return request.post(`/change-password?oldPassword=${oldPass}&newPassword=${newPass}`);
    },
    deleteAccount() {
        return request.post(`/delete-account`);
    },
    forgotPass(email) {
        return request.post(`/api/v1/forgot-password?email=${email}`);
    },
    confirmCodeLogin(email, code) {
        return request.get(`/api/v1/confirm-code-login?email=${email}&code=${code}`);
    }

}