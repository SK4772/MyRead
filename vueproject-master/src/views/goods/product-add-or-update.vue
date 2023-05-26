<template>
  <el-dialog
    :title="!dataForm.productId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="productName">
      <el-input v-model="dataForm.productName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="categoryId">
      <el-input v-model="dataForm.categoryId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="productTitle">
      <el-input v-model="dataForm.productTitle" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="productIntro">
      <el-input v-model="dataForm.productIntro" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="productPicture">
      <el-input v-model="dataForm.productPicture" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="productPrice">
      <el-input v-model="dataForm.productPrice" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="productSellingPrice">
      <el-input v-model="dataForm.productSellingPrice" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="productNum">
      <el-input v-model="dataForm.productNum" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="productSales">
      <el-input v-model="dataForm.productSales" placeholder=""></el-input>
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
          productId: 0,
          productName: '',
          categoryId: '',
          productTitle: '',
          productIntro: '',
          productPicture: '',
          productPrice: '',
          productSellingPrice: '',
          productNum: '',
          productSales: ''
        },
        dataRule: {
          productName: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          categoryId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          productTitle: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          productIntro: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          productPicture: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          productPrice: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          productSellingPrice: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          productNum: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          productSales: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.productId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.productId) {
            this.$http({
              url: this.$http.adornUrl(`/commodity/product/info/${this.dataForm.productId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.productName = data.product.productName
                this.dataForm.categoryId = data.product.categoryId
                this.dataForm.productTitle = data.product.productTitle
                this.dataForm.productIntro = data.product.productIntro
                this.dataForm.productPicture = data.product.productPicture
                this.dataForm.productPrice = data.product.productPrice
                this.dataForm.productSellingPrice = data.product.productSellingPrice
                this.dataForm.productNum = data.product.productNum
                this.dataForm.productSales = data.product.productSales
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/commodity/product/${!this.dataForm.productId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'productId': this.dataForm.productId || undefined,
                'productName': this.dataForm.productName,
                'categoryId': this.dataForm.categoryId,
                'productTitle': this.dataForm.productTitle,
                'productIntro': this.dataForm.productIntro,
                'productPicture': this.dataForm.productPicture,
                'productPrice': this.dataForm.productPrice,
                'productSellingPrice': this.dataForm.productSellingPrice,
                'productNum': this.dataForm.productNum,
                'productSales': this.dataForm.productSales
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
