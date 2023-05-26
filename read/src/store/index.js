import Vue from 'vue' //引入 Vue
import Vuex from 'vuex' //引入 Vuex
import user from './modules/user' //引入 user module
import word from './word'
import essay from './essay'

 
Vue.use(Vuex)
 
const store = new Vuex.Store({
  modules: {
    user,word, //使用 user.js 中的 action
    essay
  }
})
 
export default store