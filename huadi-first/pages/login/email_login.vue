<template>
  <div style="margin-left:30%">
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="ruleForm.email" style="width: 300px"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          type="password"
          v-model="ruleForm.password"
          autocomplete="off"
          style="width: 300px"
        ></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">立即登录</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
         <el-link href="/login/findemailpass" type="primary">忘记密码？</el-link>
      </el-form-item>
    </el-form>
    <!-- 更多注册方式 -->
    <div class="more-sign">
      <h6>社交帐号直接登录</h6>
      <ul>
        <li>
          <a id="weixin" class="weixin" href="http://localhost:8160/api/ucenter/wx/login">
            <img src="~/assets/img/wxicon.png" alt />
          </a>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
import loginApi from '~/api/login'
import cookie from 'js-cookie'
export default {
  data() {
    return {
      ruleForm: {
        email: "",
        password: "",
      },
      rules: {
        email: [
          {
            min: 9,
            max: 20,
            required: true,
            message: "请输入邮箱",
            trigger: "blur",
          },
          {
            pattern: /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/,
            message: "请输入正确的邮箱",
          },
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          loginApi.loginByEmail(this.ruleForm).then((response) => {
            // 提示发送成功
            this.$message.success(response.message);
             if(response.success==true){
                // 将jwt写入cookie
        cookie.set('jwt_token', response.data.token,  { expires: 1, path: '/' })
          // 跳转到网站的首页面
        window.location.href = '/'
        }
          });
        } else {
          alert("输入不合法!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>