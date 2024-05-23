<template>
  <div class="graph" v-if="chartData && chartOptions">
    <Bar :data="chartData" :options="chartOptions" />
  </div>
</template>

<script setup>
import { Bar } from "vue-chartjs";
import { computed, watchEffect } from "vue";
import { useBodyInfoStore } from "@/stores/bodyInfoStore";
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
} from "chart.js";

ChartJS.register(
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale
);

const store = useBodyInfoStore();

const chartData = computed(() => {
  const data = store.chartData;
  if (data && data.datasets && data.datasets.length > 0) {
    const gradient = (ctx) => {
      const gradient = ctx.createLinearGradient(0, 0, 0, 400);
      gradient.addColorStop(0, '#59D5E0'); // 시작 색깔
      gradient.addColorStop(1, '#F5DD61'); // 끝 색깔
      return gradient;
    };

    data.datasets[0].backgroundColor = (context) => {
      const chart = context.chart;
      const { ctx, chartArea } = chart;

      if (!chartArea) {
        return null;
      }
      return gradient(ctx);
    };
    data.datasets[0].borderColor = 'rgba(0, 0, 0, 0)'; // 테두리 색상 제거
  }
  return data;
});

const chartOptions = computed(() => {
  const options = store.chartOptions;
  if (options && options.scales && options.scales.y) {
    options.scales.y.ticks = {
      ...options.scales.y.ticks,
      padding: 1, // Y축 레이블 줄간 간격 조정
      font: {
        size: 16, // Y축 레이블 글자 크기 설정
      },
    };
  }
  // 안전하게 접근하여 옵션 정의
  options.layout = {
    padding: {
      top: 5,
      bottom: 5,
      left: 10,
      right: 10,
    },
  };
  if (!options.plugins) {
    options.plugins = {};
  }
  options.plugins.legend = {
    display: true,
    position: 'top',
    labels: {
      boxWidth: 10,
      padding: 10,
    }
  };
  return options;
});

watchEffect(() => {
  console.log("chartData:", chartData.value);
  console.log("chartOptions:", chartOptions.value);
});
</script>

<style scoped>
.graph {
  position: relative;
  height: 25vh;
  width: 80vw;
  margin-left: 0;
  display: flex;
  justify-content: left;
}
</style>
