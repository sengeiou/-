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
        <el-button type="info" @click="getCodeFun()">{{ codeText }}</el-button>
      </el-form-item>
      <el-form-item label="验证码" prop="code">
        <el-input v-model="ruleForm.code" style="width: 140px"></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="password">
        <el-input
          type="password"
          v-model="ruleForm.password"
          autocomplete="off"
          style="width: 300px"
        ></el-input>
      </el-form-item>

            <el-form-item label="确认密码" prop="repassword">
        <el-input
          type="password"
          v-model="ruleForm.repassword"
          autocomplete="off"
          style="width: 300px"
        ></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>


  </div>
</template>
<script>
import registerApi from "~/api/regist";
export default {
  data() {
    return {
       click:false,
      second: 60, // 倒计时间
      sending: false,
      codeText: "获取验证码",
      ruleForm: {
        nickname: "",
        email: "",
        code: "",
        password: "",
        repassword: ""
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
        code: [
          {
            min: 4,
            max: 6,
            required: true,
            message: "请输入正确验证码",
            trigger: "blur",
          },
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
        repassword: [{ required: true, message: "请输入密码", trigger: "blur" }],
      },
    };
  },
  methods: {
    // 获取验证码
    getCodeFun() {
      if (this.sending) return; // 如果已点击则退出，防止多次重复提交
      this.sending = true; // 用户已点击
      // 倒计时
      this.timeDown();
      registerApi.sendEmail(this.ruleForm.email).then((response) => {
        // 提示发送成功
        this.$message.success(response.message);
      });
    },

    // 倒计时
    timeDown() {
      this.codeText = this.second;
      // 定义计时器
      const timer = setInterval(() => {
        this.codeText--;
        if (this.codeText < 1) {
          clearInterval(timer);
          this.codeText = "获取验证码";
          this.sending = false;
          this.second = 60;
        }
        // console.log(new Date())
      }, 1000);
    },

    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
            if(this.ruleForm.repassword!=this.ruleForm.password){
                alert("两次密码不一致");
                return 
            }
           if(this.click) return
      this.click=true
          registerApi.changepassByEmail(this.ruleForm).then((response) => {
            this.click=false;
            // 提示发送成功
            this.$message.success(response.message);
             if(response.success==true){
          // 跳转到网站的首页面
          setTimeout(function () {
      window.location.href = '/login/email_login'//1秒后刷新页面
      }, "1000");
        
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