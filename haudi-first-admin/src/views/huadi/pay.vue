<template>
  <div style="margin-left:200px;margin-top:50px">
    <el-form :inline="true" :model="query" class="demo-form-inline">
      <el-form-item label="时间范围">
        <el-date-picker v-model="query.startTime" type="datetime" placeholder="选择开始时间"></el-date-picker>
      </el-form-item>
      <el-date-picker v-model="query.endTime" type="datetime" placeholder="选择结束时间"></el-date-picker>
      <el-form-item>
        <!-- 这里要带括号 -->
        <el-button type="primary" @click="drawLine()">查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="default" @click="clear">清空</el-button>
      </el-form-item>
    </el-form>

    <div id="myChart" :style="{width: '100%', height: '500px'}"></div>
  </div>
</template>
<script>
import salaryApi from "@/api/salary";
export default {
  data() {
    return {
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
      // 绘制图表
      salaryApi.getSalaryType(this.query).then((response) => {
        var list = response.data.list;

        myChart.setOption({
          title: { text: "资金申请类型占比" },
          series: [
            {
              name: "申请类型",
              type: "pie",
              radius: "55%",
              roseType: "angle",
              data: list,
            },
          ],
        });
      });
    },
    clear() {
      this.query = {};
      this.getInfo();
    },
  },
};
</script>