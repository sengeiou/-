<template>
  <div>
    <div style="margin-left:30%">
    <el-form
      :model="ruleForm2"
      :rules="rules"
      ref="ruleForm2"
      label-width="100px"
      class="demo-ruleForm"
      v-if="!user.email"
    >
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="ruleForm2.email" style="width: 300px"></el-input>
        <el-button type="info" @click="getCodeFun2()">{{ codeText }}</el-button>
      </el-form-item>
      <el-form-item label="验证码" prop="code">
        <el-input v-model="ruleForm2.code" style="width: 140px"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm2('ruleForm2')">立即绑定</el-button>
      </el-form-item>
    </el-form>
    </div>

 <div style="margin-left:30%">
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
      v-if="!user.number"
    >
      <el-form-item label="手机号" prop="number">
        <el-input v-model="ruleForm.number" style="width: 300px"></el-input>
        <el-button type="info" @click="getCodeFun()">{{ codeText }}</el-button>
      </el-form-item>
      <el-form-item label="验证码" prop="code">
        <el-input v-model="ruleForm.code" style="width: 140px"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">立即绑定</el-button>
      </el-form-item>
    </el-form>
  </div>

  </div>
</template>
<script>
import userApi from "~/api/userlock";
import registerApi from "~/api/regist";
export default {
  data() {
    return {
      user: {},
      second: 60, // 倒计时间
      sending: false,
      codeText: "获取验证码",
      ruleForm: {
        code: "",
        number: "",
      },
      ruleForm2: {
        email: "",
        code: "",
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
        number: [
          {
            min: 11,
            max: 11,
            required: true,
            message: "请输入11位手机号码",
            trigger: "blur",
          },
          {
            pattern: /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/,
            message: "请输入正确的手机号码",
          },
        ],
      },
    };
  },
  created() {
    this.getUserInfo();
  },
  methods: {
    getUserInfo() {
      userApi.isLock().then((response) => {
          if(response.data.user.email&&response.data.user.number){
         this.$confirm('您已经绑定了手机号和邮箱，无需绑定', '提示', {
          confirmButtonText: '确定',
        }).then(() => {
            window.location.href = "/";
        })
          }else{
        this.user = response.data.user;
          }
      });
 

    },
    // 获取验证码
    getCodeFun2() {
      if (this.sending) return; // 如果已点击则退出，防止多次重复提交
      this.sending = true; // 用户已点击
      // 倒计时
      this.timeDown();
      registerApi.sendEmail(this.ruleForm2.email).then((response) => {
        // 提示发送成功
        this.$message.success(response.message);
      });
    },
    getCodeFun() {
      if (this.sending) return; // 如果已点击则退出，防止多次重复提交
      this.sending = true; // 用户已点击
      // 倒计时
      this.timeDown();
      registerApi.sendPhone(this.ruleForm.number).then((response) => {
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

    submitForm2(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          userApi.lockByEmail(this.ruleForm2).then((response) => {
            // 提示发送成功
            this.$message.success(response.message);
            if (response.success == true) {
              setTimeout(function () {
                window.location.href = "/user/user_info"; //1秒后刷新页面
              }, "1000");
            }
          });
        } else {
          alert("输入不合法!");
          return false;
        }
      });
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          userApi.lockByPhone(this.ruleForm).then((response) => {
            // 提示发送成功
            this.$message.success(response.message);
            if (response.success == true) {
              // 跳转到网站的首页面
              setTimeout(function () {
                window.location.href = "/user/user_info"; //1秒后刷新页面
              }, "1000");
            }
          });
        } else {
          alert("输入不合法!");
          return false;
        }
      });
    },
  },
};
</script>