import Vue from 'vue'
import Router from 'vue-router'
import { interopDefault } from './utils'
import scrollBehavior from './router.scrollBehavior.js'

const _2d0044b8 = () => interopDefault(import('..\\pages\\login\\email_login.vue' /* webpackChunkName: "pages/login/email_login" */))
const _f049e276 = () => interopDefault(import('..\\pages\\login\\face_login.vue' /* webpackChunkName: "pages/login/face_login" */))
const _280ae246 = () => interopDefault(import('..\\pages\\login\\findemailpass.vue' /* webpackChunkName: "pages/login/findemailpass" */))
const _1d45e218 = () => interopDefault(import('..\\pages\\login\\findphonepass.vue' /* webpackChunkName: "pages/login/findphonepass" */))
const _810acfec = () => interopDefault(import('..\\pages\\login\\phone_login.vue' /* webpackChunkName: "pages/login/phone_login" */))
const _bb85bc7c = () => interopDefault(import('..\\pages\\money\\allapply.vue' /* webpackChunkName: "pages/money/allapply" */))
const _65d06c7d = () => interopDefault(import('..\\pages\\money\\dealapply.vue' /* webpackChunkName: "pages/money/dealapply" */))
const _3f939b7a = () => interopDefault(import('..\\pages\\money\\moneyapply.vue' /* webpackChunkName: "pages/money/moneyapply" */))
const _d6dd0006 = () => interopDefault(import('..\\pages\\money\\myapply.vue' /* webpackChunkName: "pages/money/myapply" */))
const _69dbe66d = () => interopDefault(import('..\\pages\\money\\paysalary.vue' /* webpackChunkName: "pages/money/paysalary" */))
const _0873ba06 = () => interopDefault(import('..\\pages\\project\\allproject.vue' /* webpackChunkName: "pages/project/allproject" */))
const _57537757 = () => interopDefault(import('..\\pages\\project\\allprojectdetail.vue' /* webpackChunkName: "pages/project/allprojectdetail" */))
const _c9d216c4 = () => interopDefault(import('..\\pages\\project\\creatproject.vue' /* webpackChunkName: "pages/project/creatproject" */))
const _0eb99c7e = () => interopDefault(import('..\\pages\\project\\mypassproject.vue' /* webpackChunkName: "pages/project/mypassproject" */))
const _3a4ce89a = () => interopDefault(import('..\\pages\\project\\mywaitproject.vue' /* webpackChunkName: "pages/project/mywaitproject" */))
const _5d840c2a = () => interopDefault(import('..\\pages\\regist\\email_regist.vue' /* webpackChunkName: "pages/regist/email_regist" */))
const _2c399e77 = () => interopDefault(import('..\\pages\\regist\\face_regist.vue' /* webpackChunkName: "pages/regist/face_regist" */))
const _1a542318 = () => interopDefault(import('..\\pages\\regist\\phone_regist.vue' /* webpackChunkName: "pages/regist/phone_regist" */))
const _b40515de = () => interopDefault(import('..\\pages\\user\\avator.vue' /* webpackChunkName: "pages/user/avator" */))
const _032b9725 = () => interopDefault(import('..\\pages\\user\\basic_info.vue' /* webpackChunkName: "pages/user/basic_info" */))
const _51375fc7 = () => interopDefault(import('..\\pages\\user\\changepass.vue' /* webpackChunkName: "pages/user/changepass" */))
const _ca34c2e8 = () => interopDefault(import('..\\pages\\user\\user_info.vue' /* webpackChunkName: "pages/user/user_info" */))
const _f670a7d4 = () => interopDefault(import('..\\pages\\work\\dealgreatwork.vue' /* webpackChunkName: "pages/work/dealgreatwork" */))
const _039269b0 = () => interopDefault(import('..\\pages\\work\\dealnormalwork.vue' /* webpackChunkName: "pages/work/dealnormalwork" */))
const _651ac4bd = () => interopDefault(import('..\\pages\\work\\managegreatwork.vue' /* webpackChunkName: "pages/work/managegreatwork" */))
const _2da265e9 = () => interopDefault(import('..\\pages\\work\\managenormalwork.vue' /* webpackChunkName: "pages/work/managenormalwork" */))
const _4850b4d4 = () => interopDefault(import('..\\pages\\work\\updateworkinfo.vue' /* webpackChunkName: "pages/work/updateworkinfo" */))
const _1801d88b = () => interopDefault(import('..\\pages\\work\\workinfo.vue' /* webpackChunkName: "pages/work/workinfo" */))
const _53ee2f0f = () => interopDefault(import('..\\pages\\work\\changeworkinfo\\_id.vue' /* webpackChunkName: "pages/work/changeworkinfo/_id" */))
const _57f335a7 = () => interopDefault(import('..\\pages\\index.vue' /* webpackChunkName: "pages/index" */))

