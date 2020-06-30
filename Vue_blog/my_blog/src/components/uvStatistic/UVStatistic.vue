<template>
  <div class="container ui grid one column">
    <v-chart :options="line" ref="echart" class="wide column chart center aligned"></v-chart>
  </div>
</template>

<script>
import ECharts from "vue-echarts";
import "echarts/lib/chart/line";
import "echarts/lib/component/tooltip";
import "echarts/lib/component/title";

import { findStatistic } from "network/uvStatisticAjax";
import eventHandle from "common/event";

export default {
  name: "UVStatistic",
  components: {
    "v-chart": ECharts
  },
  data() {
    return {
      recent: 7,
      line: {
        title: {
          show: true,
          text: "最近7天访客统计",
          subtext: "",
          textStyle: {
            color: "#19bcb5"
          },
          left: "center"
        },
        tooltip: {
          show: true,
          trigger: "axis"
        },
        xAxis: {
          type: "category",
          boundaryGap: false,
          name: "时间",
          data: []
        },
        yAxis: {
          name: "访问量",
          type: "value"
        },
        series: [
          {
            name: "访客数量",
            type: "line",
            showSymbol: true,
            symbol: "circle",
            symbolSize: 8,
            itemStyle: {
              normal: {
                color: "#19bcb5", //折点的颜色
                lineStyle: {
                  color: "#19bcb5" //折线的颜色
                }
              }
            },
            data: []
          }
        ]
      }
    };
  },
  activated() {
    this.getStatistic();
  },
  mounted() {
    eventHandle.addEvent(window, "resize", this.resizeEchart);
    this.getStatistic();
  },
  beforeDestroy() {
    eventHandle.removeEvent(window, "resize", this.resizeEchart);
  },
  methods: {
    getStatistic() {
      findStatistic(this.recent).then(res => {
        const xData = Object.keys(res.data.data.statisticMap);
        const yDate = Object.values(res.data.data.statisticMap);
        this.line.xAxis.data = xData;
        this.line.series[0].data = yDate;
        this.line.title.subtext = "历史总访问量：" + res.data.data.total;
      });
    },
    resizeEchart() {
      this.$refs.echart.resize();
    }
  }
};
</script>

<style scoped>
.container {
  padding: 0 !important;
  overflow-x: auto;
}
</style>