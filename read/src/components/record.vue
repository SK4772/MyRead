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
               
            ]
        }

    },
    mounted(){
        this.getDataList()

    },
    methods:{
        
        dealMyDate(v) {
            console.log(v)
            let len = this.resDate.length
            let res = ""
            for(let i=0; i<len; i++){
                if(this.resDate[i].date == v) {
                    res = "学习单词数为"+this.resDate[i].content
                    break
                }
            }
            return res
        },
        getDataList(){
            this.$axios.post("/word/getdatelist",{
                "userid":this.$route.params.id
            }).then((res)=>{
                console.log(res)
                this.resDate=res.data
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

.is-selected {
    color: #1989FA;
  }
</style>