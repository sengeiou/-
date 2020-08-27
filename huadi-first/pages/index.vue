<template>
<div>
   <el-carousel :interval="2000" type="card" height="300px">
    <el-carousel-item v-for="item in list" :key="item">
       <el-image style="height: 300px" :src="item"></el-image>
    </el-carousel-item>
  </el-carousel>

  <br>
 <div style="text-align:center;">
       <h1> <span >部门详情</span></h1>
    </div>

  <el-tabs  stretch >
    <el-tab-pane :label="de.name"  v-for="de in departmentlist" :key="de.id">
       <div style="text-align:center;">
     <p> <span>部门名称：{{de.name}}</span></p>
       <p> <span>部门人数：{{de.peoplenumber}}</span></p>
       <p>  <span>详细介绍：{{de.detail}}</span></p>
        <p>  <span>建立时间：{{de.gmtCreate}}</span></p>
    </div>

    </el-tab-pane>
  </el-tabs>

</div>
</template>
<script>

import userApi from '~/api/userinfo'
export default {
  
  data() {
    return {
      list:[
        "https://huadi-test.oss-cn-beijing.aliyuncs.com/u%3D2353457644%2C2584919985%26fm%3D26%26gp%3D0.jpg",
        "https://huadi-test.oss-cn-beijing.aliyuncs.com/u%3D2641160889%2C697502632%26fm%3D15%26gp%3D0.jpg",
        "https://huadi-test.oss-cn-beijing.aliyuncs.com/u%3D2874855224%2C2813156680%26fm%3D26%26gp%3D0.jpg",
        "https://huadi-test.oss-cn-beijing.aliyuncs.com/u%3D3120331574%2C4169260452%26fm%3D15%26gp%3D0.jpg",
        "https://huadi-test.oss-cn-beijing.aliyuncs.com/u%3D3795301812%2C679170305%26fm%3D26%26gp%3D0.jpg",
        "https://huadi-test.oss-cn-beijing.aliyuncs.com/%E4%B8%8B%E8%BD%BD%20%281%29.jpg",
        "https://huadi-test.oss-cn-beijing.aliyuncs.com/%E4%B8%8B%E8%BD%BD%20%282%29.jpg",
        "https://huadi-test.oss-cn-beijing.aliyuncs.com/%E4%B8%8B%E8%BD%BD.jpg"
      ],
      departmentlist:[]
    };
  },
    created() {
      this.getDepartment()
  },
  methods: {
    getDepartment(){
      userApi.getDepartment().then(response=>{
        this.departmentlist=response.data.list
      })
    },
  },
};
</script>
<style>
.el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }
  
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>