import Vue from 'vue'
import Router from 'vue-router'
import List from '../components/List.vue'
import Complete from '../components/Complete.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  linkExactActiveClass: 'is-active',
  routes: [
    {
      path: '/',
      name: 'List',
      component: List
    },
    {
      path: '/complete',
      name: 'Complete',
      component: Complete
    }
  ]
})