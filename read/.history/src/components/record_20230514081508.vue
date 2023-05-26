<template>
  <div class="page">
  <div class="panel1">
    <el-form
        :model="dataForm"
        :rules="dataRule"
        ref="dataForm"
        @keyup.enter.native="dataFormSubmit()"
        label-width="80px"
      >
        <el-form-item label="用户ID" prop="userid"   >
          <el-input v-model="dataForm.userid" placeholder="" ></el-input>
        </el-form-item>
        <el-form-item label="文章ID" prop="essayid">
          <el-input v-model="dataForm.essayid" placeholder="" ></el-input>
        </el-form-item>
        <el-form-item label="笔记内容" prop="node">
          <el-input v-model="dataForm.node" placeholder=""></el-input>
        </el-form-item>
        
      </el-form>

  </div>
  <div class="panel2">

  </div>
  </div>
</template>

<script>
export default {
    data

}
</script>

<style>
.page {
  height: 600px;
  background-color: #1f1f1f;
  display: flex;
  justify-content: center;
  align-items: center;
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
  padding: 20px;
  display: flex;
  flex-direction: column;
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