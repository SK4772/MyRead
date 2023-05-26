<template>
  <div class="page">
  
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
  height: 600px;
  background-color: #1f1f1f;
  display: flex;
  justify-content: center;
  
}
.panel1 {
  position: absolute;
  left: 50px;
  top: 5px;
  border-radius: 50px;
  background: rgb(50, 50, 50, 0.3);
  color: white;
  height: 550px;
  width: 550px;

}
.panel2 {
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