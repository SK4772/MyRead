<template>
  <div class="page">
    <div class="mainbody">
      <audio ref="phonePlayer" :src="speech"></audio>
      <div class="word-panel">
        <div class="word-text">
          <span>{{ word.componet }}</span>
        </div>
        <div class="phone">
          <button class="phone-switch-btn" @click="switchPhone()">
            <span>{{ phonelog }}</span>
          </button>
          <span @click="playPhone">{{ phone }}</span>
        </div>
      </div>
      <div class="btn-panel" id="1" style="display: none">
        <el-radio-group v-model="radio" class="btn-panel">
          <el-radio class="answer-btn-container button" :label="3">{{
            option1
          }}</el-radio>
          <el-radio class="answer-btn-container button" :label="6">{{
            option2
          }}</el-radio>
          <el-radio class="answer-btn-container button" :label="9">{{
            option3
          }}</el-radio>
        </el-radio-group>
        <div class="third">
          <el-button id="next" round v-on:click="check()">确认答案</el-button>
        </div>
      </div>
      <div id="2" class="btn-panel">
        <div class="page2_1">{{ isTrue }}</div>
        <div class="page2_2">{{word.explain}}</div>
        <div class="page2_3">{{wordGroup}}</div>
        <div class="page2_4">
          <el-button id="next" round v-on:click="next()">下一项</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "Learning",
  data() {
    return {
      words: [],
      radio: 3,
      // 当前学习的单词ID
      i: 0,
      //当前学习的单词
      word: "",
      phoneType: 0,
      speech: "",
      answer: "",
    };
  },
  mounted() {
    this.getWord();
  },
  methods: {
    ...mapActions("word", ["getWordData"]),
    async getWord() {
      this.words = await this.getWordData();
      this.word = this.words[0];
      this.speech = this.words[0].usSpeech;
      console.log(this.words);
    },
    async getData() {
      let res = await this.$axios.get("/find").then((response) => {
        this.users = response.data;
      });
    },
    setWort()
     this.$axios.post("/user/register", {
          username: this.account,
          password: this.password,
        })
        .then( (response) => {}),
    switchPhone() {
      if (this.phoneType === 0) this.phoneType = 1;
      else if (this.phoneType === 1) this.phoneType = 0;
      setTimeout(() => {
        this.playPhone();
      }, 200);
    },
    playPhone() {
      this.$refs.phonePlayer.play();
    },
    check() {
      if (this.radio == 3) {
        this.answer = true;
        document.getElementById("1").style.display = "none";
        document.getElementById("2").style.display = "";
      } else {
        this.answer = false;
          document.getElementById("1").style.display = "none";
        document.getElementById("2").style.display = "";
      }
    },
    next(){
      this.i=this.i+1;
      this.word=this.words[this.i];
      document.getElementById("1").style.display = "";
      document.getElementById("2").style.display = "none";
    },
  },
  created() {},
  computed: {
   
    wordGroup(){
      return this.word.wordGroup + this.word.wordGroupMeaning;
    },
    isTrue() {
      if (this.answer == true) {
        return "恭喜你回答正确";
      } else {
        return "回答错误";
      }
    },
    wordText() {
      return this.word.componet;
    },
    phonelog() {
      if (this.phoneType === 0) return "美";
      else if (this.phoneType === 1) return "英";
    },
    phone() {
      if (this.phoneType === 1) return this.word.ukPhonetic;
      else return this.word.usPhonetic;
    },
    phoneUrl() {
      if (this.phoneType === 1) return this.word.ukPhonetic;
      else return this.word.usPhonetic;
    },
    option1() {
      return this.word.meaning;
    },
    option2() {
      let j = this.i + 1;
      return this.word.meaning;
    },
    option3() {
      let j = this.i + 2;
      return this.word.meaning;
    },
    trans() {
      return this.word.meaning;
    },
  },
};
</script>

<style scoped>
.page {
  height: 600px;
  background-color: #1f1f1f;
  display: flex;
  justify-content: center;
  align-items: center;
}
.mainbody {
  border-radius: 50px;
  background: #373337;
  height: 500px;
  min-width: 800px;
  padding: 20px;
  display: flex;
  flex-direction: column;
}
.word-text {
  text-align: center;
  font-family: Helvetica;
  font-size: 45px;
  letter-spacing: 4px;
}
.word-panel {
  height: 50px;
  min-height: 60px;
}
.btn-panel {
  display: flex;
  height: 30px;
  flex-direction: column;
  box-sizing: border-box;
  justify-content: space-evenly;
  margin-top: 20px;
  flex: 1;
}
.phone {
  text-align: center;
}
.phone-switch-btn {
  border-color: transparent;
  border-radius: 30%;
  position: relative;
  width: 40px;
  right: 5px;
}
.answer-btn-container {
  width: 100%;
  height: 100px;
  text-align: center;
}
.button {
  background-color: rgb(238, 242, 245);
  border-color: transparent;
  border-radius: 10px;
  text-align: center;
}
.answer-btn-container button {
  width: 80%;
  height: 100px;
  background-color: rgb(238, 242, 245);
  border-color: transparent;
  border-radius: 10px;
}
.btn-text {
  font-family: Helvetica;
  font-size: 24px;
}
.shake {
  -webkit-animation: shake-horizontal 0.5s
    cubic-bezier(0.455, 0.03, 0.515, 0.955) both;
  animation: shake-horizontal 0.5s cubic-bezier(0.455, 0.03, 0.515, 0.955) both;
}

.page2_1 {
  background: rgb(246, 242, 242);
  border-radius: 10px;
  /* opacity: 0.4; */
  backdrop-filter: blur(5px);
  height: 50px;
}
.page2_2 {
  height: 200px;
  background-color: white;
}
.page2_3 {
  height: 50px;
  background-color: white;
}
.page2_4 {
  height: 50px;
  background-color: white;
}
</style>