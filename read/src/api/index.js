/*此模块用来集中所有请求，便于以后接口的修改*/
import requests from "../api/request";

//依次写请求接口的方法即可。

/*首页所需接口*/
//1.获取首页基本数据
export const reqWordData = ()=> {
    return requests({
        url:"/word/getAll",
        method:'get'
    })

}
export const reqEssayPath = ()=> {
    return requests({
        url:"/find",
        method:'get'
    })

}
export const reqStudyRecord = ()=> {
    return requests({
        url:"/word/getUserRecord",
        method:'get'
    })
}
export const reqtxt = (data)=> {
    return requests({
        url:"/test",
        method:"post",
        data,
    })
}
