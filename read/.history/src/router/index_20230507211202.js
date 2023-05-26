import Vue from 'vue' //引入 Vue
import VueRouter from 'vue-router' //引入 Vue 路由
import Index from '@/components/index.vue';
import Learning from '@/components/word1'
import Essay from '@/components/essay'

Vue.use(VueRouter); //安装插件

export const constantRouterMap = [
    //配置默认的路径，默认显示登录页
    { path: '/', component: () => import('@/components/login') },
    { path: '/home', component: () => import('@/components/index') },
    //配置测试页面，使用时需要使用 path 路径来实现跳转
    { path: '/test', component: () => import('@/views/test') },

    //配置测试页面，使用时需要使用 path 路径来实现跳转
    { path: '/test1', component: () => import('@/components/word1.vue') },

    //一级路由
    {
        path: '/index',
        name: 'index',
        component: Index,
        //路由嵌套
        children: [
            { path: '/home/wordLearning/:id',
              name:"Learning",
             component: Learning },
             { path: '/home/wordLearning/:id',
              name:"Learning",
             component: Learning },
            { path: '/index/menu2', component: () => import('@/components/essay.vue') },

        ]
    }
]

export default new VueRouter({
    // mode: 'history', //后端支持可开
    scrollBehavior: () => ({ y: 0 }),
    routes: constantRouterMap //指定路由列表
})