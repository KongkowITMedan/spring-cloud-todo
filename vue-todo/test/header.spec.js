/* eslint-env jest */
import { mount } from 'avoriaz'
import Header from '../src/components/Header.vue'
import Vue from 'vue'
import Vuex from 'vuex'
import Router from 'vue-router'
import sinon from 'sinon'

Vue.use(Vuex)
Vue.use(Router)

describe('Complete.vue', () => {
  let mutations
  let getters
  let store
  let router
  const task = {
    id: 2,
    content: 'fix bug#2',
    isComplete: true,
    isEditable: false
  }

  beforeEach(() => {
    getters = {
      complete: () => [task],
      active: () => [task]
    },

    mutations = {
      addTask: sinon.stub(),
    }

    router = new Router({
      routes: [
        {
          path: '/',
          name: 'List',
          component: { name: 'list' }
        },
        {
          path: '/complete',
          name: 'Complete',
          component: { name: 'complete' }
        }
      ]
    })

    store = new Vuex.Store({
      modules: {
        task: {
          namespaced: true,
          getters,
          mutations
        }
      }
    })
  })

  it('render correct counter', () => {
    const wrapper = mount(Header, {store, router})
    expect(wrapper.find('.tag.is-rounded')[0].text()).toBe('1')
    expect(wrapper.find('.tag.is-rounded')[1].text()).toBe('1')
  })

  it('trigger addTask event when add button clicked', () => {
    const wrapper = mount(Header, {store, router})
    wrapper.find('button')[0].trigger('click')
    expect(mutations.addTask.calledOnce).toBe(true)
  })
})
