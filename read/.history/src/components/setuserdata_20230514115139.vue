<template>
  <el-dialog
      title="个人中心"
      :close-on-click-modal="false"
      :visible.sync="visible1"
      customClass="dialog"
    >
    <el-form
        :model="userdata"
        :rules="dataRule"
        ref="userdata"
        @keyup.enter.native="dataFormSubmit()"
        label-width="80px"
      >
        <el-form-item label="账户" prop="username"   >
          <el-input v-model="userdata.username" placeholder="" class="put" ></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="userdata.password" placeholder="" class="put"></el-input>
        </el-form-item>
         <el-form-item label="昵称" prop="name">
          <el-input v-model="userdata.name" placeholder="" class="put"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="telephone">
          <el-input v-model="userdata.telephone" placeholder="" class="put"></el-input>
        </el-form-item>
        <el-form-item label="联系邮箱" prop="mail">
          <el-input v-model="userdata.mail" placeholder="" class="put"></el-input>
        </el-form-item>
         <span slot="footer" class="dialog-footer">
        <el-button @click="visible1 = false">取消</el-button>
        <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
      </span>
        
      </el-form>
    </el-dialog>
</template>

<script>
export default {

}
</script>

<style>

</style>