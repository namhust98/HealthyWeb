import {AuthAppRequest as request} from "@/api/Request";
export default {
    getBuildingManager(page, size){
        return request.get(`/api/v1/buildings?page=${page}&size=${size}`);
    },
    getOrderDetailByRoomIdAndDate(roomId, date){
        return request.get(`/api/v1/getDetailOrderForHost?roomId=${roomId}&date=${date}`);
    },
    getOrderForHostBuilding(buildingId, date){
        return request.get(`/api/v1/getOrderForHostBuilding?buildingId=${buildingId}&date=${date}`);
    }
}