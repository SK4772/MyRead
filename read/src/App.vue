<template>
  <div id="app">
  
    <router-view/>
    <!-- <HelloWorld msg="Welcome to Your Vue.js App"/> -->
    <!-- <lay-out></lay-out> -->
  </div>
</template>

<script>
// import LoginVue from './views/Login.vue'
// import HelloWorld from './components/HelloWorld.vue' 
// import LayOut from './components/LayOut.vue'


export default {
  name: 'App',
  components: {
    // LoginVue
    //  LayOut
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  
}
</style>
