// 导入组件
import Vue from 'vue';
import Router from 'vue-router';
// 登录
import login from '@/views/login';
// 首页
import index from '@/views/index';
/**
 * 基础菜单
 */
// 商品管理
import Goods from '@/views/goods/Goods';
// 机器信息管理
import Machine from '@/views/machine/Machine';
// 货道信息管理
import MachineAisle from '@/views/machine/MachineAisle';
/**
 * 订单管理
 */
// 交易订单
import Word from '@/views/pay/Word';
/**
 * 系统管理
 */
// 用户管理

// 公司管理

// 系统环境变量

/**
 * 支付管理
 */
// 支付配置信息
import MachineConfig from '@/views/machine/MachineConfig';
// 支付配置
import Config from '@/views/pay/Config';
/**
 * 数据监控
 */
// 监控查询
import druidLogin from '@/views/druid/login';

// 图表界面
import statistics from '@/views/charts/statistics';

// 启用路由
Vue.use(Router);

// 导出路由
export default new Router({
    routes: [{
        path: '/',
        name: '',
        component: login,
        hidden: true,
        meta: {
            requireAuth: false
        }
    }, {
        path: '/login',
        name: '登录',
        component: login,
        hidden: true,
        meta: {
            requireAuth: false
        }
    }, {
        path: '/index',
        name: '首页',
        component: index,
        iconCls: 'el-icon-tickets',
        children: [{
            path: '/goods/Goods',
            name: '商品管理',
            component: Goods,
            meta: {
                requireAuth: true
            }
        },{
            path: '/essay/essa',
            name: '家居管理',
            component: () => import('../views/goods/product'),
            meta: {
              requireAuth: true
            }
          },{
          path: '/goods/Product',
          name: '家居管理',
          component: () => import('../views/goods/product'),
          meta: {
            requireAuth: true
          }
        },{
            path: '/machine/Machine',
            name: '机器信息管理',
            component: Machine,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/machine/MachineAisle',
            name: '货道信息管理',
            component: MachineAisle,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/Word/wordmessage',
            name: '交易订单',
            component: Word,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/category/category',
            name: '商品分类',
            component: ()=> import('../views/category/category'),
            meta: {
                requireAuth: true
            }
        }, {
            path: '/druid/login',
            name: '监控查询',
            component: druidLogin,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/charts/statistics',
            name: '数据可视化',
            component: statistics,
            meta: {
                requireAuth: true
            }
        }]
    }]
})
