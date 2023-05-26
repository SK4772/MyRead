import { reqEssayPath,reqfile } from "@/api"

// 响应组件中用户的动作、处理业务逻辑，供组件使用该函数并传参、调用mutations中的函数  可以从后台请求数据即异步请求
const actions = {
    //用来发送请求并存储请求来的数据。
    async getEssayPath(){
        const result = await reqEssayPath()  
        console.log(result)
        return result.message.data
      
    },
    async addfile({ commit }, data) {
        const result = await reqtxt(data)
        console.log(result)
        if (result.meta.status == 200) {
            return result.message;
        } else {
            return Promise.reject(new Error("fail"))
        }
    },
    async getTrans({ commit }, data) {
        const result = await reqtxt(data)
        console.log(result)
        if (result.meta.status == 200) {
            return result.message;
        } else {
            return Promise.reject(new Error("fail"))
        }
    },
    


}

// 修改state中的数据
const mutations = {
    // 用来存储请求来的数据至state上或者修改state的数据。
    // GETCODE(state, code) {
    //     state.code = code
    // },
    
}

//state:存储数据
const state = {
    // 用来存储公共的数据。
    // code: '',
    // token: getToken(),
}

//getters：//用于对state中的数据加工,当state中的数据需要经过加工后再使用时
const getters = {
    
}


export default {
    namespaced: true,
    actions,
    mutations,
    state,
    getters
}