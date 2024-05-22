import { ref } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';

const REST_RANK_API = "http://localhost:8080/rank";

export const useRankStore = defineStore('rank', () => {
  const rankList = ref([]);
  const errorMessage = ref(null);

  const fetchRank = async (bodyPart) => {
    try {
      const response = await axios.get(`${REST_RANK_API}/${bodyPart}`);
      rankList.value = response.data;
      console.log("Fetched rank data:", response.data); // Fetch된 데이터 로그 확인
    } catch (error) {
      errorMessage.value = "Failed to fetch rank list";
      console.error("Error fetching rank list:", error);
    }
  };

  return {
    rankList,
    errorMessage,
    fetchRank,
  };
});
