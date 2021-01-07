<template>
    <v-container>
        <div v-if="auth">
          <v-row class="small" no-gutters justify="space-around" align-content="center">
              <v-col class="mini">
                <h3 style="text-align: center;">Todas las ventas por dia</h3>
                <line-chart :options="option1" :chart-data="datacollection"></line-chart>
              </v-col>
              <v-col class="mini">
                  <h3 style="text-align: center;">Ventas de hoy</h3>
                <bar-chart :chart-data="datacollection2" :options="option2"></bar-chart>
              </v-col>
          </v-row>
          <v-row class="small" no-gutters justify="center" align="center">
              <v-col cols="12" sm="6">
                  <h3 style="text-align: center;">Solicitudes Pendientes</h3>
                <bar-chart :chart-data="datacollection3" :options="option3"></bar-chart>
              </v-col>
          </v-row>
        </div>
      <UserView v-else/>
    </v-container>
</template>

<script>
    import LineChart from './LineChart.js'
    import UserView from "./UserView.vue";
    import BarChart from "./BarChart";


  export default {
    components: {
        LineChart,
        UserView,
        BarChart
    },
    data () {
      return {
        datacollection: null,
        datacollection2: null,
        datacollection3: null,
        option2: null,
        option3: {
              title: {
                  text: 'Solicitudes pendientes'
              },
              scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true,
                        stepSize: 1
                    }
                }]
              }
        },
          option1 : {
              title: {
                  text: 'Total de ventas por dia'
              },
              scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true,
                        stepSize: 20
                    }
                }]
              }
          },
        data: [],
        labels: []
      }
    },
    mounted () {
        this.getAll();
        this.getToday();
        this.getSolicitudes();
    },
    methods: {
        getSolicitudes() {
            window.axios("event/compras/solicitudes")
                .then(ans => {
                    let arr = ans.data;
                    let lab = [];
                    let val = [];
                    for(let i = 0;i < arr.length;i++) {
                        lab.push(new Date(arr[i].fecha).toLocaleDateString());
                        val.push(arr[i].count);
                    }
                    this.datacollection3 = {
                        labels: lab,
                        datasets: [
                            {
                              label: 'Solicitudes pendientes por dias',
                              backgroundColor: '#4287f5',
                              data: val
                            }
                        ]
                    }
                   
                })
        },
        getAll() {
            window.axios("event/compras/purchases")
                .then( ans => {
                    let arr = ans.data;
                    let lab = [];
                    let val = [];
                    for(let i = 0;i < arr.length;i++) {
                        lab.push(new Date(arr[i].fecha).toLocaleDateString());
                        val.push(arr[i].count);
                    }
                    this.labels = lab;
                    this.data = val;
                    this.datacollection = {
                        labels: this.labels,
                        datasets: [
                            {
                              label: 'Total de compra por dia',
                              backgroundColor: '#4287f5',
                              data: this.data
                            }
                        ]
                    }
                }).catch(err => {
                    alert("Couldn't get all");
                })
        },
        getToday() {
            window.axios("event/compras/today")
                .then(ans => {
                    this.datacollection2 = {
                        labels: [ ans.data.today ],
                        datasets: [
                            {
                              label: 'Cantidad de compras de hoy',
                              backgroundColor: '#4287f5',
                              data: [ ans.data.count ]
                            }
                        ],
                    }
                    this.option2 = {
                        scales: {
                            yAxes: [{
                                ticks: {
                                    beginAtZero: true,
                                    stepSize: 1
                                }
                            }]
                        }
                    }
                })
        },
      fillData () {
        this.datacollection = {
            labels: this.labels,
            datasets: [
            {
              label: 'Data One',
              backgroundColor: '#4287f5',
              data: this.data
            }
          ]
        }
      },
          getRandomInt () {
            return Math.floor(Math.random() * (50 - 5 + 1)) + 5
          },
        refresh() {
            this.datacollection = {
                labels: this.labels,
                datasets: [
                    {
                      label: 'Data One',
                      backgroundColor: '#4287f5',
                      data: this.data
                    }
                ]
            }
        }
    },
      computed: {
          auth() {
              return this.$store.state.user.role === "ROLE_ADMIN";
          }
      }
  }
</script>

<style>
  .small {
    background: white;
    border-radius: 10px;
    margin: 30px;
  }
  .mini {
      margin: 10px;
      max-width: 400px;
  }
</style>
