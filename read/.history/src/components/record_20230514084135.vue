<template>
  <div class="page">
  <el-calendar v-model="value" class="panel">
    <template
         slot="dateCell"
         slot-scope="{date, data}">
         <p> <!--这里原本有动态绑定的class，去掉-->
            {{ data.day.split('-').slice(1).join('-') }}<br /> {{dealMyDate(data.day)}}
         </p>
     </template>
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
.panel{
    
}
</style>