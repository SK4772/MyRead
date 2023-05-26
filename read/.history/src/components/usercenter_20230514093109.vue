<template>
   <el-dialog
      title="单词修改"
      :close-on-click-modal="false"
      :visible.sync="true"
    >
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
      <span slot="footer" class="dialog-footer">
        <el-button @click="visible1 = false">取消</el-button>
        <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
      </span>
    </el-dialog>
</template>

<script>
export default {

}
</script>

<style>

</style>