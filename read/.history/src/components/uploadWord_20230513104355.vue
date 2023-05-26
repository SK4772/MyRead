<template>
  <div class="page">
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
    </div>
    <div class="words_panel">
      <el-table
          v-loading="isloading"
    element-loading-text="拼命加载中"
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(0, 0, 0, 0.8)"
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
        <el-table-column prop="meaning" label="翻译" width="100px">
        </el-table-column>
        
        <el-table-column prop="usPhonetic" label="英式音标" width="100px">
        </el-table-column>
        <el-table-column prop="ukPhonetic" label="美式音标" width="100px">
        </el-table-column>
        <el-table-column prop="wordGroup" label="词组" width="100px">
        </el-table-column>
        <el-table-column prop="wordGroupMeaning" label="词组翻译">
        </el-table-column>
        <el-table-column prop="wordGroup1" label="词组" width="100px">
        </el-table-column>
        <el-table-column prop="wordGroupMeaning1" label="词组翻译">
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "UploadWord",
  data() {
    return {
      filelist: [],
      WordData: [],
      WordList: [],
      userid: "",
       isloading:false

    };
  },
  mounted() {
    this.getWordData();
  },
  methods: {
         resetA(){
       this.$axios
        .post("/word/setWords", {
          "list": this.WordList,
          "userid": this.userid
        })
        .then((response) => {

          console.log(response);
        });
     },
    getWordData() {
        this.isloading=true
      console.log(this.$route.params.id);
      this.userid = this.$route.params.id;
      this.$axios.post("/word/getUserWord",
      ).then((response) => {
        this.WordData = response.data;
        setTimeout(()=> {
  this.isloading=false
,1000})
        console.log(response.data);
      });
      
    },
    
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
    
  },
};
</script>

<style>
.el-form-item__content {
 
    width: 1000px;

}
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
/* #building{
background:url("../assets/image/bj1.jpg");
width:100%;			
height:100%;			
position:fixed;
background-size:100% 100%;
} */
.update_panel {
  position: absolute;
  left: 300px;
  top: 0px;
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
  left: 0px;
  top: 200px;
  background: rgb(50, 50, 50, 0.3);
  color: white;
  height: 400px;
  width: 1400px;
  padding: 20px;
  display: flex;
}
</style>