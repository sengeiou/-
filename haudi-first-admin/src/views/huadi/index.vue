<template>
  <div style="margin-left:100px">
    <div id="myChart" :style="{width: '100%', height: '300px'}"></div>
 <div id="myChart3" :style="{width: '100%', height: '300px'}"></div>
    <br>
    <br>
<el-form :inline="true" :model="query" class="demo-form-inline">
    <el-form-item label="时间范围">
      <el-date-picker v-model="query.startTime" type="datetime" placeholder="选择开始时间"></el-date-picker>
    </el-form-item>
    <el-date-picker v-model="query.endTime" type="datetime" placeholder="选择结束时间"></el-date-picker>
    <el-form-item>
      <!-- 这里要带括号 -->
      <el-button type="primary" @click="getInfo()">查询</el-button>
    </el-form-item>
    <el-form-item>
      <el-button type="default" @click="clear">清空</el-button>
    </el-form-item>

        </el-form>
    <div id="myChart2" :style="{width: '100%', height: '300px'}"></div>
  </div>
</template>
<script>
import salaryApi from "@/api/salary";
export default {
  data() {
    return {
      numlist: [],
      namelist: [],
      query: {
        startTime: "",
        endTime: "",
      },
    };
  },
  mounted() {
     
 this.drawLine();
  },
  methods: {
    drawLine() {       
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById("myChart"));
       let myChart3 = this.$echarts.init(document.getElementById("myChart3"));

        salaryApi.getBarSalary().then((response) => {
        var namedata = response.data.namelist;
       var numdata = response.data.numlist;
       var list=response.data.list;
             // 绘制图表
      myChart.setOption({
        title: { text: "工资分布图" },
        tooltip: {},
        xAxis: {
          data: namedata,
        },
        yAxis: {},
        series: [
          {
            name: "工资所占人数",
            type: "bar",
            data: numdata,
          },
        ],
      });

         // 绘制图表
      myChart3.setOption({
          xAxis: {},
    yAxis: {},
    series: [{
        symbolSize: 10,
        data: list,
        type: 'scatter'
    }]
      });
      });
    this.getInfo();
    },
    getInfo() {
      salaryApi.getBarSalaryByYear(this.query).then((response) => {
        this.namelist = response.data.namelist;
        this.numlist = response.data.numlist;
      });
       // 基于准备好的dom，初始化echarts实例
      let myChart2 = this.$echarts.init(document.getElementById("myChart2")); 
    
             // 绘制图表
      myChart2.setOption({
        title: { text: "工资发放图" },
        tooltip: {},
        xAxis: {
          data: this.namelist,
        },
        yAxis: {},
        series: [
          {
            name: "工资所占人数",
            type: "bar",
            data: this.numlist,
          },
        ],
      });
    },

    clear() {
      this.query = {};
      this.getInfo();
    },
  },
};
</script>