<template>
  <div class="columns is-multiline">
    <div class="column is-4" v-for="(task, index) in completeTasks" :key="index">
      <card :task="task"></card>
    </div>
  </div>
</template>

<script>
import Card from './Card.vue'

export default {
  name: 'complete',

  components: {
    Card
  },

  computed: {
    completeTasks () {
      return this.$store.getters['task/complete']
    }
  }
}
</script>


