<template>
  <div class="page">
    <div class="essay_panel"> {{show}}
      </div>
    <div class="trans_panel">
     
     
    </div>
    <div class="note_panel"></div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "Learning",
  data() {
    return {
      essays: [],
      essay: "",
      i: 0,
      userid:"",
      fileList: [],
      show:"",
      url:""
    };
  },
  mounted() {
    // this.getPath();
    this.readFile();
    this.getEssay();
  },
  computed:{
    async text(){
     
      return this.show;
    }

  },
  methods: {
    // ...mapActions("essay", ["getEssayPath"]),
    // async getPath() {
    //   this.essays = await this.getEssayPath();
    //   this.essay = this.essays[this.i];
    //   this.essayPath = this.essay.essayPath;
    //   this.uesrid=this.$route.params.id;
            
    // },
    // async getFile() {
    //   const formdata = new FormData();

    //   formdata.append("file", this.fileList[0].raw);
    //   console.log(formdata);
    //   let result = await this.addPet(formdata);
    // },
    getEssay() {
      this.userid=this.$route.params.id;
      this.$axios.post("/getEssay", {
          username: this.userid,
        })
        .then( (response) => {
          console.log(response.data)
          this.essays=response.data;
          this.essay=this.essays[0];
          console.log(this.essay)
          this.url=this.essay.essayPath;
          
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    handleChange(file,fileList){
      this.fileList = []
      this.fileList.push(file)
    },
    getTxt(){
      this.readFile(this.url);
    },
    readFile() {
      //注意很重要
      var thst=this
        thst.$axios.get(that.url).then(function (res) {
        console.log(res.data);
        thst.show=res.data;
        return res.data;
      
    }).catch(function (error) {
      console.log(error);
    }); 
  },
  },
};
</script>

<style scoped>
.page {
  position: relative;
  border-radius: 50px;
  background-image: url("../images/bg1.jpg");
  height: 100%;
  width: 100%;
  background-color: #1f1f1f;
  display: flex;
  justify-content: center;
  align-items: center;
}
.essay_panel {
  position: absolute;
  left: 5px;
  top:5px;
  border-radius: 50px;
  background: rgb(50,50,50,0.3);
  color: white;
  height: 400px;
  width: 800px;
  min-width: 600px;
  padding: 20px;
  display: flex;
  /* flex-direction: column; */
}
.trans_panel {
  position: absolute;
  left: 5px;
  top:480px;
  border-radius: 50px;
  background: #373737;
  opacity: 0.3;
  height: 100px;
  min-width: 800px;
  padding: 20px;
  display: flex;
  /* flex-direction: column; */
}
.note_panel {
  position: absolute;
  left: 950px;
  top:5px;
  border-radius: 50px;
  background: #373737;
  opacity: 0.3;
  height: 550px;
  min-width: 300px;
  padding: 20px;
  display: flex;
  /* flex-direction: column; */
}
.title-panel {
  height: 50px;
  min-height: 60px;
}

</style>