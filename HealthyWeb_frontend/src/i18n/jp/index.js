import login from './login';
import common from './common';
import setupFee from './setupFee';
import room from './room';
import editChecklist from './editChecklist';
import navbar from './navbar';
import buildingManagement from "./buildingManagement";
import member from "./member";
import report from "./report";
import accountSetting from "./accountSetting";
import register from "./register";

const jpMessage = {
    ...login,
    ...common,
    ...setupFee,
    ...room,
    ...editChecklist,
    ...navbar,
    ...buildingManagement,
    ...member,
    ...report,
    ...accountSetting,
    ...register
}
export default jpMessage;