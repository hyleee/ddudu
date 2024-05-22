import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { useLoginStore } from "./loginStore";

const REST_STATS_API = "http://localhost:8080/stats";

export const useBodyInfoStore = defineStore("bodyInfoStore", () => {
  const weeklyStats = ref([]);
  const loginStore = useLoginStore();
  const userId = ref(loginStore.loginUser.userId);

  const fetchWeeklyStats = async () => {
    try {
      console.log("Fetching stats for userId: " + userId.value);
      const response = await axios.get(
        `${REST_STATS_API}/weekly/${userId.value}`
      );
      console.log("Fetched weeklyStats:", response.data);
      weeklyStats.value = response.data;
    } catch (error) {
      console.error("Failed to fetch weekly stats:", error);
    }
  };

  const chartData = computed(() => {
    return {
      labels: weeklyStats.value.map((stat) => stat.bodyPart),
      datasets: [
        {
          label: "Total Weight (kg)",
          data: weeklyStats.value.map((stat) => stat.totalWeight),
          backgroundColor: "rgba(255, 99, 132, 0.2)",
          borderColor: "rgba(255, 99, 132, 1)",
          borderWidth: 1,
        },
      ],
    };
  });

  const chartOptions = ref({
    indexAxis: "y",
    scales: {
      x: {
        beginAtZero: true,
      },
    },
  });

  return {
    weeklyStats,
    fetchWeeklyStats,
    userId,
    chartData,
    chartOptions,
  };
});
