<template>
  <div data-app justify="center">
    <v-dialog
      v-model="dialog"
      persistent
      max-width="400"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="primary"
          dark
          v-bind="attrs"
          v-on="on"
        >
          Hacer una reserva
        </v-btn>
      </template>
      <v-card>
        <v-card-title class="headline">
          Reservar Evento
        </v-card-title>
        <v-card-text>
            Costo : {{ compra.total }}
            <div class="separate">
                <h3> Fecha del Evento: </h3>
                <v-date-picker v-model="compra.fechaEvento"></v-date-picker>
            </div>
            <div class="separate">
                <v-select
                    :items="plans"
                    v-model="compra.plan"
                    label="Selecciona un plan"
                    dense
                    />
            </div>
        </v-card-text>
        <v-card-actions>

          <div ref="paypal"></div>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="dialog = false"
          >
            Salir
          </v-btn>
          <v-btn
            color="green darken-1"
            text
            @click="save"
          >
            Reservar
          </v-btn>
              <br/>
            <div ref="paypal"></div>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        dialog: false,
          loaded: false,
          paidFor: false,
          plans: ["Pre-Boda", "Boda", "Cumpleaños", "Video de Evento"],
          costos: {
            "Pre-Boda": 1000,
            "Boda": 5000,
            "Cumpleaños": 3000,
            "Video de Evento": 4000
          },
          compra: {
              usuario: this.$store.state.user.username,
              email: this.$store.state.user.email,
              fechaEvento: new Date().toISOString().substr(0,10),
              fechaCompra: new  Date(),
              plan: '',
              total: 0
          }
      }
    },
    methods: {
        save() {
            const script = document.createElement("script");
            script.src =
              "https://www.paypal.com/sdk/js?client-id=AZAnU-OowpTRoMsqDai1Ttt-dV73am8X1WCYg4qtGGZ4HVoXwSH-boFLmdlb8vkr8R1Q3nVHWNtqSq3W";
            script.addEventListener("load", this.setLoaded);
            document.body.appendChild(script);
            console.log(this.compra);
        },
        setLoaded() {
          this.loaded = true;
          window.paypal
            .Buttons({
              createOrder: (data, actions) => {
                return actions.order.create({
                  purchase_units: [
                    {
                      description: this.compra.plan,
                        amount: {
                        currency_code: "USD",
                        value: this.compra.total
                      }
                    }
                  ]
                });
              },
              onApprove: async (data, actions) => {
                const order = await actions.order.capture();
                this.paidFor = true;
                console.log(order);
                  window.axios.post("event/compras",this.compra)
                      .then(ans => {
                          console.log(ans.data)
                          this.dialog = false;
                      }).catch(err => {
                          console.log(err);
                          this.dialog = false;
                      })
              },
              onError: err => {
                console.log(err);
              }
            })
            .render(this.$refs.paypal);
        }
    },
      watch: {
          compra:{
              deep: true,
              handler: function(val, oldVal) {
                  if(val.plan !== "") {
                      this.compra.total =this.costos[val.plan]
                  }
              }
          }
      }
  }
</script>

<style scoped> 
.separate  {
    margin: 12px
}
</style
