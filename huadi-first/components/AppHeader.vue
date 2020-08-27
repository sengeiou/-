<template>
  <!-- 公共头 -->
  <header id="header">
    <section class="container">
      <el-menu
        :default-active="activeIndex2"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        background-color="#F0F0F0"
        text-color="#3A006F"
        active-text-color="#3A006F"
      >
        <el-submenu index="1" v-if="userInfo">
          <template slot="title">
            <img :src="userInfo.avatar" width="30" height="30" class="vam picImg" alt />
            <span id="userName" class="vam disIb">{{ userInfo.nickname }}</span>
          </template>
          <el-menu-item index="5-2">
            <el-link href="/user/avator" type="primary">修改头像</el-link>
          </el-menu-item>
           <el-menu-item index="5-5">
            <el-link href="/user/changepass" type="primary">设置密码</el-link>
          </el-menu-item>
          <el-menu-item index="5-3">
            <el-link href="/user/basic_info" type="primary">修改基本信息</el-link>
          </el-menu-item>
          <el-menu-item index="5-4">
            <el-link href="/user/user_info" type="primary">账号绑定</el-link>
          </el-menu-item>
         
          <el-menu-item index="5-1">
            <a href="javascript:void(0)" title="退出" class="a" @click="logout()">退出</a>
          </el-menu-item>
        </el-submenu>

        <el-submenu index="5" v-if="!userInfo">
          <template slot="title">登录</template>
          <el-menu-item index="5-1">
            <el-link href="/login/phone_login" type="primary">手机号登录</el-link>
          </el-menu-item>
          <el-menu-item index="5-2">
            <el-link href="/login/email_login" type="primary">邮箱登录</el-link>
          </el-menu-item>
          <el-menu-item index="5-3">
            <el-link href="/login/face_login" type="primary">人脸登录</el-link>
          </el-menu-item>
            <el-menu-item index="5-4">
            <el-link href="http://localhost:8160/api/ucenter/wx/login" type="primary">微信扫码登录</el-link>
          </el-menu-item>
        </el-submenu>

        <el-submenu index="2" v-if="!userInfo">
          <template slot="title">注册</template>
          <el-menu-item index="2-1">
            <el-link href="/regist/phone_regist" type="primary">手机号注册</el-link>
          </el-menu-item>
          <el-menu-item index="2-2">
            <el-link href="/regist/email_regist" type="primary">邮箱注册</el-link>
          </el-menu-item>
          <el-menu-item index="2-3">
            <el-link href="/regist/face_regist" type="primary">人脸注册</el-link>
          </el-menu-item>
        </el-submenu>

        <el-submenu index="3" v-if="userInfo">
          <template slot="title">我的工作台</template>

          <el-submenu index="2-4">
            <template slot="title">我的工作信息</template>
            <el-menu-item index="2-4-1">
              <el-link href="/work/updateworkinfo" type="primary">申请修改工作信息</el-link>
            </el-menu-item>
            <el-menu-item index="2-4-2">
              <el-link href="/work/workinfo" type="primary">查看工作信息</el-link>
            </el-menu-item>
          </el-submenu>

                    <el-submenu index="2-5">
            <template slot="title">我的资金信息</template>
            <el-menu-item index="2-4-1">
              <el-link href="/money/moneyapply" type="primary">申请资金报销</el-link>
            </el-menu-item>
            <el-menu-item index="2-4-2">
              <el-link href="/money/myapply" type="primary">我的申报记录</el-link>
            </el-menu-item>
          </el-submenu>

                             <el-submenu index="2-6">
            <template slot="title">我的项目信息</template>
            <el-menu-item index="2-4-1">
              <el-link href="/project/mywaitproject" type="primary">待完成项目</el-link>
            </el-menu-item>
            <el-menu-item index="2-4-2">
              <el-link href="/project/mypassproject" type="primary">我完成的项目</el-link>
            </el-menu-item>
          </el-submenu>

        </el-submenu>



         <el-submenu index="2" v-if="userInfo">
          <template slot="title">我的特权</template>
          <el-menu-item  v-for="power in userPower" :key="power.id">
          <el-link  :href="power.path" type="primary">{{power.name}}</el-link>
          </el-menu-item>
          </el-submenu>
         <!-- </el-submenu> -->
        
    
        <el-menu-item index="6">
          <el-link href="/" type="primary">首页</el-link>
        </el-menu-item>


      </el-menu>
    </section>
       <el-divider></el-divider>
  </header>
  <!-- /公共头 -->
</template>

 <script>
import cookie from "js-cookie";
import loginApi from "~/api/login";
import powerApi from "~/api/power";
export default {
  data() {
    return {
      activeIndex2: "6",
      userInfo: null,
      userPower: [],
    };
  },
  // 登录成功后获取用户信息
  // 页面渲染之前执行：尚未有window对象
  created() {
    this.getUserInfo();
  },

  // 页面渲染之后执行：可以有window对象
  mounted() {
    const token = this.$route.query.token;
    if (token) {
      // 将jwt写入cookie
      cookie.set("jwt_token", token, { expires: 1, path: "/" });
      window.location.href = "/";
    }
  },

  methods: {
    handleSelect(key, keyPath) {
    },
    getUserInfo() {
      // 如果cookie中的token值不存在，则无需获取用户信息
      if (!cookie.get("jwt_token")) {
        return;
      }
      // 如果token村子，则携带token的值向服务器发起请求
      loginApi.getLoginInfo().then((response) => {
        this.userInfo = response.data.userInfo;
      });

      powerApi.getUserPower().then((response) => {
        this.userPower = response.data.list;
      });
    },

    logout() {
      // 清除cookie
      cookie.set("jwt_token", "", { expires: 1, path: "/" });
      // 跳转页面
      window.location.href = "/";
    },
  },
};
</script>
