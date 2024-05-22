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
    data.datasets[0].backgroundColor = "rgba(255, 223, 0, 0.7)"; // 개나리 색깔
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
  height: 40vh;
  width: 80vw;
  margin-left: 0;
  display: flex;
  justify-content: left;
}
</style>
