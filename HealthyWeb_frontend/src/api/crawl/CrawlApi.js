import { AuthAppRequest as request } from "@/api/Request";

export default {
    getInfo() {
        return request.get(`/api/v1/crawl`);
    }
}