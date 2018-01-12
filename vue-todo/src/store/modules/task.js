import _ from 'lodash'
import axios from 'axios'

function commitToBackend (task, state) {
  axios.put('http://localhost:8000/api/todo', task, {headers: {'Content-Type': 'text/json'}})
    .then(res => {      
      Object.assign(state, res.data)
    })
    .catch(err => {
      console.log(err)
    })
}

function addToBackend(task, state) {
  axios.post('http://localhost:8000/api/todo', task, {headers: {'Content-Type': 'text/json'}})
    .then(res => {     
      Object.assign(state, res.data)
      state.editable = true
    })
    .catch(err => {
      console.log(err)
    })
}

export default {
  namespaced: true,

  state: {
    all: [
    ]
  },

  getters: {
    active: state => state.all.filter(task => !task.complete),
    complete: state => state.all.filter(task => task.complete)
  },

  mutations: {
    enableEdit (state, payload) {      
      const index = _.findIndex(state.all, ['id', payload.id])
      const task = state.all[index]
      task.editable = true
    },

    disableEdit (state, payload) {
      const index = _.findIndex(state.all, ['id', payload.id])
      const task = state.all[index]
      task.editable = false
    },

    updateContent (state, payload) {
      const index = _.findIndex(state.all, ['id', payload.id])
      Object.assign(state.all[index], payload)
    },

    loadTask(state, payload) {
      state.all.push(payload)
    }
  },

  actions: {
    loadInitialTasks (context) {
      axios.get('http://localhost:8000/api/todo')
        .then(res => {         
          res.data.forEach(task => { context.commit('loadTask', task) })
        })
        .catch(err => {
          console.log(err)
        })
    },

    newTask (context) {
      axios.post('http://localhost:8000/api/todo', {content: '',
          editable: true,
          complete: false})
        .then(res => {         
          var task = res.data
          context.commit('loadTask', task)
        })
        .catch(err => {
          console.log(err)
        })
    },

    saveTask (context, task) {     
      var newTask = Object.assign({}, task)      
      newTask.editable = false
      axios.put('http://localhost:8000/api/todo', newTask)
        .then(res => {         
          context.commit('updateContent', res.data)
        })
        .catch(err => {
          console.log(err)
        })
    }
  }
}
