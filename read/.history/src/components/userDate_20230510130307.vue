<template>
  <div class="page">
    <div class="progress_panel"></div>
    <div class="update_panel"></div>
    <div class="words_panel"></div>
  </div>
</template>

<script>

export default {
    name:"Upload",
    data(){
        
    }

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
   flex-direction: column;
}
.progress_panel{
  position: absolute;
  left: 40px;
  top: 5px;
  border-radius: 50px;
  background: rgb(50, 50, 50, 0.3);
  color: white;
  height: 200px;
  width: 20px;
  padding: 20px;
  display: flex;
  flex-direction: column;
}
.update_panel{
  position: absolute;
  left: 270px;
  top: 100px;
  border-radius: 50px;
  background: rgb(50, 50, 50, 0.3);
  color: white;
  height: 50px;
  width: 800px;
  padding: 20px;
  display: flex;
}
.words_panel{
  position: absolute;
  left: 20px;
  top: 200px;
  background: rgb(50, 50, 50, 0.3);
  color: white;
  height: 400px;
  width: 1400px;
  padding: 20px;
  display: flex;
}
</style>