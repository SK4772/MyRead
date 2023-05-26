<template>
  <div class="page">
  <el-calendar>
  <!-- 这里使用的是 2.5 slot 语法，对于新项目请使用 2.6 slot 语法-->
  <template
    slot="dateCell"
    
    slot-scope="{date, data}">
    <p :class="data.isSelected ? 'is-selected' : ''">
      {{ data.day.split('-').slice(1).join('-') }} {{ data.isSelected ? '✔️' : ''}}<br /> {{dealMyDate(data.day)}}
    </p>
  </template>
</el-calendar>
  </div>
</template>

<script>
export default {
    data(){
        return{
             resDate: [
                {"date":"2023-05-20","content":"放假"},
                {"date":"2019-12-26","content":"去交电费"},
                {"date":"2019-11-26","content":"去学习vue"}
            ]
        }

    },
    methods:{
        
        dealMyDate(v) {
            console.log(v)
            let len = this.resDate.length
            let res = ""
            for(let i=0; i<len; i++){
                if(this.resDate[i].date == v) {
                    res = this.resDate[i].content
                    break
                }
            }
            return res
        },
        getDataList(){
            this.$axios.post("/word/getdatelist",{
                "useid":this.$
            })
        }

    }
    
    

}
</script>

<style>
.page {
  height: 650px;
  background-color: #1f1f1f;
  display: flex;
  justify-content: center;
  
}
.el-progress-bar__outer {
    
    border-radius: 100px;
    background-color: #EBEEF5;
    overflow: hidden;
    position: relative;
    vertical-align: middle;
    top: -250px;
    left: -100px;
    width: 700px;
}
.is-selected {
    color: #1989FA;
  }
</style>