/**
* 基础菜单 商品管理
*/
<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="searchText" class="user-search">
      <el-form-item label="搜索：">
        <el-input size="small" v-model="searchText" placeholder="输入商品名称"></el-input>
      </el-form-item>

      <el-form-item label="">
        <el-select v-model="searchCategoryId" placeholder="请选择">
          <el-option
            v-for="item in category"
            :key="item.category_id"
            :label="item.category_name"
            :value="item.category_id">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button size="small" type="primary" icon="el-icon-search" @click="search">搜索</el-button>
        <el-button size="small" type="primary" icon="el-icon-plus" @click="addOrUpdateHandle()">添加</el-button>
        <el-button type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>

    </el-form>

    <!--列表-->
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">

      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        prop="product_name"
        header-align="center"
        align="center"
        label="名称">
      </el-table-column>
      <el-table-column
        prop="category_name"
        header-align="center"
        align="center"
        label="分类">
      </el-table-column>
      <el-table-column
        prop="product_title"
        header-align="center"
        align="center"
        label="标题">
      </el-table-column>
      <el-table-column
        type="expand"
        header-align="center"
        align="center"
        label="描述">
        <template slot-scope="props">
          <el-form  label-position="left" inline class="demo-table-expand">
            <el-form-item id="xiala" label="商品描述:">
              <span >{{ props.row.product_intro}}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
        prop="product_picture"
        header-align="center"
        align="center"
        label="图片">
        <template slot-scope="scope">
          <img
            :src="scope.row.product_picture.includes('https:')?scope.row.product_picture:'http://localhost:3000/'+scope.row.product_picture"
            style="width:80px">
        </template>
      </el-table-column>
      <el-table-column
        prop="product_price"
        header-align="center"
        align="center"
        label="价格">
      </el-table-column>
      <el-table-column
        prop="product_selling_price"
        header-align="center"
        align="center"
        label="售价">
      </el-table-column>
      <el-table-column
        prop="product_num"
        header-align="center"
        align="center"
        label="库存">
      </el-table-column>
      <el-table-column
        prop="product_sales"
        header-align="center"
        align="center"
        label="销量">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.product_id)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.product_id,scope.row.product_name)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>

  </div>
</template>

<script>
import AddOrUpdate from './product-add-or-update'

export default {
  data() {
    return {
      dataForm: {
        key: ''
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false,
      categoryID: [],
      searchText: "",

      category: [],
      searchCategoryId: ''
    }
  },
  // 注册组件
  components: {
    AddOrUpdate
  },
  /**
   * 数据发生改变
   */

  /**
   * 创建完毕
   */
  created() {
    this.getDataList()
    this.getCategories()
  },
  activated() {

  },

  /**
   * 里面的方法只有被调用才会执行
   */
  methods: {

    //搜索方法
    search() {
      this.$axios
        .post("/api/admin/product/adminSearch", {
          categoryID: this.searchCategoryId ? [this.searchCategoryId] : [],
          currentPage: this.pageIndex,
          pageSize: this.pageSize,
          searchText: this.searchText
        })
        .then(res => {
          this.dataList = res.data.data;
          this.totalPage = res.data.total;
          this.dataListLoading = false
          //重置 搜索信息
          this.searchText = "";
          this.searchCategoryId = ''
        })


    },
    //得到所有分类信息
    getCategories() {
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

    // 获取数据列表
    getDataList() {
      this.dataListLoading = true

      this.$axios
        .post("/api/admin/product/all", {
          categoryID: this.categoryID,
          currentPage: this.pageIndex,
          pageSize: this.pageSize
        })
        .then(res => {
          this.dataList = res.data.data;
          this.totalPage = res.data.total;
          this.dataListLoading = false
        })
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val
      this.getDataList()
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val
      this.pageIndex = 1
      this.getDataList()
    },
    // 多选
    selectionChangeHandle(val) {
      this.dataListSelections = val
    },      // 新增 / 修改
    addOrUpdateHandle(id) {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id)
      })
    },
    // 删除
    deleteHandle(id, name) {
      var ids = id ? [id] : this.dataListSelections.map(item => {
        return item.product_id
      })
      var names = name ? [name] : this.dataListSelections.map(item => {
        return item.product_name
      })

      this.$confirm(`确定对 ${names.join(' ,')} 进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios({
            method: "post",
            url: "api/admin/product/adminDelete",
            data: ids
          }
        ).then((res) => {
          if (res.data.code == '001') {
            this.$message({
              message: '操作成功',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.getDataList()
              }
            })
          } else {
            this.$message.error(res.data.msg)
          }
        })


      })
    }
  }
}
</script>

<style scoped>
.user-search {
  margin-top: 20px;
}

.userRole {
  width: 100%;
}

.demo-table-expand {
  font-size: 0;
}
/deep/.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
/deep/.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 100%;
}

/deep/ #xiala{
  padding-left: 10px;
  display: flex;
}
</style>


