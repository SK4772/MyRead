<template>
  <div >
    <el-container>
      <el-header><navpro></navpro></el-header>
      <el-container>
        <el-aside width="auto"><left-vue></left-vue></el-aside>
        <el-main><router-view></router-view></el-main>
      </el-container>
      
    </el-container>
  </div>
</template>

<script>
import navpro from '@/views/home/header.vue';
import leftVue from '@/views/home/left.vue';

export default {
  name: 'Index',
  components: {
        navpro,
        leftVue
  },
  props: {
    msg: String
  }
}
</script>
<style>
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    background-color: #373737;
    color: #333;
    text-align: center;
    line-height: 200px;
    height: 650px;
  }

  .el-main {
    background-color: #1f1f1f;
    color: #333;
    text-align: center;
  }

  body > .el-container {
    margin-bottom: 40px;
  }
</style>
