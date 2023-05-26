const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})
module.exports = {
  devServer: {

    port: 8081, // 此处修改你想要的端口号

    proxy: {
      "/": {
        ws: false,
        target: "http://localhost:8080",
        changeOrigin: true,

      }
    }

  }

}

