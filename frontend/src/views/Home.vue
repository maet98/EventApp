<template>
  <div class="small">
    <line-chart :chart-data="datacollection"></line-chart>
    <button @click="addNew()">Randomize</button>
  </div>
</template>

<script>
  import LineChart from './LineChart.js'

  export default {
    components: {
      LineChart
    },
    data () {
      return {
        datacollection: null,
        data: [],
        labels: []
      }
    },
    mounted () {
      this.addNew()
    },
    methods: {
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
        addNew() {
            this.labels.push(this.labels.length + 1)
            this.data.push(this.getRandomInt())
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
    }
  }
</script>

<style>
  .small {
    max-width: 600px;
    margin:  150px auto;
  }
</style>
