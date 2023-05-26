<template>
  <div class="page">
    <audio ref="phonePlayer" :src="EnglishSpeech"></audio>
    <audio ref="phonePlayer1" :src="ChineseSpeeech"></audio>
    <div class="essay_panel">
      <span>{{ show }}</span>
      <div>
        <el-button class="playbutton1" @click="playEspeech"> 播放 </el-button>
      </div>
    </div>
    <div class="trans_panel">
      <span>{{ translate }}</span>

      <el-button class="playbutton2" @click="playEspeech1"> 播放 </el-button>
    </div>
    <div class="note_panel">
      <span>Note</span>
      <div class="shownote_panel">{{oldnode}}</div>
      <el-input
        type="textarea"
        autosize
        placeholder="请输入内容"
        v-model="node"
        maxlength="500"
        show-word-limit
      >
      </el-input>
      <el-button class="notebutton" @click="getnote"> 确认 </el-button>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "Learning",
  data() {
    return {
      oldnode:"我是笔记",
      node:"",
      essays: [],
      essay: "",
      i: 0,
      userid: "",
      essayid:"",
      fileList: [],
      show: "",
      url: "",
      translate: "",
      EnglishSpeech: "",
      ChineseSpeeech: "",
    };
  },
  mounted() {
    // this.getPath();

    this.getEssay();
  },
  computed: {
    async text() {
      return this.show;
    },
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
    getEssayList(){
      this.$axios
        .post("/getTranslate", {
          title: this.show,
        })
        .then((response) => {}

    },
    getTranslate() {
      this.$axios
        .post("/getTranslate", {
          title: this.show,
        })
        .then((response) => {
          console.log(this.show);
          // this.translate=response.date.translation[0];
          this.translate = response.data.translation[0];
          this.EnglishSpeech = response.data.speakUrl;
          this.ChineseSpeeech = response.data.tSpeakUrl;
          console.log(response);
        });
    },
    getEssay() {
      console.log(this.$route.params.id)
      this.userid = this.$route.params.id;
      this.$axios
        .post("/essay/getEssayDetail", {
          userid: this.userid,
        })
        .then((response) => {
          console.log(response.data);
          
          this.essays = response.data;
          this.essay = this.essays[0];
          console.log(this.essay);
          this.url = this.essay.essayPath;
          this.readFile();
          this.getTranslate();
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    handleChange(file, fileList) {
      this.fileList = [];
      this.fileList.push(file);
    },
    getTxt() {
      this.readFile(this.url);
    },
    readFile() {
      //注意很重要
      console.log(this.url);
      this.$axios
        .get(this.url)
        .then((response) => {
          console.log(response);
          this.show = response.data;
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    playEspeech() {
      this.$refs.phonePlayer.play();
    },
    playEspeech1() {
      this.$refs.phonePlayer1.play();
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
  top: 5px;
  border-radius: 50px;
  background: rgb(50, 50, 50, 0.3);
  color: white;
  height: 400px;
  width: 800px;
  min-width: 600px;
  padding: 20px;
  display: flex;
  /* flex-direction: column; */
}
.el-button {
  height: 40px;
  border-radius: 11px;
  background: rgb(50, 50, 50, 0.3);
  color: white;
}
.playbutton1 {
  position: absolute;
  top: 350px;
  left: 700px;
}
.playbutton2 {
  position: absolute;
  top: 50px;
  left: 700px;
}
.notebutton {
  position: absolute;
  top: 500px;
  left: 140px;
}
.trans_panel {
  position: absolute;
  color: white;
  left: 5px;
  top: 480px;
  border-radius: 50px;
  background: rgb(50, 50, 50, 0.3);

  height: 100px;
  min-width: 800px;
  padding: 20px;
  display: flex;
  /* flex-direction: column; */
}
.note_panel {
  position: absolute;
  left: 950px;
  top: 5px;
  border-radius: 50px;
  background: rgb(50, 50, 50, 0.3);
  color: white;
  height: 550px;
  min-width: 300px;
  padding: 20px;
  display: flex;
  flex-direction: column;
}
.shownote_panel{
  
  border-radius: 50px;
  background: rgb(50, 50, 50, 0.3);
  color: white;
  height: 300px;
  min-width: 300px;
  padding: 20px;
  display: flex;
  flex-direction: column;

}
.title-panel {
  height: 50px;
  min-height: 60px;
}
</style>