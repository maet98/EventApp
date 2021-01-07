<template>
  <div class="centerdiv">
      <form @submit.prevent="login" class="login-form center">
          Iniciar Sesion
          <h3 v-if="error.length !== 0" class="error">{{error}}</h3>
          <v-text-field label="username" v-model="body.username" />
          <v-text-field type="password" label="password" v-model="body.password" />
            <v-btn
                class="button"
                  :loading="loading"
                  :disabled="loading"
                  color="succes"
                  type="submit"
                >
                    Iniciar Sesion
                  <template v-slot:loader>
                    <span class="custom-loader">
                      <v-icon light>mdi-cached</v-icon>
                    </span>
                  </template>
                </v-btn>
          </form>
      </div>
</template>

<script>
import axios from "axios";
import toast from 'vuetify-toast';

export default {
  data() {
    return {
        error: '',
        body: {
            username: '',
            password: ''
        },
        loading: false,
      logged: false
    };
  },
  methods: {
    login: function() {
        this.loading = true;
      window.axios
        .post("/login", this.body)
        .then(resp => {
            this.loading = false;
            const payload = resp.data;
            console.log(payload);
            localStorage.setItem("token", payload.token);
            localStorage.setItem("user", JSON.stringify(payload.user));
            this.$store.commit('changeUser',payload.user);
            this.$store.commit("toggle", true);
            toast.success('The document has been saved.');
            const instance = axios.create({
              baseURL: this.$store.state.apiUrl,
                timeout: 3000,
                headers: { 'Authorization' : payload.token }
            });

            window.axios = instance;

            this.$router.push("/");
        })
            .catch(err =>{
                this.loading = false;
                this.error = "Malas credenciales";
                console.log(err)
            });
    }
  },
    watch: {
      loader () {
        setTimeout(() => (this.loading = false), 3000)
      },
    },
};
</script>

<style>
.centerdiv {
    position: absolute;
    z-index: 15;
    top: 50%;
    left: 50%;
    width: 800px;
    margin: -100px 0 0 -150px;
}

.error {
    color: red;
    text-align: center;
    animation: alert 1s infinite;
}

@keyframes alert
{
    0%
    {
        color: red;
    }
    50%
    {
        color:#133b5c;
    }
}

.center {
    display: flex;
    align-content: center;
    justify-content: center;
    flex-direction: column;
    border-radius: 10px;
}

.login-form {
    max-width: 500px;
    background: #fcdab7;
    color: #133b5c;
    text-align: center;
    padding: 4px;
}

.button {
    min-width: 100%;
}

body {
    background: #1d2d50
}
.custom-loader {
    animation: loader 1s infinite;
    display: flex;
}
@-moz-keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
}
@-webkit-keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
}
@-o-keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
}
@keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
}
</style>
