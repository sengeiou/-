<template>
  <div style="text-align: center">
    <h3>点击图片更换</h3>
<el-upload
  class="avatar-uploader"
  action="http://192.168.110.149:7002/manageoss/file/upload/huadi-test"
  :show-file-list="false"
  :on-success="handleAvatarSuccess"
  :before-upload="beforeAvatarUpload">
  <img v-if="userInfo" :src="userInfo.avatar"  class="avatar">
  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
</el-upload>
<el-button type="primary" @click="save()" plain >保存</el-button>
  </div>
</template>
 <script>
import cookie from "js-cookie";
import loginApi from "~/api/login";
export default {
  data() {
    return {
      userInfo: null
    };
  },
    // 登录成功后获取用户信息
    // 页面渲染之前执行：尚未有window对象
    created() {
      this.getUserInfo();
    },
  methods: {
    save(){
      loginApi.saveAvator(this.userInfo).then(response=>{
        //生成了新的token,包含新头像信息
        this.$message.success(response.message);
         // 将jwt写入cookie
        cookie.set('jwt_token', response.data.token,  { expires: 1, path: '/' })
         // 跳转到网站的首页面
       setTimeout(function () {
              window.location.href = "/";//1秒后刷新页面
            }, "1000");
      })
    },
     handleAvatarSuccess(res, file) {
        this.userInfo.avatar=res.data.url;
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      },
  
    getUserInfo() {
      // 如果cookie中的token值不存在，则无需获取用户信息
      if (!cookie.get("jwt_token")) {
        return;
      }

      // 如果token村子，则携带token的值向服务器发起请求
      loginApi.getLoginInfo().then((response) => {
        // 渲染页面
        this.userInfo = response.data.userInfo;
      });
    }
  }
};
</script>
<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>