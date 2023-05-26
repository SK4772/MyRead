
<template>

  <header>
     <el-dialog
      title="笔记修改"
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
    <el-dialog
      title="个人中心"
      :close-on-click-modal="false"
      :visible.sync="visible"
      customClass="dialog"
    >
     <el-form :model="dateFrom">
       <el-form-item label="1">
          <el-descriptions class="margin-top" title="基本信息" :column="3" :size="size" border>
    <template slot="extra">
      <el-button type="primary" size="small" @click="handle()">修改个人信息</el-button>
    </template>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-user"></i>
        用户名
      </template>
    {{userdata[0].username}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-mobile-phone"></i>
        密码
      </template>
      {{userdata[0].password}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-location-outline"></i>
        昵称
      </template>
      {{userdata[0].name}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-tickets"></i>
        联系电话
      </template>
      {{userdata[0].telephone}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-office-building"></i>
        联系邮箱
      </template>
      {{userdata[0].mail}}
    </el-descriptions-item>
  </el-descriptions>
       </el-form-item>
       <el-form-item label="2.单词学习进度"  >
        <el-progress :text-inside="true" :stroke-width="24" :percentage="process" status="success"></el-progress>
       </el-form-item>
     </el-form> 
        
    </el-dialog>
    <el-menu
      class="el-menu"
      mode="horizontal"
      @select="handleSelect"
      background-color="#373737"
      text-color="#fff"
      active-text-color="#ffd04b"
    >
     
      <el-menu-item index="1" class="el-icon-headset">Reader</el-menu-item>
      <el-menu-item class="select" style="width: 400px">
        <el-button
          icon="el-icon-search"
          type="text"
          style="margin-right: 10px"
          @click="search"
          class="button"
        >
        </el-button>
        <el-input
          class="input"
         
          style="width: 200px; margin-right: 10px"
          clearable
          @clear="search"
          @keydown.enter.native="search"
        ></el-input>
      </el-menu-item>
      <el-menu-item>
        <!-- 主题选择器 -->
        <el-switch v-model="select" active-text="按月付费"> </el-switch>
      </el-menu-item>
     
      <el-menu-item>
        <!-- 头像 -->
        <el-avatar> user </el-avatar>
      </el-menu-item>
      <el-submenu index="2" class="submenu">
      <!-- <template slot="title">{{user.userRealName}}</template> -->
      <template slot="title">用户中心</template>
      <el-menu-item @click="content()" index="2-2">个人中心</el-menu-item>
      <el-menu-item @click="exit()" index="2-3">退出</el-menu-item>
    </el-submenu>
    </el-menu>
  </header>
</template>

<script>
import Header from "@/components/Header.vue";
import languagesVue from "./nav/languages.vue";
import usercenterVue from '@/components/usercenter.vue'

export default {
  components: {
    languagesVue,
    usercenterVue,
  },

  data() {
    return {
      visible:false,
      visible1:false,
      select: true,
      userdata:{},
      process:"",
     
    };
  },
  mounted(){
    this.getUserDate()
  },
  methods: {
    // 搜索框绑定事件
    search() {},
   
    //国际化切换语言
    handleSelect() {},
    visible:false,
    content(){
      this.visible=true;
      this.getUserDate();
      this.getProcess();
    },
    getUserDate(){
      this.$axios.post("/admin/user/queryByID",{
        username:this.$route.params.id
      }).then((res)=>{
        console.log(res.data)
        this.userdata=res.data;
      })
    },
    getProcess(){
      this.$axios.post("/word/getProcess",{
        userid:this.$route.params.id
      }).then((res)=>{
        console.log(res.data)
        this.process=res.data;
      })

    }
  },
};
</script>

<style>
.el-menu.el-menu--horizontal {
    border-bottom: solid 0px #e6e6e6;
}
.el-header {
  padding-left: 0px;
  padding-right: 0px;
}
.el-menu-item{
  padding-left: 0px;
  padding-right: 20px;
}
.el-icon-headset{
  padding-left: 90px;
}
.select {
  display: flex;
}
.button {
  margin-right: auto;
  background-color: #373737;
  width: 30px;
}
.el-input__inner {
  background-color: #373737;
  border: 1px #802c2c;
}
.dialog{
  width: 80%;
  height: 80%;
}
.el-dialog__body {
 
      display: flex;
 
      justify-content: center;
 
      align-content: center;
      
 
}
</style>