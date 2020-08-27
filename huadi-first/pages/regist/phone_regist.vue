<template>
  <div style="margin-left:30%">
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"     
    >
      <el-form-item label="昵称" prop="nickname" >
        <el-input v-model="ruleForm.nickname" style="width: 300px"></el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="number">
        <el-input v-model="ruleForm.number" style="width: 300px"></el-input>
        <el-button type="info" @click="getCodeFun()">{{ codeText }}</el-button>
      </el-form-item>
      <el-form-item label="验证码" prop="code">
        <el-input v-model="ruleForm.code" style="width: 140px"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="ruleForm.password" autocomplete="off" style="width: 300px"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">立即注册</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>

     <!-- 更多注册方式 -->
      <div class="more-sign">
        <h6>社交帐号直接登录</h6>
        <ul>
          <li><a id="weixin" class="weixin" href="http://localhost:8160/api/ucenter/wx/login"> <img src="~/assets/img/wxicon.png" alt></a></li>
        </ul>
      </div>
  </div>
</template>
<script>

import registerApi from '~/api/regist'
export default {
  
  data() {
    return {
      click:false,
      second: 60, // 倒计时间
      sending:false,
      codeText: '获取验证码',
      ruleForm: {
        nickname: "",
        number:"",
        code:"",
        password:""
      },
      rules: {
        nickname: [
          { required: true, message: "请输入昵称", trigger: "blur" },
          { min: 3, max: 10, message: "长度在 3 到 10 个字符", trigger: "blur" },
        ],
        number: [
         { min: 11, max: 11,required: true, message: "请输入11位手机号码", trigger: "blur" },
          {
            pattern: /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/,
            message: "请输入正确的手机号码"
          }
        ],
        code: [
          { min: 4, max: 6,required: true, message: "请输入正确验证码", trigger: "blur" },
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
      },
    };
  },
  methods: {
 // 获取验证码
    getCodeFun() {
      if (this.sending) return // 如果已点击则退出，防止多次重复提交
      this.sending = true // 用户已点击
        // 倒计时
        this.timeDown()
          registerApi.sendPhone(this.ruleForm.number).then(response => {
        // 提示发送成功
        this.$message.success(response.message)
      })
    },

    // 倒计时
    timeDown() {
      this.codeText = this.second
      // 定义计时器
      const timer = setInterval(() => {
        this.codeText--
        if (this.codeText < 1) {
          clearInterval(timer)
          this.codeText = '获取验证码'
          this.sending = false
          this.second = 60
        }
        // console.log(new Date())
      }, 1000)
    },



    submitForm(formName) {

      this.$refs[formName].validate((valid) => {
        if (valid) {
      if(this.click) return
      this.click=true

          registerApi.registByPhone(this.ruleForm).then(response =>{
            this.click=false;

             // 提示发送成功
        this.$message.success(response.message)
         if(response.success==true){
          // 跳转到网站的首页面
             setTimeout(function () {
        window.location.href = '/login/phone_login' //1秒后刷新页面
      }, "1000");
       
        }
          })
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