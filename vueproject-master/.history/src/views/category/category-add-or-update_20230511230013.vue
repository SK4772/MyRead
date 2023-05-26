<template>
  <el-dialog
    :title="!dataForm.category_id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="单词内容" prop="category_name">
      <el-input v-model="dataForm.category_name" placeholder="请输入单词"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          category_id: 0,
          category_name: ''
        },
        dataRule: {
          category_name: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.category_id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.category_id) {
            // 获取分类名称
            this.$axios
              .get("/api/admin/category/getCategoryName", {
                params:{
                  categoryId:this.dataForm.category_id
                }
              })
              .then(res => {
                this.dataForm.category_name = res.data.data
              })

          }
      })},
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$axios.post("/admin/word/add",{
              'componet': this.dataForm.category_name
            }).then((res) => {
              console.log(res);
              // if (res.data.code == '001') {
              //   this.$message({
              //     message: '操作成功',
              //     type: 'success',
              //     duration: 1500,
              //     onClose: () => {
              //       this.visible = false
              //       this.$emit('refreshDataList')
              //     }
              //   })
              // }else {
              //   this.$message.error(res.data.msg)
              // }
            })
          }
        })
      }
    }
  }
</script>
