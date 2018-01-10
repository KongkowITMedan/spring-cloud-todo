<template>
    <div class="modal" :class="{'is-active': active}">
      <div class="modal-background"></div>
      <div class="modal-card">
        <header class="modal-card-head">
          <p class="modal-card-title">{{title}}</p>
          <button class="delete" aria-label="close" @click="close"></button>
        </header>
        <section class="modal-card-body" v-html="message">
        </section>
        <footer class="modal-card-foot">
          <button class="button is-success" @click="close">Close</button>
        </footer>
      </div>
    </div>
</template>

<script>
export default {
  name: 'messagebox',
  props: {
    title: {
      type: String,
      required: true,
    },
    message: {
      type: String,
      required: true,
    },
  },
  data () {
    return {
      active: false
    }
  },
  methods: {
    show () {
      this.active = true
    },
    close () {
      this.active = false
    }
  }
}
</script>
