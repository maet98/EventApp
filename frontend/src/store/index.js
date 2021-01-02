import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        apiUrl: process.env.APIURL || "http://localhost:8989/"
    },
    mutations: {},
    actions: {},
    modules: {}
});
