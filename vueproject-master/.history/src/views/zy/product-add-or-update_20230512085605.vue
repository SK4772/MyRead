<template>
  <el-dialog
    :title="!dataForm.productId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="80px">
      <el-form-item label="商品名称" prop="product_name">
        <el-input v-model="dataForm.product_name" placeholder=""></el-input>
      </el-form-item>
      <el-form-item label="分类名称" prop="category_id">
        <el-select v-model="dataForm.category_id" placeholder="请选择">
          <el-option
            v-for="item in category"
            :key="item.category_id"
            :label="item.category_name"
            :value="item.category_id">
          </el-option>
        </el-select>

<!--        <el-input v-model="dataForm.category_id" placeholder=""></el-input>-->
      </el-form-item>
      <el-form-item label="商品标题" prop="product_title">
        <el-input v-model="dataForm.product_title" placeholder=""></el-input>
      </el-form-item>
      <el-form-item label="商品介绍" prop="product_intro">
        <el-input v-model="dataForm.product_intro" placeholder=""></el-input>
      </el-form-item>
      <el-form-item label="商品图片" prop="product_picture">
        <singleUpload v-model="dataForm.product_picture" @addfile="addFile"></singleUpload>
<!--        <singleUpload v-model="dataForm.product_picture"></singleUpload>-->
      </el-form-item>
      <el-form-item label="商品价格" prop="product_price">
        <el-input v-model="dataForm.product_price" placeholder=""></el-input>
      </el-form-item>
      <el-form-item label="商品售价" prop="product_selling_price">
        <el-input v-model="dataForm.product_selling_price" placeholder=""></el-input>
      </el-form-item>
      <el-form-item label="商品库存" prop="product_num">
        <el-input v-model="dataForm.product_num" placeholder=""></el-input>
      </el-form-item>
      <el-form-item label="商品销售量" prop="product_sales">
        <el-input v-model="dataForm.product_sales" placeholder=""></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import singleUpload from "../../components/upload/singleUpload"

export default {
  components: {singleUpload},
  data() {
    return {
      visible: false,
      category:[],
      dataForm: {
        product_id: 0,
        product_name: '',
        category_id: '',
        product_title: '',
        product_intro: '',
        product_picture: '',
        product_price: '',
        product_selling_price: '',
        product_num: '',
        product_sales: ''
      },
      dataRule: {
        product_name: [
          {required: true, message: '不能为空', trigger: 'blur'}
        ],
        category_id: [
          {required: true, message: '不能为空', trigger: 'blur'}
        ],
        product_title: [
          {required: true, message: '不能为空', trigger: 'blur'}
        ],
        product_intro: [
          {required: true, message: '不能为空', trigger: 'blur'}
        ],
        product_picture: [
          {required: true, message: '图片还未上传', trigger: 'blur'}
        ],
        product_price: [
          // {required: true, message: '不能为空', trigger: 'blur'}
          {
            validator: (rule, value, callback) => {
              if (value == "") {
                callback(new Error("请输入商品价格"));
              } else if (!/^(([1-9]\d*)|(0))$/.test(value)) {
                callback(new Error("价格须是大于等于 0 的整数"));
              } else {
                callback();
              }
            }, trigger: 'blur'
          }
        ],
        product_selling_price: [
          // {required: true, message: '不能为空', trigger: 'blur'}
          {
            validator: (rule, value, callback) => {
              if (value == "") {
                callback(new Error("请输入商品售价"));
              } else if (!/^(([1-9]\d*)|(0))$/.test(value)) {
                callback(new Error("售价须是大于等于 0 的整数"));
              } else {
                callback();
              }
            }, trigger: 'blur'
          }
        ],
        product_num: [
          // {required: true, message: '不能为空', trigger: 'blur'}
          {
            validator: (rule, value, callback) => {
              if (value == "") {
                callback(new Error("库存须填写"));
              } else if (!/^(([1-9]\d*)|(0))$/.test(value)) {
                callback(new Error("库存须是大于等于 0 的整数"));
              } else {
                callback();
              }
            }, trigger: 'blur'
          }
        ],
        product_sales: [
          // {required: true, message: '不能为空', trigger: 'blur'}
          {
            validator: (rule, value, callback) => {
              if (value == "") {
                callback(new Error("销售量须填写"));
              } else if (!/^(([1-9]\d*)|(0))$/.test(value)) {
                callback(new Error("销售量须是大于等于 0 的整数"));
              } else {
                callback();
              }
            }, trigger: 'blur'
          }
        ]
      }
    }
  },
  methods: {
    //得到所有分类信息
    getCategories(){
      this.$axios({
        method: "get",
        url: `/api/admin/category/all`
      }).then((response) => {
        if (response.data.code == '001') {
          console.log(response.data.data)
          this.category = response.data.data
        }
      })
    },

    //添加文件信息
    addFile(file) {
      console.log(file)
      this.dataForm.product_picture = file
    },

    init(id) {
      console.log(id)
      this.getCategories()
      this.dataForm.product_id = id || 0
      this.visible = true
      this.$nextTick(() => {

        this.$refs['dataForm'].resetFields()
        if (this.dataForm.product_id) {
          this.$axios({
            method: "get",
            url: `/api/admin/product/adminProductdetail/`,
            params:{product_id:this.dataForm.product_id}
          }).then((response) => {
            if (response.data.code == '001') {
              this.dataForm.product_name = response.data.data.product_name
              this.dataForm.category_id = response.data.data.category_id
              this.dataForm.product_title = response.data.data.product_title
              this.dataForm.product_intro = response.data.data.product_intro
              this.dataForm.product_picture = response.data.data.product_picture
              this.dataForm.product_price = response.data.data.product_price
              this.dataForm.product_selling_price = response.data.data.product_selling_price
              this.dataForm.product_num = response.data.data.product_num
              this.dataForm.product_sales = response.data.data.product_sales
            }
          })
        }
      })
    },
    // 表单提交
    dataFormSubmit() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {

          this.$axios.post("/api/admin/product/save",{
            'product_id': this.dataForm.product_id || undefined,
            'product_name': this.dataForm.product_name,
            'category_id': this.dataForm.category_id,
            'product_title': this.dataForm.product_title,
            'product_intro': this.dataForm.product_intro,
            'product_picture': this.dataForm.product_picture,
            'product_price': this.dataForm.product_price,
            'product_selling_price': this.dataForm.product_selling_price,
            'product_num': this.dataForm.product_num,
            'product_sales': this.dataForm.product_sales
          }).then((res) => {
            if (res.data.code == '001') {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.visible = false
                  this.$emit('refreshDataList')
                }
              })
            }else {
              this.$message.error(res.data.msg)
            }
          })

        }
      })
    }
  }
}
</script>
