<template>
  <div>
    <div class="card">
      <div class="card-content" :class="{ 'no-padding': task.editable }">
        <textarea v-if="task.editable" class="textarea" v-model="content"></textarea>
        <p v-else class="subtitle">{{ task.content }}</p>
      </div>

      <footer class="card-footer">
        <template v-if="task.editable">
          <a @click="updateCard" class="card-footer-item">Save</a>
        </template>
        <template v-else-if="task.complete">
          <a @click="redoTask" class="card-footer-item">Redo</a>
          <a @click="taskTrail" class="card-footer-item">Trail</a>
        </template>
        <template v-else>
          <a @click="editCard" class="card-footer-item">Edit</a>
          <a @click="clearTask" class="card-footer-item">Done</a>
          <a @click="taskTrail" class="card-footer-item">Trail</a>
        </template>
      </footer>
    </div>
    <MessageBox :title="title" :message="message" ref="messageBox" />
  </div>
</template>

<script>
import axios from 'axios'
import MessageBox from './MessageBox.vue'

export default {
  name: 'card',

  components: {
    MessageBox
  },

  props: ['task'],

  data () {
    return {
      content: '',
      title: '',
      message: ''
    }
  },

  methods: {
    editCard () {
      this.$store.commit('task/enableEdit', { id: this.task.id })
      this.content = this.task.content
    },

    updateCard () {
      var task = this.task
      task.content = this.content
      this.$store.dispatch('task/saveTask', task)
      this.$store.commit('task/disableEdit', { id: this.task.id })
    },

    clearTask () {
      var task = Object.assign({}, this.task)
      task.complete = true
      this.$store.dispatch('task/saveTask', task)
    },

    redoTask () {
      var task = Object.assign({}, this.task)
      task.complete = false
      this.$store.dispatch('task/saveTask', task)
    },

    loadTaskTrail (context, task) {
      try {
        var href = task.links.filter(x => x.rel === 'trail')[0].href
        axios.get(href)
          .then(res => {
            context.commit('displayMessageBox', {task: task, trails: res.data})
          })
          .catch(err => {
            console.log(err)
          })
      } catch (e) {
        console.log(e)
      }
    },

    showMessage (title, message) {
      this.title = title
      this.message = message

      this.$refs.messageBox.show()
    },

    taskTrail () {
      var task = this.task
      try {
        var href = task.links.filter(x => x.rel === 'trail')[0].href
        axios.get(href)
          .then(res => {
            var message = '<table>' +
                          '<tr><th style="padding: 0.3em">Change Time</th><th>Message</th></tr>' +
                          res.data.map(x => '<tr>' +
                            '<td style="padding: 0.3em">' + new Date(x.changeTime) + '</td>' +
                            '<td style="padding: 0.3em">' + x.message + '</td>' + 
                            '</tr>').join('') +
                          '</table>'
            this.showMessage(task.content, message)
          })
          .catch(err => {
            this.showMessage(task.content, err)
          })
      } catch (e) {
        console.log(e)
      }
    }
  },
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
