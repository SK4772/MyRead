<template>
  <div style="display: inline-flex">
    <el-input
      v-model="keyword"
      :disabled="disabled"
      :placeholder="placeholder"
      prefix-icon="el-icon-search"
      style="width: 350px;margin-right: 10px"
      clearable
      @clear="search"
      @keydown.enter.native="search"></el-input>
    <el-button
      :disabled="disabled"
      icon="el-icon-search"
      type="primary"
      @click="search">
      搜索
    </el-button>
  </div>
</template>
 
<script>
    export default {
        name: "Search",
        props:{
            disabled:{
                type:Boolean,
                default:true
            },
            placeholder:{
                type:String,
                default: '请输入昵称进行搜索，可以直接回车搜索...'
            }
        },
        data(){
            return {
                keyword:''
            }
        },
        methods:{
            search(){
                this.$emit("search",['search',this.keyword])
            }
        }
    }
</script>
 
<style scoped>
 
</style>