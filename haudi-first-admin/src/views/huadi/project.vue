<template>
  <div >
    <div id="myChart" :style="{width: '100%', height: '500px'}"></div>
  </div>
</template>
<script>
import projectApi from "@/api/project";
export default {
  data() {
    return {
    };
  },
  mounted() {
    this.drawLine();
  },
  methods: {
    drawLine() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById("myChart"));

      projectApi.getBarSalary().then((response) => {
        var list = response.data.list;
        // 绘制图表
        myChart.setOption({
          legend: {},
          tooltip: {},
          dataset: {
            source: list,
          },
          xAxis: [{ type: "category", gridIndex: 0 }],
          yAxis: [{ gridIndex: 0 }],
          grid: [{ bottom: "45%" }, { top: "45%" }],
          series: [
            // These series are in the first grid.
            { type: "bar", seriesLayoutBy: "row" },
            { type: "bar", seriesLayoutBy: "row" },
          ],
        });
      });
    },
  },
};
</script>