<template>
    <div>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      <v-data-table
        :headers="headers"
        :items="events"
          :search="search"
        :items-per-page="5"
        class="elevation-1"
      >
        <template v-slot:item.fecha="{ item }">
          <span> {{item.fecha.toLocaleDateString()}} </span>
        </template>
      </v-data-table>
    </div>
</template>

<script>
    import axios from "axios"
    let apiUri = ""

  export default {
    data () {
      return {
          search: '',
        headers: [
          { text: 'Cliente', value: 'cliente' },
          {
            text: 'Plan',
            value: 'plan',
          },
          { text: 'Costo', value: 'costo' },
          { text: 'Fecha del evento', value: 'fecha' }
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
              axios.get(apiUri + "/events")
                  .then(ans => {
                      this.events = ans.data;
                  })
          }
      }
  }
</script>
