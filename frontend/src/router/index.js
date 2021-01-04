import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Usuario from "../views/Usuario.vue";
import VerCompras from "../views/VerCompras.vue";
import store from "../store/index";
import Login from "../views/Login.vue";

Vue.use(VueRouter);


const isAuthenticated = (_, __, next) => {
  if (store.state.token) {
    next();
  } else {
      next({
          name: "Login"
      });
  }
};


const ifNotAuthenticated = (_, __, next) => {
  if (!store.state.token) {
    next();
  } else {
      next({
          name: "Home"
      })
  }
};


const routes = [
    {
        path: "/",
        name: "Home",
        component: Home,
        beforeEnter: isAuthenticated
    },
    {
        path: "/compras",
        name: "Compras",
        component: VerCompras,
        beforeEnter: isAuthenticated
    },
    {
        path: "/usuarios",
        name: "Usuarios",
        component: Usuario,
        beforeEnter: isAuthenticated
    },
    {
        path: "/login",
        name: "Login",
        component: Login,
        beforeEnter: ifNotAuthenticated
    }
];

const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes
});

export default router;
