<template>
  <div class="page">
    <div class="mainbody">
      <audio ref="phonePlayer" :src="phoneUrl"></audio>
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
      <div class="btn-panel" id="1">
        <!-- <el-radio-group v-model="radio" class="btn-panel">
          <el-radio class="answer-btn-container button" :label="3">{{
            option1
          }}</el-radio>
          <el-radio class="answer-btn-container button" :label="6">{{
            option2
          }}</el-radio>
          <el-radio class="answer-btn-container button" :label="9">{{
            option3
          }}</el-radio>
        </el-radio-group> -->
        <el-table
          ref="singleTable"
          :data="tableData"
          :show-header="false"
          highlight-current-row
          @current-change="handleCurrentChange"
          style="width: 100%"
        >
          <el-table-column type="index" width="50"> </el-table-column>
          <el-table-column property="date" label="日期" width="120">
          </el-table-column>
          <el-table-column property="name" label="姓名" width="120">
          </el-table-column>
          <el-table-column property="address" label="地址"> </el-table-column>
        </el-table>
        <div class="third">
          <el-button id="next" round v-on:click="check()">确认答案</el-button>
        </div>
      </div>
      <div id="2" class="btn-panel" style="display: none">
        <div class="page2_1">{{ isTrue }}</div>
        <div class="page2_2">{{ word.explain }}</div>
        <div class="page2_3">{{ wordGroup }}</div>
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
      select: "",
      trueSelect: "",
      answer: "",
      wordID: "",
      tableData: [
        {
          date: "选项A",
          name: "",
        },
        {
          date: "选项B",
          name: "",
        },
        {
          date: "选项C",
          name: "",
        },
        {
          date: "选项D",
          name: "",
        },
      ],
    };
  },
  mounted() {
    // this.getWord();
    this.getWortList();
  },
  methods: {
    // ...mapActions("word", ["getWordData"]),
    // async getWord() {
    //   this.words = await this.getWordData();
    //   this.word = this.words[0];
    //   this.speech = this.words[0].usSpeech;
    //   console.log(this.words);
    // },
    // async getData() {
    //   let res = await this.$axios.get("/find").then((response) => {
    //     this.users = response.data;
    //   });
    // },
    handleCurrentChange(val) {
      this.select = val;
    },
    resetWord() {
      this.$axios
        .post("/word/resetWord", {
          userid: this.$route.params.id,
          wordid: this.wordID,
        })
        .then((response) => {
          console.log(response);
        });
    },
    learnWord() {
      this.$axios
        .post("/word/learnWord", {
          userid: this.$route.params.id,
          wordid: this.wordID,
        })
        .then((response) => {
          console.log(response);
        });
    },
    getSelectMeaning(wordmeaning) {
      this.$axios
        .post("/word/getSelectMeaning", {
          wordid: this.wordID,
        })
        .then((response) => {
          console.log(123)
          console.log(response);
          const arr = [0, 1, 2, 3];
          // 生成随机数组
          let sq = this.generateRandomThreeNumsArr(arr, 3);
          var i;
          for (i = 0; i < 3; i++) {
            if (!sq.includes(i)) {
              this.trueSelect = i;
              console.log(this.trueSelect);
              console.log(wordmeaning);
            }
          }

          this.tableData[sq[0]].name = response.data[0];
          this.tableData[sq[1]].name = response.data[1];
          this.tableData[sq[2]].name = response.data[2];
          
        });
    },
    generateRandomThreeNumsArr(arr, num) {
      const outputArr = []; // 输出数组
      while (outputArr.length < num) {
        const temp = (Math.random() * arr.length) >> 0;
        outputArr.push(arr.splice(temp, 1).toString() * 1);
      }
      return outputArr;
    },
    getWortList() {
      this.$axios
        .post("/word/getNoWord", {
          userid: this.$route.params.id,
        })
        .then((response) => {
          console.log(response);
          this.wordID = response.data[0];
          console.log(this.wordID);
          wathis.getWordById();
          this.speech = this.word.usSpeech;
          console.log(this.word.meaning  )
          this.getSelectMeaning(this.word.meaning);
        });
    },
    getWordById() {
      this.$axios
        .post("/word/getWordDetail", {
          id: this.wordID,
        })
        .then((response) => {
          console.log(response);
          this.word = response.data;
        });
    },

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
    next() {
      if (this.answer == true) {
        this.learnWord();
      } else if (this.answer == false) {
        this.resetWord();
      }
      console.log(123);
      this.getWortList();
      this.tableData[0].name = "";
      this.tableData[1].name = "";
      this.tableData[2].name = "";
      this.tableData[3].name = "";
      document.getElementById("1").style.display = "";
      document.getElementById("2").style.display = "none";
    },
  },
  created() {},
  computed: {
    wordGroup() {
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
      if (this.phoneType === 1) return this.word.usSpeech;
      else return this.word.ukSpeech;
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
  color: white;
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