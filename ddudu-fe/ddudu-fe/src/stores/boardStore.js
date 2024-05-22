import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { useLoginStore } from "./loginStore";

const REST_STATS_API = "http://localhost:8080/board";

export const useBoardStore = defineStore("boardStore", () => {

  const articleList = ref([]);
  const article = ref({
    articleId: "",
    userId: "",
    articleTitle: "",
    articleContent: "",
    articleCreated: "",
    category: "",
  });



  return {
  };
});
