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
    <el-form :inline="true" :model="formInline" class="user-search">
      <el-form-item label="搜索：">
        <el-input size="small" v-model="formInline.deptName" placeholder="输入部门名称"></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-input size="small" v-model="formInline.deptNo" placeholder="输入部门代码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button size="small" type="primary" icon="el-icon-search" @click="search">搜索</el-button>
        <el-button size="small" type="primary" icon="el-icon-plus" @click="handleEdit()">添加</el-button>
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
<!--      <el-table-column-->
<!--        prop="product_id"-->
<!--        header-align="center"-->
<!--        align="center"-->
<!--        label="id">-->
<!--      </el-table-column>-->
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
        prop="product_intro"
        header-align="center"
        align="center"
        label="描述">
      </el-table-column>
      <el-table-column
        prop="product_picture"
        header-align="center"
        align="center"
        label="图片">
        <template slot-scope="scope">
          <img :src="scope.row.product_picture.includes('http:')?scope.row.product_picture:'http://localhost:3000/'+scope.row.product_picture" style="width:80px">
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
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.productId)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.productId)">删除</el-button>
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
      categoryID:[ ],
      formInline:""
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
  },
  activated() {

  },

  /**
   * 里面的方法只有被调用才会执行
   */
  methods: {
    // 获取数据列表
    getDataList () {
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
    currentChangeHandle (val) {
      this.pageIndex = val
      this.getDataList()
    },
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
</style>


