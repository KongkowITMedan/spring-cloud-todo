/* eslint-env jest */
import { mount } from 'avoriaz'
import List from '../src/components/List.vue'
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

describe('List.vue', () => {
  let getters
  let store
  const task = {
    id: 2,
    content: 'fix bug#2',
    isComplete: false,
    isEditable: false
  }

  beforeEach(() => {
    getters = {
      active: () => [task]
    },

    store = new Vuex.Store({
      modules: {
        task: {
          namespaced: true,
          getters
        }
      }
    })
  })

  it('render correct item from getters', () => {
    const wrapper = mount(List, {store})
    expect(wrapper.vm.activeTasks).toEqual([task])
    expect(wrapper.find('.card > .card-content > .subtitle')[0].text()).toBe(task.content)
  })
})
