import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Usuario from "../views/Usuario.vue";
import VerCompras from "../views/VerCompras.vue";

Vue.use(VueRouter);

const routes = [
    {
        path: "/",
        name: "Home",
        component: Home
    },
    {
        path: "/compras",
        name: "Compras",
        component: VerCompras
    },
    {
        path: "/usuarios",
        name: "Usuarios",
        component: Usuario
    }

];

const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes
});

export default router;
