<template>
    <div id="App" >
        <div id="nav" v-if="auth">
            <router-link to="/">Dashboard</router-link> |
            <router-link to="/usuarios">Usuario</router-link> |
            <router-link to="/compras">Ver Compras</router-link>
        </div>
        <router-view />
    </div>
</template>

<script>

export default {
    computed: {
        auth() {
            if(this.$store.state.user !== null ) {
                return this.$store.state.user.role == "ROLE_ADMIN" && this.$store.state.token;
            } else {
                return false;
            }
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
  padding: 30px;
  background: #fcdab7;
  text-align: center;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

.logout {
    position: absolute;
    top: 0px;
    right: 0px;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>

