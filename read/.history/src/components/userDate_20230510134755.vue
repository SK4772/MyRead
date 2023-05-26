<template>
  <div class="page">
    <div class="progress_panel">
      <el-progress type="circle" :percentage="25" width:40px></el-progress>
    </div>
    <div class="update_panel">
      <el-upload
        class="upload"
        drag
        action=""
        :file-list="filelist"
        :auto-upload="false"
        :on-change="handleChange"
        :on-remove="handleRemove"
        multiple
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip" slot="tip">
          只能上传jpg/png文件，且不超过500kb
        </div>
      </el-upload>
    </div>
    <div class="words_panel"></div>
  </div>
</template>

<script>
export default {
  name: "Upload",
  data() {
    return{
      filelist:""
    }
  },
  methods:{
    handleChange() {
       const formdata = new FormData();
       formdata.append("file", this.fileList[0].raw);
        this.$axios
        .post("/test", {
          "file": formdata,
        })
        .then((response) => {
          console.log(this.show);
          // this.translate=response.date.translation[0];
          this.translate = response.data.translation[0];
          this.EnglishSpeech = response.data.speakUrl;
          this.ChineseSpeeech = response.data.tSpeakUrl;
          console.log(response);
        });
       this.handleRemove();
    },
    handleRemove(){
      this.filelist=[];
    }
  }
};
</script>

<style>
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
  margin: 0 auto;
  flex-direction: column;
}
.progress_panel {
  position: absolute;
  left: 40px;
  top: 5px;
  border-radius: 50px;
  background: rgb(50, 50, 50, 0.3);
  color: white;
  height: 150px;
  width: 200px;
  padding: 20px;
  display: flex;
}
.update_panel {
  position: absolute;
  left: 500px;
  top: 5px;
  border-radius: 50px;
  background: rgb(50, 50, 50, 0.3);
  color: white;
  height: 150px;
  width: 800px;
  padding: 20px;
  display: flex;
}
.words_panel {
  position: absolute;
  left: 20px;
  top: 200px;
  background: rgb(50, 50, 50, 0.3);
  color: white;
  height: 400px;
  width: 1400px;
  padding: 20px;
  display: flex;
}
.upload{
  position: absolute;
  left: 250px;
  top: 5px;
  color: white;
  width: px;

}
</style>