import { AuthAppRequest as request } from "@/api/Request";

export default {
    getBuilding(buildingId) {
        return request.get(`/api/v1/building/${buildingId}`);
    },
    getBuildingInfo(buildingId) {
        return request.get(`/api/v1/rooms?buildingId=${buildingId}`);
    },
    getBuildingNames() {
        return request.get(`/api/v1/getBuildingNames`);
    },
    getAllFloorsByUser() {
        return request.get(`/api/v1/getFloorsByUser`);
    },
    changeFloorPositions(floors) {
        return request.put(`/api/v1/changeFloorPositions`, floors);
    },
    createBuilding(buildingRequest) {
        return request.post(`/api/v1/building`, buildingRequest);
    },
    createFloor(floor) {
        return request.post(`/api/v1/floor`, floor);
    },
    getFloorsByBuildingId(buildingId) {
        return request.get(`/api/v1/getFloorsByBuilding?buildingId=${buildingId}`);
    },
    createRoom(room) {
        return request.post(`/api/v1/room`, room);
    },
    updateBuilding(building) {
        return request.put(`/api/v1/building`, building);
    },
    deleteBuilding(buildingId) {
        return request.delete(`/api/v1/building/${buildingId}`);
    },
    deleteFloor(floorId) {
        return request.delete(`/api/v1/floor?floorId=${floorId}`);
    },
    updateFloor(floor) {
        return request.put(`/api/v1/floor`, floor);
    },
    createFloor(floor) {
        return request.post(`/api/v1/floor`, floor);
    },
    updateRoom(room) {
        return request.put(`/api/v1/room`, room);
    },
    deleteRoom(id) {
        return request.delete(`/api/v1/room/${id}`);
    },
    getChecklistDetail(roomId){
        return request.get(`/api/v1/checklist?roomId=${roomId}`);
    }
}