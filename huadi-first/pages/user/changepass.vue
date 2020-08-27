<template>
  <div>

 <div style="margin-left:30%">
    <el-form
      :model="ruleForm"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
    >
       <el-form-item label="原密码" prop="code">
        <el-input
          type="password"
          v-model="ruleForm.code"
          autocomplete="off"
          style="width: 300px"
        ></el-input>
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
      </el-form-item>
    </el-form>
  </div>

  </div>
</template>
<script>
import userApi from "~/api/userlock";
import registerApi from "~/api/regist";
import cookie from 'js-cookie'
export default {
  data() {
    return {
      user: {},
      ruleForm: {
        code:"",
       // spassword: "",
        password: "",
        repassword:""
      }
    };
  },
  created() {
    this.getUserInfo();
  },
  methods: {
    getUserInfo() {
      userApi.isLock().then((response) => {
          if(!response.data.user.email&&!response.data.user.number){
         this.$confirm('请先绑定邮箱或者手机号后再设置密码', '提示', {
          confirmButtonText: '确定',
        }).then(() => {
            window.location.href = "/";
        })
          }else{
        this.user = response.data.user;
          }
      });
 

    },
    submitForm(formName) {
        if(this.ruleForm.repassword!=this.ruleForm.password){
                alert("两次密码不一致");
                return 
            }
    registerApi.changepass(this.ruleForm).then((response) => {
            // 提示发送成功
            this.$message.success(response.message);
            console.log(response)
            if (response.success == true) {
                 // 清除cookie
      cookie.set("jwt_token", "", { expires: 1, path: "/" });
              // 跳转到网站的首页面
              setTimeout(function () {
                window.location.href = "/"; //1秒后刷新页面
              }, "1000");
            }
          });
    },
  },
};
</script>