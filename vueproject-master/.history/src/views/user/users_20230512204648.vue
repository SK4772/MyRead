<template>
  <div class="mod-config">
    <el-dialog
      title="单词修改"
      :close-on-click-modal="false"
      :visible.sync="visible1"
    >
      <el-form
        :model="dataForm"
        :rules="dataRule"
        ref="dataForm"
        @keyup.enter.native="dataFormSubmit()"
        label-width="80px"
      >
        <el-form-item label="标题" prop="title">
          <el-input v-model="dataForm.title" placeholder=""></el-input>
        </el-form-item>
        <el-form-item label="分类" prop="classify">
          <el-input v-model="dataForm.classify" placeholder=""></el-input>
        </el-form-item>
        <el-form-item label="文件存储路径" prop="essayPath">
          <el-input v-model="dataForm.essayPath" placeholder=""></el-input>
        </el-form-item>
        <el-form-item label="上传文件" prop="wordGroupMeaning">
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
      <el-button @click="visible1 = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
    </el-dialog>
    <el-form
      :inline="true"
      :model="dataForm"
      @keyup.enter.native="getDataList()"
    >
      <el-form-item>
        <el-input
          v-model="wordInputContent"
          placeholder="参数名"
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="queryByComponet()">查询</el-button>
        <el-button type="primary" @click="addHandle()">新增</el-button>
        <!-- <el-button
          type="danger"
          @click="deleteHandle()"
          :disabled="dataListSelections.length <= 0"
          >批量删除</el-button
        > -->
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%"
    >

      <el-table-column
        sortable
        prop="essayid"
        label="用户ID"
        width="100"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column
        sortable
        prop="title"
        label=""
        width="80"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column
        sortable
        prop="classify"
        label="分类"
        width="80"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column
        sortable
        prop="essayPath"
        label="文件存放地址"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作"
      >
        <template slot-scope="scope">
          <el-button
            type="text"
            size="small"
            @click="updateHandle(scope.row)"
            >修改</el-button
          >
          <el-button
            type="text"
            size="small"
            @click="deleteHandle(scope.row.category_id, scope.row.componet)"
          >
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
      layout="total, sizes, prev, pager, next, jumper"
    >
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update
      v-if="addOrUpdateVisible"
      ref="addOrUpdate"
      @refreshDataList="getDataList"
    ></add-or-update>
  </div>
</template>

<script>
import AddOrUpdate from "../category/category-add-or-update";

export default {
  data() {
    return {
    
      visible1: false,
      dataList: [],
      dataForm:"",
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false,
      component: "",
      wordInputContent: "",
      dateRule:""
      
    };
  },
  components: {
    AddOrUpdate,
  },
  created() {
    this.getDataList();
  },
  activated() {
    // this.getDataList()
  },
  methods: {
        // 表单提交
    dataFormSubmit() {
      if(this.dataForm.essayid){
              this.$refs['dataForm'].validate((valid) => {
        if (valid) {

          this.$axios.post("/admin/essay/updataEssay",this.dataForm).then((res) => {
            if (res.data== true) {
              this.$message({
                message: '操作成功',
                type: 'success',
                onClose: () => {
                  this.visible1 = false
                  this.$emit('refreshDataList')
                }
              })
            }else {
              this.$message.error("操作失败，请重试")
            }
            console.log(this.dataForm);
            console.log(res);
          })

        }
      })
      }else{
          this.$refs['dataForm'].validate((valid) => {
        if (valid) {

          this.$axios.post("/admin/essay/addEssay",this.dataForm).then((res) => {
            if (res.data== true) {
              this.$message({
                message: '操作成功',
                type: 'success',
                onClose: () => {
                  this.visible1 = false
                  this.$emit('refreshDataList')
                }
              })
            }else {
              this.$message.error("操作失败，请重试")
            }
            console.log(this.dataForm);
            console.log(res);
          })

        }
      })
      }
      
    },
    addHandle(){   
      this.dataForm={};
      this.visible1=true;
      console.log(1234545);
     
    },
    updateHandle(row) {
      this.visible1=true;
      this.dataForm=row;
      console.log(row);
      

    },
    queryByComponet() {
      this.$axios
        .post("/admin/essay/queryByTitle", {
          title : this.wordInputContent,
        })
        .then((response) => {
          this.dataList = response.data;
          console.log(response);
        });
    },
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;

      this.$axios
        .get("/admin/essay/queryAll", {
          params: {
            pageindex: this.pageIndex,
            pagesize: this.pageSize,
          },
        })
        .then((res) => {
          console.log(123);
          this.dataList = res.data.list;
          this.totalPage = res.data.total;
          this.dataListLoading = false;
        });
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },
    // 多选
    selectionChangeHandle(val) {
      this.dataListSelections = val;
    },
    // 新增 / 修改
    addOrUpdateHandle(id) {
      this.addOrUpdateVisible = true;
      this.$nextTick(() => {
        console.log("id=>", id);
        this.$refs.addOrUpdate.init(id);
      });
    },
    // 删除
    deleteHandle(id, name) {
      var ids = id
        ? [id]
        : this.dataListSelections.map((item) => {
            return item.category_id;
          });
      var names = name
        ? [name]
        : this.dataListSelections.map((item) => {
            return item.category_name;
          });
      this.$confirm(`确定进行操作?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        this.$axios
          .post("/admin/word/deleta", {
            componet: name,
          })
          .then((res) => {
            console.log(res);
            this.dataListLoading = true;
            if (res.data) {
              this.$message({
                message: "单词删除成功",
                type: "success",
                onClose: () => {
                  this.getDataList();
                },
              });
            } else
              this.$message({
                message: "单词删除失败，请重试",
                type: "error",
                onClose: () => {
                  this.getDataList();
                },
              });
            this.dataListLoading = false;
            // if (res.data.code == '001') {
            //   if (res.data.data.length>0){
            //     console.log(res.data.data)
            //     var cnames = res.data.data.map(item => {
            //       return item.category_name
            //     })
            //     this.$message({
            //       message: `${cnames.join(' ,')} 分类下存在商品，不可删除`,
            //       type: 'success',
            //       duration: 1500,
            // onClose: () => {
            //   this.getDataList()
            //       }
            //     })
            //   }else {
            //     this.$message({
            //       message: '操作成功',
            //       type: 'success',
            //       duration: 1500,
            //       onClose: () => {
            //         this.getDataList()
            //       }
            //     })
            //   }

            // } else {
            //   this.$message.error(res.data.msg)
            // }
          });
      });
    },
  },
};
</script>
