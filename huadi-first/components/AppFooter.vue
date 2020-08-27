<template>
  <!-- 公共底 -->
  <footer id="footer">
     <el-divider></el-divider>
    <section class="container" style="text-align:center;">

     <div class style="text-align:left;">
      <h4 >
          <span class="fsize18 f-fM c-999">友情链接：</span>
        </h4>

        <el-row :gutter="5">
  <el-col :span="2"><div class="grid-content bg-purple">  <el-link type="primary" href="https://www.ele.me/" target="_blank">饿了么</el-link></div></el-col>
  <el-col :span="2"><div class="grid-content bg-purple"> <el-link type="primary" href="https://www.baidu.com" target="_blank">百度一下</el-link></div></el-col>
  <el-col :span="2"><div class="grid-content bg-purple"><el-link type="primary" href="https://www.github.com" target="_blank">github</el-link></div></el-col>
  <el-col :span="2"><div class="grid-content bg-purple">      <el-link type="primary" href="https://www.csdn.net/" target="_blank">CSDN</el-link></div></el-col>
</el-row>
      </div>

        <el-divider></el-divider>   
      <div class="b-foot">
        <section class="fl col-7">
          <section class="mr20">
            <section class="b-f-link">
              <el-link type="primary" href="https://www.baidu.com" target="_blank">关于我们</el-link>|
              <el-link type="primary" href="https://www.baidu.com" target="_blank">联系我们</el-link>|
              <el-link type="primary" href="https://www.baidu.com" target="_blank">帮助中心</el-link>|
              <el-link type="primary" href="https://www.baidu.com" target="_blank">资源下载</el-link>|
              <span>服务热线：011-56253825(贵州) 0755-85293825(遵义)</span>
              <span>Email：1721071147@qq.com</span>
            </section>
            <section class="b-f-link mt10">
              <span>©2020页面版权归404天团所有 京ICP备9999号</span>
            </section>
          </section>
        </section>
        <aside class="fl col-3 tac mt15">
          <section class="gf-tx">
            <span>
               <el-link type="primary" href="https://open.weibo.com/" target="_blank">  <img src="~/assets/img/wb-icon.png" alt></el-link>
            
            </span>
            <span>
               <el-link type="primary" href="https://weixin.qq.com/" target="_blank"> <img src="~/assets/img/wx-icon.png" alt></el-link>
             
            </span>
          </section>
        </aside>
        <div class="clear"/>
        
      </div>
    </section>
  </footer>
  <!-- /公共底 -->
</template>
<script>
export default {
  
mounted(){
  document.querySelector('#footer').setAttribute('style', 'background-color:#E4E7ED')
  document.querySelector('body').setAttribute('style', 'background-color:#F2F6FC')
},
}
</script>

