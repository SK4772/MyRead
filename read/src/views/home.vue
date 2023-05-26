<template>
  <el-container>
  <el-aside width="auto"><leftmenu-vue></leftmenu-vue></el-aside>
  <el-container>
    <el-header><navpro></navpro></el-header>
    <el-main><word-vue></word-vue></el-main>
  </el-container>
</el-container>

</template>


<script>
import leftmenuVue from './home/leftmenu.vue'
import navpro from './home/navpro.vue'
import updateFileVue from './pages/updateFile.vue'
import wordVue from './pages/word.vue'



export default {
  components: { 
    navpro,leftmenuVue,
    wordVue,
    updateFileVue
   },

}
</script>

<style>
 .el-header, .el-footer {
    
    background-color: #373737;
    
  }

  .el-header{
    width: 95.5%;
    position: absolute;
    left: 66px;
  }
  
  .el-aside {
    background-color: #373737;
    margin-top:60px;
    color: #333;
    text-align: center;
    line-height: 200px;
    height: 100vh;
  }
  
  .el-main {
    background-color: #E9EEF3;
    color: #1f1f1f;
    text-align: center;
    line-height: 160px;
    background-color: #1f1f1f;
  }

  .el-menu.el-menu--horizontal{
    border: 0px solid transparent;
  }
</style>