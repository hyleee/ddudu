<template>
  <div>
    <h2>Body Info</h2>
    <div v-if="weeklyStats && weeklyStats.length">
      <h3>Weekly Stats</h3>
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
import { onMounted } from "vue";
import { useBodyInfoStore } from "@/stores/bodyInfoStore";

const store = useBodyInfoStore();

onMounted(() => {
  store.fetchWeeklyStats();
});

const weeklyStats = store.weeklyStats;
</script>

<style scoped>
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
