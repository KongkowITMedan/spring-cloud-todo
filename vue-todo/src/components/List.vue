<template>
  <div class="columns is-multiline">
    <div class="column is-4" v-for="(task, index) in activeTasks" :key="index">
      <card :task="task"></card>
    </div>
  </div>
</template>

<script>
import Card from './Card.vue'

export default {
  name: 'list',

  components: {
    Card
  },

  computed: {
    activeTasks () {
      return this.$store.getters['task/active']
    }
  }
}
</script>

