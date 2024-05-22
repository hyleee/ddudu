import { ref } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';

const REST_RANK_API = "http://localhost:8080/rank";

export const useRankStore = defineStore('rank', () => {
  const rankList = ref([]);
  const errorMessage = ref(null);

  const fetchRank = async (bodyPart) => {
    try {
      const res = await axios.get(`${REST_RANK_API}/${bodyPart}`);
      rankList.value = res.data;
      errorMessage.value = null;
    } catch (error) {
      errorMessage.value = `Error fetching ${bodyPart} rank list: ${error.message}`;
      console.error(`Error fetching ${bodyPart} rank list:`, error);
    }
  };

  return {
    rankList,
    errorMessage,
    fetchRank,
  };
});
