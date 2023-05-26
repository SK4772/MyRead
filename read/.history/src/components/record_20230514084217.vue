<template>
  <div class="page">
  <el-calendar v-model="value" class="panel">
    <template
         slot="dateCell"
         slot-scope="{date, data}">
         <p> <!--这里原本有动态绑定的class，去掉-->
            {{ data.day.split('-').slice(1).join('-') }}<br /> {{dealMyDate(data.day)}}
         </p>
     </template>
</el-calendar>
  </div>
</template>

<script>
export default {
  data() {
        return {
            resDate: [
                {"date":"2019-12-20","content":"放假"},
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
        }
    }

————————————————
版权声明：本文为CSDN博主「种麦南山下」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/conving/article/details/106717853

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
.panel{
    
}
</style>