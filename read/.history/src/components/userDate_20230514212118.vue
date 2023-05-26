<template>
  <div class="page" id="building">
    <el-table
      :data="WordData"
      height="400"
      style="width: 100%"
      :row-class-name="tableRowClassName"
    >
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="详情">
              <span>{{ props.row.explain }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column prop="componet" label="单词" width="100px">
      </el-table-column>
      <el-table-column prop="meaning" label="意思" width="100px">
      </el-table-column>

      <el-table-column prop="usPhonetic" label="英式音标" width="100px">
      </el-table-column>
      <el-table-column prop="ukPhonetic" label="美式音标" width="100px">
      </el-table-column>
      <el-table-column prop="wordGroup" label="词组一" width="100px">
      </el-table-column>
      <el-table-column prop="wordGroupMeaning" label="词组一翻译">
      </el-table-column>
      <el-table-column prop="wordGroup1" label="词组二" width="100px">
      </el-table-column>
      <el-table-column prop="wordGroupMeaning1" label="词语二翻译">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="加入自定义词书"
      >
        <template slot-scope="scope">
          <el-button
            type="text"
            size="small"
            @click="addHandle(scope.row.word_id)"
            >添加</el-button
          >
          <el-button
            type="text"
            size="small"
            @click="deleteHandle(scope.row.word_id)"
          >
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
  <!-- <div class="progress_panel">
      <el-progress type="circle" :percentage="25" width:40px></el-progress>
    </div>
    <div class="update_panel">
      <el-upload
        class="upload"
        drag
        action=""
        :file-list="file"
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
    </div> -->
</template>

<script>
export default {
  name: "Upload",
  data() {
    return {
      filelist: [],
      WordData: [],
      WordList: [],
      userid: "",
    };
  },
  mounted() {
    this.getWordData();
  },
  methods: {
    getWordData() {
      console.log(this.$route.params.id);
      this.userid = this.$route.params.id;
      this.$axios.get("/word/getAllWord").then((response) => {
        this.WordData = response.data;
        console.log(response.data);
        this.userid = this.$route.params.id;
      });
    },
    addHandle(word_id) {
      this.$axios
        .post("/word/addUserWord", {
          userid: this.userid,
          wordid: word_id,
        })
        .then((response) => {
          if(response.data==true){
             this.$message({
          type: "success",
          message: "单词添加成功",
        });
          }else{
            this.$message({
          type: "error",
          message: "单词已存在于您的词库",
        });
          
        }

          console.log(response.data);
        });
    },
    deleteHandle(word_id){
      
    }
    //读取文件，管理员界面要用
    handleChange(file) {
      let reader = new FileReader(); //先new 一个读文件的对象 FileReader
      if (typeof FileReader === "undefined") {
        //用来判断你的浏览器是否支持 FileReader
        this.$message({
          type: "info",
          message: "您的浏览器不支持文件读取。",
        });
        return;
      }
      //  reader.readAsText(file.raw, "gb2312");  //读.txt文件
      reader.readAsArrayBuffer(file.raw); //读任意文件
      reader.onload = (e) => {
        var ints = new Uint8Array(e.target.result); //要使用读取的内容，所以将读取内容转化成Uint8Array
        ints = ints.slice(0, 5000); //截取一段读取的内容
        let snippets = new TextDecoder("gb2312").decode(ints); //二进制缓存区内容转化成中文（即也就是读取到的内容）
        console.log("读取的内容如下：");
        console.log(snippets);
        this.WordList = snippets.split(",");
        console.log(this.userid);
        console.log(this.WordList);
        this.resetA();
      };
    },
    resetA() {
      this.$axios
        .post("/word/setWords", {
          list: this.WordList,
          userid: this.userid,
        })
        .then((response) => {
          console.log(response);
        });
    },

    // handleChange(file,fileList) {
    //   this.filelist.push(file);
    //  const formdata = new FormData();
    //  formdata.append("file", this.formref.fileList[0].raw);
    //   this.$axios
    //   .post("/test", {
    //     data : formdata,
    //   })
    //   .then((response) => {
    //    console.log(123);
    //     console.log(response);
    //   });
    // },
    // handleRemove(){
    //   this.filelist=[];
    // }
  },
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
  top: -100px;
  background: rgb(50, 50, 50, 0.3);
  color: white;
  height: 100px;
  width: 1400px;
  padding: 20px;
  display: flex;
}
.upload {
  position: absolute;
  left: 250px;
  top: 5px;
  color: white;
  width: px;
}
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
.demo-table-expand {
  margin-left: 100px;
  margin-bottom: 0;
  width: 1000px;
}
</style>