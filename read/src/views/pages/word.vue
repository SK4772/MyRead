<template>
  <div class="pages">
    <div class="first">
      <audio ref="phonePlayer" :src="speech"></audio>
      <span class="first_word">{{ words[i].componet}}</span>
      <div>
        <button class="phone-switch-btn" @click="switchPhone()">
            <span>{{ phonelog }}</span>
          </button>
        <span @click="playPhone">{{ phonetic }}</span>
      </div>
    </div>
    <div class="second">
      <el-radio-group
        v-model="radio"
        style="display: flex; flex-flow: column nowrap; justify-content: center">
        <el-radio :label="3">你好</el-radio>
        <el-radio :label="6">再见</el-radio>
        <el-radio :label="9">选择</el-radio>
      </el-radio-group>
    </div>
    <div class="third">
      <el-button id="next" round v-on:click="nextWord()">确认答案</el-button>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  data() {
    return {
      words: [],
      radio: 3,
      // 当前学习的单词ID
      i: 0,
      //当前学习的单词
      word: "hello",
      phoneType:0,
      speech:"",
      phonetic:"",
    };
  },
  mounted() {
    // this.getData();
    this.getWord();
  },
  methods: {
    ...mapActions("word", ["getWordData"]),
    async getWord() {
      this.words = await this.getWordData();
      console.log(this.words);
    },
    async getData() {
      let res = await this.$axios.get("/find").then((response) => {
        this.users = response.data;
      });
    },
    switchPhone() {
      if (this.phoneType === 0) this.phoneType = 1;
      else if (this.phoneType === 1) this.phoneType = 0;
      setTimeout(() => {
        this.playPhone();
      }, 200);
    },
    nextWord() {
      document.getElementById("next").blur();

      this.i += 1;
      this.word = this.words[this.i].word;
      //  length=this.words.length;
      //  if(i<length){
      //   this.word=this.words[this.i];
      //  }
    },
    playPhone() {
      this.$refs.phonePlayer.play();
    },
  },
  computed:{
     phonelog() {
      if (this.phoneType === 0) {
        this.speech=this.words[this.i].usSpeech;
        this.phonetic=this.words[this.i].usPhonetic;
        return "美";
        }
      else if (this.phoneType === 1) {
        this.speech=this.words[this.i].ukSpeech;
        this.phonetic=this.words[this.i].ukPhonetic;
        return "英";
        }
    },
  }
  // watch: {
  //   word: {
  //     handler: function () {
  //       this.$getMp3Url(this.word).then((res) => {
  //         console.log(res);
  //         console.log(res.speakUrl);
  //         this.phone = res.basic.phonetic;
  //         this.phoneUrl= res.speakUrl;
  //         // return url;
  //       });
  //     },
  //     immediate: true,
  //     // let url = "";
  //     // this.$getMp3Url("hello").then((res) => {
  //     //   console.log(res);
  //     //   console.log(res.speakUrl);
  //     //   this.phone = res.basic.phonetic;
  //     //   this.a = res.speakUrl
  //     //   return url;
  //     // });
  //   },
  // },
};
</script>

<style>
.pages {
  margin-top: 60px;
  height: 90%;
  width: 100%;
  color: aliceblue;
}
.first {
  height: 15%;
  width: 100%;
  font-size: 50px;
  font-family: "Word";
}
.first_word {
  position: absolute;
  left: 300px;
  top: 30px;
}
.word {
  height: 100px;
  font-family: "Word";
}
.second {
  height: 50%;
  width: 100%;
}
.el-radio {
  margin-top: 30px;
  position: relative;
  font-family: "Word";
  display: inline-block;
  height: 100px;
  width: 100%;
  background-color: rgb(238, 242, 245);
  border-color: transparent;
  border-radius: 10px;
}

.el-radio__label {
  position: relative;
  margin-top: -20px;
  font-size: 30px;
}
.select {
  height: 100px;
}
.third {
  height: 30px;
  width: 100%;
}
</style>