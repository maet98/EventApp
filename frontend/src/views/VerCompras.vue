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
        <template v-slot:item.fecha="{ item }">
          <span> {{item.fecha.toLocaleDateString()}} </span>
        </template>
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
          { text: 'Fecha del evento', value: 'fechaEvento' },
          { text: 'Fecha del reservacion', value: 'fechaCompra' },
          { text: 'Costo Total', value: 'total' },
          { text: 'Plan', value: 'plan' }
        ],
        events: [
          {
              plan: "Pre-Boda",
              costo: 1000,
              cliente: "Juan",
              fecha: new Date()
          },

          {
              plan: "Boda",
              costo: 5000,
              cliente: "Pedro",
              fecha: new Date()
          },
          {
              plan: "Cumpleagnos",
              costo: 30000,
              cliente: "Maria",
              fecha: new Date()
          },
          {
              plan: "Video de evento",
              costo: 4000,
              cliente: "Perez",
              fecha: new Date()
          }
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
                          console.log(ans.data);
                          this.events = ans.data;
                      })
              } else {
                  window.axios.get("/event/compras/" + this.$store.state.user.username)
                      .then(ans => {
                          this.loading = false;
                          console.log(ans.data);
                          this.events = ans.data;
                      })
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
    text-align: right;
}
</style>
