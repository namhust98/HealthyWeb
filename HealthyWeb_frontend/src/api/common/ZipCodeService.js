import { AuthAppRequest as requests } from '../Request.js';

class ZipCodeService {
    findZipCode(code) {
        return requests.get(`/findZipCode?code=${code}`);
    }
}
export default new ZipCodeService();