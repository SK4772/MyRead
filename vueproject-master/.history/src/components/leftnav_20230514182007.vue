/**
* 左边菜单
*/
<template>
  <el-menu  :collapse="collapsed" collapse-transition router :default-active="$route.path" unique-opened class="el-menu-vertical-demo" background-color="#334157" text-color="#fff" active-text-color="#ffd04b">
    <div class="logobox">
      <img class="logoimg" src="../assets/img/logo.png" alt="">
    </div>
    <el-submenu v-for="menu in allmenu" :key="menu.menuid" :index="menu.menuname">
      <template slot="title">
        <i class="iconfont" :class="menu.icon"></i>
        <span>{{menu.menuname}}</span>
      </template>
      <el-menu-item-group>
        <el-menu-item v-for="chmenu in menu.menus" :index="'/'+chmenu.url" :key="chmenu.menuid">
          <i class="iconfont" :class="chmenu.icon"></i>
          <span>{{chmenu.menuname}}</span>
        </el-menu-item>
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>
<script>
import { menu } from '../api/userMG'
export default {
  name: 'leftnav',
  data() {
    return {
      collapsed: false,
      allmenu: []
    }
  },
  // 创建完毕状态(里面是操作)
  created() {
    // 获取图形验证码
    let res = {
      success: true,
      data: [
        {
          menuid: 1,
          icon: 'li-icon-xiangmuguanli',
          menuname: '用户管理',
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 2,
              icon: 'icon-cat-skuQuery',
              menuname: '用户信息',
              hasThird: 'N',
              url: 'users/user',
              menus: null
            }
          ]
        },
        {
          menuid: 33,
          icon: 'li-icon-dingdanguanli',
          menuname: '文章管理',
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 34,
              icon: 'icon-order-manage',
              menuname: '文章信息',
              hasThird: 'N',
              url: 'essays/essay',
              menus: null
            }
          ]
        },{
          menuid: 40,
          icon: 'li-icon-dingdanguanli',
          menuname: '单词管理',
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 41,
              icon: 'icon-order-manage',
              menuname: '单词信息',
              hasThird: 'N',
              url: 'category/category',
              menus: null
            }
          ]
        },
        {
          menuid: 150,
          icon: 'li-icon-shangchengxitongtubiaozitihuayuanwenjian91',
          menuname: '笔记管理',
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 159,
              icon: 'icon-provider-manage',
              menuname: '笔记信息',
              hasThird: 'N',
              url: 'notes/note',
              menus: null
            }
          ]
        }
      ],
      msg: 'success'
    }
    this.allmenu = res.data

    this.$root.Bus.$on('toggle', value => {
      this.collapsed = !value
    })
  }
}
</script>
<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 240px;
  min-height: 400px;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  border: none;
  text-align: left;
}
.el-menu-item-group__title {
  padding: 0px;
}
.el-menu-bg {
  background-color: #1f2d3d !important;
}
.el-menu {
  border: none;
}
.logobox {
  height: 40px;
  line-height: 40px;
  color: #9d9d9d;
  font-size: 20px;
  text-align: center;
  padding: 20px 0px;
}
.logoimg {
  height: 40px;
}
</style>
