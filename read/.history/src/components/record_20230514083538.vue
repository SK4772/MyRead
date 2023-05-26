<template>
  <div class="page">
  <el-progress :text-inside="true"  :stroke-width="26" :percentage="70"></el-progress>
  <el-calendar v-model="value" class="">
</el-calendar>
  </div>
</template>

<script>
export default {
    data(){
        return{
            dataForm:"",
            dataRule:""
        }
    }

}
</script>

<style>
.page {
  height: 650px;
  background-color: #1f1f1f;
  display: flex;
  justify-content: center;
  
}
.el-progress-bar__outer {
    
    border-radius: 100px;
    background-color: #EBEEF5;
    overflow: hidden;
    position: relative;
    vertical-align: middle;
    top: -250px;
    left: -100px;
    width: 700px;
}
</style>