
<template>
  <div class="container">
    <el-button icon="el-icon-tickets" @click="Isdisplay"></el-button>
    <!-- <el-radio-group v-model="isCollapse" style="margin-bottom: 20px">
      <el-radio-button :label="false">展开</el-radio-button>
      <el-radio-button :label="true">收起</el-radio-button>
    </el-radio-group> -->
    <div class="menu">
      <el-menu
        default-active="1-4-1"
        :collapse-transition="false"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        :collapse="isCollapse"
        background-color="#373737"
        text-color="white"
        router
      >
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span slot="title">个人信息</span>
          </template>
          <el-menu-item-group>
            <span slot="title">分组一</span>
            <router-link
              :to="{ name: 'Learning', params: { id: 2019 } }"
              tag="span"
            >
              <el-menu-item>选项1</el-menu-item>
            </router-link>

            <el-menu-item index="1-2">选项2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="分组2">
            <el-menu-item index="1-3">选项3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="1-4">
            <span slot="title">选项4</span>
            <el-menu-item index="1-4-1">选项1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <router-link
          :to="{ name: 'Learning', params: { id} }"
          tag="span"
        >
          <el-menu-item>
            <i class="el-icon-menu"></i>
            <span slot="title">学习单词</span>
          </el-menu-item>
        </router-link>
        <el-menu-item index="/index/menu2" disabled>
          <i class="el-icon-document"></i>
          <span slot="title">复习单词</span>
        </el-menu-item>
        <router-link
          :to="{ name: 'Essay' }"
          tag="span"
        >
          <el-menu-item >
            <i class="el-icon-setting"></i>
            <span slot="title">阅读文章</span>
          </el-menu-item>
        </router-link>
      </el-menu>
    </div>
  </div>
</template>
<style lang="scss">
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}

.container {
  .el-button {
    width: 66px;
    height: 60px;
    position: absolute;
    left: 0px;
    top: 0px;
    border: none;
    background-color: #373737;
    z-index: 999;
  }

  .menu {
    text-align: left;
    background-color: #373737;
  }
}
.el-menu {
  border-right: solid 0px;
}
.el-button {
  border-radius: 0px;
}
</style>

<script>
export default {
  data() {
    return {
      isCollapse: true,
      id:""
    };
  },
  mounted
  methods: {
    Isdisplay() {
      this.isCollapse = !this.isCollapse;
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
  },
};
</script>