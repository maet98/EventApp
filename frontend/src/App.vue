<template>
    <div id="App" >
        <button class="logout" @click="logout" v-if="logged">
            Cerrar Sesion
        </button>
        <div id="nav" class="colorize" v-if="auth">
            <router-link to="/">Dashboard</router-link> |
            <router-link to="/usuarios">Usuario</router-link> |
            <router-link to="/compras">Ver Compras</router-link>
        </div>
        <div class="colorize" v-else>
            <h2> {{ hello }} </h2>
        </div>
        <router-view />
    </div>
</template>

<script>

export default {
    methods: {
        logout() {
            this.$store.commit("changeUser",null);
            this.$store.commit("toggle", false);
            localStorage.removeItem("token");
            localStorage.removeItem("user");
            this.$router.push("/login");
        }
    },
    computed: {
        auth() {
            if(this.$store.state.user !== null ) {
                return this.$store.state.user.role == "ROLE_ADMIN" && this.$store.state.token;
            } else {
                return false;
            }
        },
        logged() {
            return this.$store.state.token;
        },
        hello() {
            if(this.$store.state.user !== null)
                return "Bienvenido, "+ this.$store.state.user.username;
        }
    }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  text-align: center;
}

.colorize {
  background: #fcdab7;
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

.logout {
    position: absolute;
    top: 0px;
    right: 0px;
    background: red;
    color: white;
    padding: 10px;
    border-radius: 4px;
    margin: 8px;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>

