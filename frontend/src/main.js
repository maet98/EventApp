import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import DatetimePicker from 'vuetify-datetime-picker';
import axios from "axios"

Vue.config.productionTip = false;

Vue.use(DatetimePicker)

const instance = axios.create({
  baseURL: process.env.APIURL || "http://localhost:8989/",
    timeout: 1000,
    headers: { 'Authorization' : localStorage.getItem("token") }
});

window.axios = instance;

if(localStorage.getItem("token") === null) {
    store.dispatch("toggle", false);
}

if(localStorage.getItem("user") !== null) {
    console.log("exist");
    console.log(localStorage.getItem("user"));
    store.commit("changeUser", JSON.parse(localStorage.getItem("user")));
}

new Vue({
    router,
    store,
    vuetify,
    render: h => h(App)
}).$mount("#app");
