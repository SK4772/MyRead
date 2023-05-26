<template>
  <el-select v-model="value" placeholder="language">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
</template>

<script>
  export default {
    data() {
      return {
        options: [{
          value: '选项1',
          label: '简体中文'
        }, {
          value: '选项2',
          label: '繁体中文'
        }, {
          value: '选项3',
          label: 'English'
        }],
        value: '选项1'
      }
    }
  }
</script>

<style >

</style>