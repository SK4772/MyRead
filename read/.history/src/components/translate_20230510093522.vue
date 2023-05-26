<template>
  <div class="page">
    <div class="EnglishPanel">
        <el-input
  type="textarea"
  :rows="2"
  placeholder="请输入内容"
  v-model="textarea">
</el-input>
    </div>
    <div class="ChinesePanel">

    </div>
  </div>
</template>

<script>
export default {

}
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
  left: 5px;
  top: 5px;
  border-radius: 50px;
  background: rgb(50, 50, 50, 0.3);
  color: white;
  height: 600px;
  width: 600px;
  min-width: 600px;
  padding: 20px;
  display: flex;
  /* flex-direction: column; */
}
.ChinesePanel {
  position: absolute;
  right: 5px;
  top: 5px;
  border-radius: 50px;
  background: rgb(50, 50, 50, 0.3);
  color: white;
  height: 600px;
  width: 600px;
  min-width: 600px;
  padding: 20px;
  display: flex;
  /* flex-direction: column; */
}
</style>