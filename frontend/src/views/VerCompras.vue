<template>
    <div class="data">
        <h2 style="text-align: center;"> Compras Realizadas </h2>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        class="search"
        single-line
        hide-details
      ></v-text-field>
      <v-data-table
        :headers="headers"
        :items="events"
        :search="search"
        :items-per-page="5"
          :loading="loading"
          loading-text="Cargando..."
        class="elevation-1"
      >
      </v-data-table>
    </div>
</template>

<script>

  export default {
    data () {
      return {
          loading: false,
          search: '',
        headers: [
          { text: 'Cliente', value: 'usuario' },
          { text: 'Fecha de la reservacion', value: 'fechaCompra' },
          { text: 'Fecha del evento', value: 'fechaEvento' },
          { text: 'Costo Total', value: 'total' },
          { text: 'Plan', value: 'plan' }
        ],
      }
    },
      mounted() {
          this.getEvents();
      },
      methods: {
          getEvents() {
              this.loading = true;
              if(this.$store.state.user.role === "ROLE_ADMIN") {
                  window.axios.get("/event/compras")
                      .then(ans => {
                          this.loading = false;
                          this.events = ans.data;
                      })
              } else {
                  window.axios.get("/event/compras/" + this.$store.state.user.username)
                      .then(ans => {
                          this.loading = false;
                          this.events = ans.data;
                      })
              }
          }
      },
      computed: {
          events: {
              get() {
                  return this.$store.state.events;
              },
              set(value) {
                  this.$store.commit('initial',value);
              }
          }
      }
  }
</script>

<style>
.data {
    background: white;
    margin: 10px;
    border-radius: 10px;
}

.search {
    width: 250px;
    margin-left: 20px;
    text-align: right;
}
</style>
