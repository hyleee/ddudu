import { ref } from "vue";
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
      console.log("userId.vallue: " + userId.value);
      const response = await axios.get(
        `${REST_STATS_API}/weekly/${userId.value}`
      );
      weeklyStats.value = response.data;
    } catch (error) {
      console.error("Failed to fetch weekly stats:", error);
    }
  };

  return {
    weeklyStats,
    fetchWeeklyStats,
  };
});
