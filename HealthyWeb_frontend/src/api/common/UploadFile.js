// import { AuthAppRequest as request } from '@/api/Request';
import { AuthAppRequest as requests } from '../Request.js';

class UploadFilesService {
    uploadFile(file, type) {
        return requests.post(`/uploadFile?type=${type}`, file);
    }
}
export default new UploadFilesService();