<template>
  <div class="test">
    <el-button
      v-for="(item, index) in com_list"
      :key="index"
      @click="currentTab = item"
      >{{ item }}</el-button
    >
    <component :is="iscomponent"></component>
  </div>
</template>

<script>


export default {
  methods: {
    tran() {
      this.$getMp3Url("hello").then((res) => {
        console.log(res);
        console.log(res.speakUrl);
      });
    },
  },
  computed: {
    iscomponent() {
      return "tab-" + this.currentTab.toLowerCase();
    },
  },
};
</script>

<style>
.test{
  height: 100%;

}
</style>