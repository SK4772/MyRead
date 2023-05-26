
<template>
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
        v-model="keyword"
        style="width: 200px; margin-right: 10px"
        clearable
        @clear="search"
        @keydown.enter.native="search"
      ></el-input>
    </el-menu-item>
    <el-menu-item>
      <!-- 主题选择器 -->
      <el-switch v-model="value1" active-text="按月付费"> </el-switch>
    </el-menu-item>
    <el-menu-item>
      <!-- 国际化 -->
      <languages-vue></languages-vue>
    </el-menu-item>
    <el-menu-item>
      <!-- 头像 -->
      <el-avatar> user </el-avatar>
    </el-menu-item>
  </el-menu>
</template>

<script>
import languagesVue from "./nav/languages.vue";

export default {
  components: {
    languagesVue,
  },
  data() {
    return {
      value1: true,
      value2: true,
      keyword: "",
    };
  },
  methods: {
    // 搜索框绑定事件
    search() {},
    //国际化切换语言
    handleSelect() {},
  },
};
</script>

<style>
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
</style>