<template>
  <div>
    <app-header></app-header>
    <section class="section">
      <div class="container">
        <router-view></router-view>
      </div>
    </section>
  </div>
</template>

<script>
import AppHeader from './components/Header.vue'

export default {
  name: 'app',

  components: {
    AppHeader
  }
}
</script>
