import Vue from 'vue'
import Vuex from 'vuex'
import task from './modules/task'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    task
  }
})
