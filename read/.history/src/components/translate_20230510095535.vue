<template>
  <div class="page">
    <div class="EnglishPanel">
        <el-input
        type="EnglishContent"
        :rows="10"
        placeholder="请输入内容"
        v-model="node"
        maxlength="500"
        show-word-limit
      >
      </el-input>
    </div>
    <el-button icon="el-icon-refresh" round>确认翻译</el-button>
    <div class="ChinesePanel"></div>
  </div>
</template>

<script>
export default {
    name:"Translate",
    data(){
        return{
            EnglishContent:""
        }        
    }
    m
    
};
</script>

<style>
.page {
  position: relative;
  border-radius: 50px;
  background-image: url("../images/bg1.jpg");
  height: 100%;
  width: 100%;
  background-color: #1f1f1f;
  display: flex;
  justify-content: center;
  align-items: center;
}
.EnglishPanel {
    position: absolute;
  left: 50px;
  top: 5px;
  border-radius: 50px;
  background: rgb(50, 50, 50, 0.3);
  color: white;
  height: 550px;
  width: 550px;
  padding: 20px;
  display: flex;
  flex-direction: column;
}
.ChinesePanel {
  position: absolute;
  right: 50px;
  top: 5px;
  border-radius: 50px;
  background: rgb(50, 50, 50, 0.3);
  color: white;
  height: 550px;
  width: 550px;
  padding: 20px;
  display: flex;
  flex-direction: column;
}
</style>