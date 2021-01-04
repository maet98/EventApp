import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        apiUrl: process.env.APIURL || "http://localhost:8989/",
        token: true,
        user: null
    },
    mutations: {
      toggle (state, payload) {
          state.token = payload;
      },
        changeUser(state, payload) {
            state.user = payload;
        }
    },
    actions: {
      toggle (state, payload) {
          state.isAuthenticated = payload;
      }
    },
    modules: {
    }
});
