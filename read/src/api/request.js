//引入axios
import axios from "axios"
//引入进度条
import nprogress from "nprogress"
// start：进度条开始  done：进度条结束
//引入进度条样式
import "nprogress/nprogress.css"

//引入store模块以便于获取uuid
import store from '@/store'

//1、二次封装
const requests = axios.create({
    baseURL:'',
    timeout:5000,
})

//2、配置拦截器
requests.interceptors.request.use(config => {
    //config内主要是对请求头Header配置
    //比如添加token

    /*此处可以为config添加请求头之类的信息。*/
    nprogress.start()
    return config;
})

//3、配置响应拦截器
requests.interceptors.response.use((res) => {
    //成功的回调函数

    //进度条结束
    nprogress.done()
    return  res.data;
},(error) => {
    //失败的回调函数
    console.log("响应失败"+error)
    return Promise.reject(new Error('fail'))
})


//4、对外暴露
export default requests;