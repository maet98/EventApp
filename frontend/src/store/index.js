import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        apiUrl: process.env.APIURL || "http://localhost:8989/",
        token: true,
        user: null,
        events: []
    },
    mutations: {
        initial (state, payload) {
            state.events = payload;
        },
        addEvent (state, payload) {
            state.events.push(payload);
        },
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
