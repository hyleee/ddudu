<template>
  <div class="container">
    <h2>Body Info</h2>
    <div v-if="weeklyStats && store.weeklyStats.length">
      <h3>Weekly Stats</h3>
      <BarChart />
      <ul>
        <li v-for="stat in weeklyStats" :key="stat.bodyPart">
          {{ stat.bodyPart }}: {{ stat.totalWeight }} kg
        </li>
      </ul>
    </div>
    <div v-else>
      <p>No data available</p>
    </div>
  </div>
</template>

<script setup>
import { onMounted, computed } from "vue";
import { useBodyInfoStore } from "@/stores/bodyInfoStore";
import BarChart from "@/components/analysis/BarChart.vue";

const store = useBodyInfoStore();

const weeklyStats = computed(() => store.weeklyStats);

console.log(store.chartData);
console.log(store.chartOptions);

onMounted(async () => {
  console.log("Fetching weekly stats...");
  await store.fetchWeeklyStats();
  console.log("onMounted:", store.weeklyStats.value);
});
</script>

<style scoped>
.container {
  margin: 0;
}

h2 {
  text-align: center;
}

div {
  margin: 20px;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  margin: 5px 0;
}
</style>
