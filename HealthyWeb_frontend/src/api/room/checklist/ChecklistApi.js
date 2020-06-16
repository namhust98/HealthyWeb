import { AuthAppRequest as request } from "@/api/Request";

export default {
    getChecklistDetail(checklistId) {
        return request.get(`/api/v1/checklistDetail?checklistId=${checklistId}`);
    },
    addChecklist(requestBody) {
        return request.post(`/api/v1/checklist`, requestBody);
    },
    updateChecklist(requestBody) {
        return request.put(`/api/v1/checklist`, requestBody);
    },
    deleteChecklist(checklistId) {
        return request.delete(`/api/v1/checklist/${checklistId}`);
    },

    changeDefaultChecklist(checklistId, roomId) {
        return request.put(`/api/v1/changeChecklistDefault?checklistId=${checklistId}&roomId=${roomId}`);
    }
}