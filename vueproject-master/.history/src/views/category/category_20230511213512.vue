<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="单词详情">
              <span>{{ props.row.explain }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
        sortable
        prop="componet"
        label="单词"
        width="80"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column
        sortable
        prop="meaning"
        label="意思"
        width="80"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column
        sortable
        prop="usPhonetic"
        label="英式音标"
        width="80"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column
        sortable
        prop="ukPhonetic"
        label="美式音标"
        width="80"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column
        sortable
        prop="wordGroup"
        label="单词词组1"
        width="100"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column
        sortable
        prop="wordGroupMeaning"
        label="词组翻译1"
        width="350"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column
        sortable
        prop="wordGroup1"
        label="单词词组2"
        width="100"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column
        sortable
        prop="wordGroupMeaning1"
        label="词组翻译2"
        width="350"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.category_id)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.category_id,scope.row.category_name)">
            删除
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
import AddOrUpdate from './category-add-or-update'

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
      addOrUpdateVisible: false
    }
  },
  components: {
    AddOrUpdate
  },
  created() {
    this.getDataList()
  },
  activated() {
    // this.getDataList()
  },
  methods: {
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true
      
      this.$axios
        .get("/admin/word/query", {
          params: {
            pageindex: this.pageparm.currentPage,
            pagesize: this.pageparm.pageSize
          }

        })
        .then(res => {console.log(123);
          this.dataList = res.data.list;
          this.totalPage = res.data.total;
          this.dataListLoading = false
        })
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val
      this.pageIndex = 1
      this.getDataList()
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val
      this.getDataList()
    },
    // 多选
    selectionChangeHandle(val) {
      this.dataListSelections = val
    },
    // 新增 / 修改
    addOrUpdateHandle(id) {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        console.log("id=>", id)
        this.$refs.addOrUpdate.init(id)
      })
    },
    // 删除
    deleteHandle(id, name) {

      var ids = id ? [id] : this.dataListSelections.map(item => {
        return item.category_id
      })
      var names = name ? [name] : this.dataListSelections.map(item => {
        return item.category_name
      })
      this.$confirm(`确定对 ${names.join(' ,')} 进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios({
          method: "post",
          url: "api/admin/category/delete",
          data: ids
        }
        ).then((res) => {
          if (res.data.code == '001') {
            if (res.data.data.length>0){
              console.log(res.data.data)
              var cnames = res.data.data.map(item => {
                return item.category_name
              })
              this.$message({
                message: `${cnames.join(' ,')} 分类下存在商品，不可删除`,
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            }else {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            }


          } else {
            this.$message.error(res.data.msg)
          }
        })

      })
    }
  }
}
</script>
