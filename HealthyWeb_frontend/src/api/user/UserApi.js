import { AuthAppRequest as request } from "@/api/Request";

export default { 
    getChecklistDetail(param){
        return request.put(`/api/v1/changeInfor`, param);
    }
}