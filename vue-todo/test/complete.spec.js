/* eslint-env jest */
import { mount } from 'avoriaz'
import Complete from '../src/components/Complete.vue'
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

describe('Complete.vue', () => {
  let getters
  let store
  const task = {
    id: 2,
    content: 'fix bug#2',
    isComplete: true,
    isEditable: false
  }

  beforeEach(() => {
    getters = {
      complete: () => [task]
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
    const wrapper = mount(Complete, {store})
    expect(wrapper.vm.completeTasks).toEqual([task])
    expect(wrapper.find('.card > .card-content > .subtitle')[0].text()).toBe(task.content)
  })
})
