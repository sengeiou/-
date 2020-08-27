import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index')
    }]
  },

  {
    path: '/资金管理',
    component: Layout,
    redirect: '/salary/index',
    name: '资金概况',
    meta: { title: '资金概况', icon: 'example' },
    children: [
      {
        path: '/index',
        name: '工资分布',
        component: () => import('@/views/huadi/index'),
        meta: { title: '工资分布', icon: 'table' }
      },
      {
        path: '/salary/pay',
        name: '资金类型占比',
        component: () => import('@/views/huadi/pay'),
        meta: { title: '资金类型占比', icon: 'tree' }
      }
    ]
  },

  {
    path: '/project',
    component: Layout,
    redirect: '/project/info',
    name: '项目概况',
    meta: { title: '项目概况', icon: 'example' },
    children: [
      {
        path: '/info',
        name: '项目完成情况',
        component: () => import('@/views/huadi/project'),
        meta: { title: '项目完成情况', icon: 'table' }
      }
    ]
  },
  
  {
    path: '/department',
    component: Layout,
    redirect: '/department/people',
    name: '部门管理',
    meta: { title: '部门管理', icon: 'example' },
    children: [
      {
        path: '/people',
        name: '部门人数分布',
        component: () => import('@/views/huadi/department'),
        meta: { title: '部门人数分布', icon: 'table' }
      },
      {
        path: '/addDepartment',
        name: '添加部门',
        component: () => import('@/views/huadi/addDepartment'),
        meta: { title: '添加部门', icon: 'table' }
      },
      {
        path: '/changePower',
        name: '修改部门权限',
        component: () => import('@/views/huadi/changePower'),
        meta: { title: '修改部门权限', icon: 'table' }
      }
    ]
  },
  {
    path: '/user',
    component: Layout,
    redirect: '/user/manage',
    name: '用户管理',
    meta: { title: '用户管理', icon: 'example' },
    children: [
      {
        path: '/manage',
        name: '管理用户',
        component: () => import('@/views/huadi/user'),
        meta: { title: '管理用户', icon: 'table' }
      }
    ]
  },

  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
