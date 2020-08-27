<template>
  <div >
    <div id="myChart" :style="{width: '100%', height: '500px'}"></div>
  </div>
</template>
<script>
import departmentApi from "@/api/department";
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

      departmentApi.getdepartmentPeople().then((response) => {
        var list1 = response.data.list1;
        var list2 = response.data.list2;
        // 绘制图表
        myChart.setOption({
         title: { text: "部门人数柱状图" },
        tooltip: {},
        xAxis: {
          data: list1,
        },
        yAxis: {},
        series: [
          {
            name: "部门人数",
            type: "bar",
            data: list2,
          },
        ],

        });
      });
    },
  },
};
</script>