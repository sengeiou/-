<template>
<div class="camera_outer" style="margin-left:30%">
    <video id="videoCamera" :width="videoWidth" :height="videoHeight" autoplay style="float:left; display:inline"></video>
    <canvas style="display:none;" id="canvasCamera" :width="videoWidth" :height="videoHeight"></canvas>
    <div v-if="imgSrc" class="img_bg_camera" >
      <p>效果预览:</p>
      <img :src="imgSrc" alt class="tx_img" />
    </div>
    <div class="button" >
      <el-button @click="getCompetence()">打开摄像头</el-button>
      <el-button @click="stopNavigator()">关闭摄像头</el-button>
      <el-button @click="setImage()">点击登录</el-button>
    </div>
      <div>

          <!-- 更多注册方式 -->
      <div class="more-sign">
        <h6>社交帐号直接登录</h6>
        <ul>
          <li><a id="weixin" class="weixin" href="http://localhost:8160/api/ucenter/wx/login"> <img src="~/assets/img/wxicon.png" alt></a></li>
        </ul>
      </div>
        </div>
    </div>
    
</template>
<script>
import axios from 'axios'
import cookie from 'js-cookie'
export default {
   data() {
    return {
      videoWidth: 250,
      videoHeight: 350,
      imgSrc: "",
      thisCancas: null,
      thisContext: null,
      thisVideo: null,
      openVideo:false
    };
  },
  mounted(){
    //this.getCompetence()//进入页面就调用摄像头
  },
  methods: {
    // 调用权限（打开摄像头功能）
    getCompetence() {
      var _this = this;
      _this.thisCancas = document.getElementById("canvasCamera");
      _this.thisContext = this.thisCancas.getContext("2d");
      _this.thisVideo = document.getElementById("videoCamera");
      _this.thisVideo.style.display = 'block';
      // 获取媒体属性，旧版本浏览器可能不支持mediaDevices，我们首先设置一个空对象
      if (navigator.mediaDevices === undefined) {
        navigator.mediaDevices = {};
      }
      // 一些浏览器实现了部分mediaDevices，我们不能只分配一个对象
      // 使用getUserMedia，因为它会覆盖现有的属性。
      // 这里，如果缺少getUserMedia属性，就添加它。
      if (navigator.mediaDevices.getUserMedia === undefined) {
        navigator.mediaDevices.getUserMedia = function(constraints) {
          // 首先获取现存的getUserMedia(如果存在)
          var getUserMedia =
            navigator.webkitGetUserMedia ||
            navigator.mozGetUserMedia ||
            navigator.getUserMedia;
          // 有些浏览器不支持，会返回错误信息
          // 保持接口一致
          if (!getUserMedia) {//不存在则报错
            return Promise.reject(
              new Error("getUserMedia is not implemented in this browser")
            );
          }
          // 否则，使用Promise将调用包装到旧的navigator.getUserMedia
          return new Promise(function(resolve, reject) {
            getUserMedia.call(navigator, constraints, resolve, reject);
          });
        };
      }
      var constraints = {
        audio: false,
        video: {
          width: this.videoWidth,
          height: this.videoHeight,
          transform: "scaleX(-1)"
        }
      };
      navigator.mediaDevices
        .getUserMedia(constraints)
        .then(function(stream) {
          // 旧的浏览器可能没有srcObject
          if ("srcObject" in _this.thisVideo) {
            _this.thisVideo.srcObject = stream;
          } else {
            // 避免在新的浏览器中使用它，因为它正在被弃用。
            _this.thisVideo.src = window.URL.createObjectURL(stream);
          }
          _this.thisVideo.onloadedmetadata = function(e) {
            _this.thisVideo.play();
          };
        })
        .catch(err => {
          console.log(err);
        });
    },
    //  绘制图片（拍照功能）
	setImage() {
      var _this = this;
      // canvas画图
  if(_this.thisContext==null){
    // 提示
        this.$message.error("请先打开摄像头");
        return 
  }

      _this.thisContext.drawImage(
        _this.thisVideo,
        0,
        0,
        _this.videoWidth,
        _this.videoHeight-35
      );
      // 获取图片base64链接
      var image = this.thisCancas.toDataURL("image/png");
      _this.imgSrc = image;//赋值并预览图片

      var file=this.dataURLtoFile(image,"testfile.png");//这里一定要加文件类型，不然上传时截取不到文件后缀
    //手动创建一个post文件上传请求
    const param = new FormData();
    param.append("file",file);
     const config = {
      headers: { "Content-Type": "multipart/form-data"}
    };
     
    axios.post(//"http://192.168.110.149:7002/userservice/user/faceLogin"
    "http://localhost:7002/userservice/user/faceLogin"
    , param, config).then(res => {
     // 提示发送成功
        this.$message.success(res.data.message);
        if(res.data.success==true){
           // 将jwt写入cookie
        cookie.set('jwt_token', res.data.data.token,  { expires: 1, path: '/' })
        window.location.href = '/'
        }
    });

    },
    // 关闭摄像头
    stopNavigator() {
      this.thisVideo.srcObject.getTracks()[0].stop();
    },
    // base64转文件，此处没用到
    dataURLtoFile(dataurl, filename) {
      var arr = dataurl.split(",");
      var mime = arr[0].match(/:(.*?);/)[1];
      var bstr = atob(arr[1]);
      var n = bstr.length;
      var u8arr = new Uint8Array(n);
      while (n--) {
        u8arr[n] = bstr.charCodeAt(n);
      }
      return new File([u8arr], filename, { type: mime });
    }
  }
};
</script>