// TODO: remove in Nuxt 3
const emptyFn = () => {}
const originalPush = Router.prototype.push
Router.prototype.push = function push (location, onComplete = emptyFn, onAbort) {
  return originalPush.call(this, location, onComplete, onAbort)
}

Vue.use(Router)

export const routerOptions = {
  mode: 'history',
  base: decodeURI('/'),
  linkActiveClass: 'nuxt-link-active',
  linkExactActiveClass: 'nuxt-link-exact-active',
  scrollBehavior,

  routes: [{
    path: "/login/email_login",
    component: _2d0044b8,
    name: "login-email_login"
  }, {
    path: "/login/face_login",
    component: _f049e276,
    name: "login-face_login"
  }, {
    path: "/login/findemailpass",
    component: _280ae246,
    name: "login-findemailpass"
  }, {
    path: "/login/findphonepass",
    component: _1d45e218,
    name: "login-findphonepass"
  }, {
    path: "/login/phone_login",
    component: _810acfec,
    name: "login-phone_login"
  }, {
    path: "/money/allapply",
    component: _bb85bc7c,
    name: "money-allapply"
  }, {
    path: "/money/dealapply",
    component: _65d06c7d,
    name: "money-dealapply"
  }, {
    path: "/money/moneyapply",
    component: _3f939b7a,
    name: "money-moneyapply"
  }, {
    path: "/money/myapply",
    component: _d6dd0006,
    name: "money-myapply"
  }, {
    path: "/money/paysalary",
    component: _69dbe66d,
    name: "money-paysalary"
  }, {
    path: "/project/allproject",
    component: _0873ba06,
    name: "project-allproject"
  }, {
    path: "/project/allprojectdetail",
    component: _57537757,
    name: "project-allprojectdetail"
  }, {
    path: "/project/creatproject",
    component: _c9d216c4,
    name: "project-creatproject"
  }, {
    path: "/project/mypassproject",
    component: _0eb99c7e,
    name: "project-mypassproject"
  }, {
    path: "/project/mywaitproject",
    component: _3a4ce89a,
    name: "project-mywaitproject"
  }, {
    path: "/regist/email_regist",
    component: _5d840c2a,
    name: "regist-email_regist"
  }, {
    path: "/regist/face_regist",
    component: _2c399e77,
    name: "regist-face_regist"
  }, {
    path: "/regist/phone_regist",
    component: _1a542318,
    name: "regist-phone_regist"
  }, {
    path: "/user/avator",
    component: _b40515de,
    name: "user-avator"
  }, {
    path: "/user/basic_info",
    component: _032b9725,
    name: "user-basic_info"
  }, {
    path: "/user/changepass",
    component: _51375fc7,
    name: "user-changepass"
  }, {
    path: "/user/user_info",
    component: _ca34c2e8,
    name: "user-user_info"
  }, {
    path: "/work/dealgreatwork",
    component: _f670a7d4,
    name: "work-dealgreatwork"
  }, {
    path: "/work/dealnormalwork",
    component: _039269b0,
    name: "work-dealnormalwork"
  }, {
    path: "/work/managegreatwork",
    component: _651ac4bd,
    name: "work-managegreatwork"
  }, {
    path: "/work/managenormalwork",
    component: _2da265e9,
    name: "work-managenormalwork"
  }, {
    path: "/work/updateworkinfo",
    component: _4850b4d4,
    name: "work-updateworkinfo"
  }, {
    path: "/work/workinfo",
    component: _1801d88b,
    name: "work-workinfo"
  }, {
    path: "/work/changeworkinfo/:id?",
    component: _53ee2f0f,
    name: "work-changeworkinfo-id"
  }, {
    path: "/",
    component: _57f335a7,
    name: "index"
  }],

  fallback: false
}

export function createRouter () {
  return new Router(routerOptions)
}
