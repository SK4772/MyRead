import Vue from 'vue';
import App from './App.vue';
import { createApp } from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from './router';
import store from './store';
import 'lib-flexible/flexible'
import axios from 'axios';
import sd from "@/assets/font.css"
import youdao from './utils/youdao'


// app.config.globalProperties.$getMp3Url = youdao
Vue.use(ElementUI);
Vue.prototype.$axios = axios;
Vue.prototype.$getMp3Url = youdao

new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store

});