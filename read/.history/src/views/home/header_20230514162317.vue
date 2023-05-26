
<template>

  <header>
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
          <el-input v-model="userdata.username" placeholder="" id="put" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="userdata.password" placeholder="" id="put"></el-input>
        </el-form-item>
         <el-form-item label="昵称" prop="name">
          <el-input v-model="userdata.name" placeholder="" id="put"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="telephone">
          <el-input v-model="userdata.telephone" placeholder="" id="put"></el-input>
        </el-form-item>
        <el-form-item label="联系邮箱" prop="mail">
          <el-input v-model="userdata.mail" placeholder="" id="put"></el-input>
        </el-form-item>
        
      </el-form><span slot="footer" class="dialog-footer">
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
    {{userdata.username}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-mobile-phone"></i>
        密码
      </template>
      {{userdata.password}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-location-outline"></i>
        昵称
      </template>
      {{userdata.name}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-tickets"></i>
        联系电话
      </template>
      {{userdata.telephone}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-office-building"></i>
        联系邮箱
      </template>
      {{userdata.mail}}
    </el-descriptions-item>
  </el-descriptions>
       </el-form-item>
       <el-form-item label="2单词学习进度">
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
      <el-submenu index="2" class="submenu">
      <!-- <template slot="title">{{user.userRealName}}</template> -->
      <template slot="title">用户中心</template>
      <el-menu-item @click="content()" index="2-2">个人中心</el-menu-item>
      <el-menu-item @click="exit()" index="2-3" >退出</el-menu-item>
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

// ^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$
  data() {
    return {
      visible:false,
      visible1:false,
      select: true,
      userdata:{},
      userid:"",
      process:"",
      dataRule:{
        username:[
            { required: true, message: '请输入账户', trigger: 'blur' },
            { pattern: /^\d{9}$/, message: '请输入9位数数字', trigger: 'blur' }
        ],
        password:[
            { required: true, message: '请输入账户', trigger: 'blur' },
            { min: 8, max: 12, message: '请输入8~12位密码', trigger: 'blur' }
        ],
        name:[
            { required: true, message: '请输入昵称', trigger: 'blur' },
        ],
        telephone:[
            { pattern: /^1(3\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\d|9[0-35-9])\d{8}$/, message: '请输入正确的电话', trigger: 'blur' },
        ],
        mail:[
          { pattern: /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/, message: '请输入正确的邮箱', trigger: 'blur' }
        ]
        
      }
     
    };
  },
  mounted(){
    this.getUserDate()
  },
  methods: {
     dataFormSubmit() {
      // /user/update
      this.$axios.post("/user/update",this.userdata).then((res) => {
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
            console.log(this.userdata);
            console.log(res);
          })
     },
    // 搜索框绑定事件
    search() {},
    exit(){
      
    },
   
    //国际化切换语言
    handleSelect() {},
    handle(){this.getUserDate();
      this.visible1=true;
      
    },
    content(){
      this.visible=true;
      this.getUserDate();
      this.getProcess();
    },
    getUserDate(){
      console.log()
      if(this.$route.params.id){
        this.userid=this.$route.params.id;
      }else{
        this.userid=this.$route.query.id;
      }
      this.$axios.post("/admin/user/queryByID",{
        username:this.userid
      }).then((res)=>{
        console.log(res.data)
        
        this.userdata=res.data[0];
      })
    },
    getProcess(){
      this.$axios.post("/word/getProcess",{
        userid:this.userid
      }).then((res)=>{
        console.log(res.data)
        this.process=res.data;
      })

    }
  },
};
</script>

<style >
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
.el-form-item{

    margin-bottom: 20px !important;

}
#put {
    background-color: whitesmoke !important;
    border: 1px black solid;
}
</style>