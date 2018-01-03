/* eslint-env jest */
import { mount } from 'avoriaz'
import Card from '../src/components/Card.vue'
import Vue from 'vue'
import Vuex from 'vuex'
import sinon from 'sinon'

Vue.use(Vuex)

describe('Card.vue', () => {
  let state
  let store
  let mutations
  const task = {
    id: 1,
    content: 'test task',
    isComplete: false,
    isEditable: false
  }

  beforeEach(() => {
    state = {
      all: [task],
    },

    mutations = {
      disableEdit: sinon.stub(),
      enableEdit: sinon.stub(),
      setComplete: sinon.stub(),
      setIncomplete: sinon.stub(),
      updateContent: sinon.stub()
    },

    store = new Vuex.Store({
      modules: {
        task: {
          namespaced: true,
          state,
          mutations
        }
      }
    })
  })

  it('call enableEdit mutation when edit button clicked', () => {
    const wrapper = mount(Card, { store, propsData: { task }})
    wrapper.find('.card-footer-item')[0].trigger('click')
    expect(wrapper.data().content).toBe(task.content)
    expect(mutations.enableEdit.calledOnce).toBe(true)
  })

  it('call setComplete mutation when done button clicked', () => {
    const wrapper = mount(Card, { store, propsData: { task }})
    wrapper.find('.card-footer-item')[1].trigger('click')
    expect(mutations.setComplete.calledOnce).toBe(true)
  })

  it('call setIncomplete mutation when redo button clicked', () => {
    task.isComplete = true
    const wrapper = mount(Card, { store, propsData: { task }})
    wrapper.find('.card-footer-item')[0].trigger('click')
    expect(mutations.setIncomplete.calledOnce).toBe(true)
  })

  it('call updateContent mutation when save button clicked', () => {
    task.isEditable = true
    const wrapper = mount(Card, { store, propsData: { task }})
    wrapper.find('.card-footer-item')[0].trigger('click')
    expect(mutations.updateContent.calledOnce).toBe(true)
  })

  it('call disableEdit mutation when save button clicked', () => {
    task.isEditable = true
    const wrapper = mount(Card, { store, propsData: { task }})
    wrapper.find('.card-footer-item')[0].trigger('click')
    expect(mutations.disableEdit.calledOnce).toBe(true)
  })
})
