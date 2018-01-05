<template>
  <div class="card">
    <div class="card-content" >
      <text class="textarea" v-model="content"></text>      
    </div>   
  </div>
</template>

<script>
export default {
  name: 'search',

  props: [],

  data () {
    return {
      content: ''
    }
  },

  methods: {    
  }
}
</script>

<style scoped>
.no-padding {
  padding: 0;
}

a {
  color: #3273dc;
}
</style>
