import {AuthAppRequest as request} from "@/api/Request";
export default {
    getPaymentHistoryByYear(year){
        return request.get(`/api/v1/paymentHistoryByYear?year=${year}`);
    },
}