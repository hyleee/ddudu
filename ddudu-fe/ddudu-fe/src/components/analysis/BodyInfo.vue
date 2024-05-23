<template>
  <div class="container">
    <h2>주간 운동량 분석</h2>
    <div v-if="weeklyStats && store.weeklyStats.length">
      <BarChart />
      <ul class="stat-list">
        <li v-for="stat in weeklyStats" :key="stat.bodyPart" class="stat-item">
          <div class="stat-body-part-box">
            {{ translateBodyPart(stat.bodyPart) }}
          </div>
          <div class="stat-weight-box">
            <strong>{{ stat.totalWeight }} kg</strong>
          </div>
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

const translateBodyPart = (bodyPart) => {
  const translations = {
    chest: '가슴',
    back: '등',
    shoulders: '어깨',
    arms: '팔',
    legs: '다리',
    core: '코어',
    abs: '복근',
  };
  return translations[bodyPart] || bodyPart;
};

onMounted(async () => {
  console.log("Fetching weekly stats...");
  await store.fetchWeeklyStats();
  console.log("onMounted:", store.weeklyStats.value);
});
</script>

<style scoped>
.container {
  margin: 0;
  padding: 20px;
}

h2 {
  text-align: center;
}

h3 {
  margin-bottom: 10px; /* 그래프와 수치 정보 간의 공백 조정 */
}

li {
  margin-bottom: 10px;
}

.stat-list {
  list-style-type: none;
  padding: 0;
  margin: 0; /* 리스트 상단 마진 제거 */
}

.stat-item {
  display: flex;
  justify-content: space-between;
  margin: 5px 0; /* 각 항목 간의 간격을 줄임 */
}

.stat-body-part-box{
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px;
  /* background-color: #e0f7fa; 더 연한 하늘색 */
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.stat-weight-box {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px;
  background-color: #fff; /* 더 연한 하늘색 */
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.stat-body-part-box {
  flex: 4; /* 비율 4 */
  font-weight: bold;
  margin-right: 5px; /* 항목 간의 간격을 줄임 */
}

.stat-weight-box {
  flex: 6; /* 비율 6 */
  color: #333;
}
</style>
