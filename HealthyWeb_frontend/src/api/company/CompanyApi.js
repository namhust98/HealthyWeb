import {AuthAppRequest as request} from "@/api/Request";
export default {
    getYourCompany(){
        return request.get(`/api/v1/getYourCompany`);
    },
    updateYourCompany(company){
        return request.put(`/api/v1/updateYourCompany`, company);
    },

    createMember(cleaner) {
        return request.post(`/api/v1/createMember`, cleaner);
    },

    getMemberInCompany() {
        return request.get(`/api/v1/getMember`);
    },

    getMemberInCompanyByName(searchName) {
        return request.get(`/api/v1/getMember?searchName=${searchName}`);
    },

    updateMemberInCompany(userMemberRequest) {
        return request.put(`api/v1/updateMemberInCompany`, userMemberRequest)
    },

    removeMemberInCompany(memberId) {
        return request.put(`/api/v1/removeMember?memberId=${memberId}`);
    }
}