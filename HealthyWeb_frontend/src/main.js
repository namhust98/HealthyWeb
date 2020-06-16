import Vue from "vue";
import App from "./App";
import router from "./router";
import i18n from "@/i18n";
import ElementUI from "element-ui";
import lang from "element-ui/lib/locale/lang/en";
import locale from "element-ui/lib/locale";
import "element-ui/lib/theme-chalk/index.css";
import "@/security/index";
import Auth from "@/security/Authentication";
import draggable from "vuedraggable";
import VueFacebookPage from 'vue-facebook-page'

Vue.config.productionTip = false;
Vue.prototype.$auth = Auth;
// Vue.use(draggable);
Vue.use(ElementUI);
Vue.use(VueFacebookPage);
locale.use(lang);

Vue.filter("toCurrency", function (value) {
  return "¥" + value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
});

/* eslint-disable no-new */
import store from "./store";
new Vue({
  el: "#app",
  store,
  router,
  i18n,
  components: { App },
  template: "<App/>"
});
