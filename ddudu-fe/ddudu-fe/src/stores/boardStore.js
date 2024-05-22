import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { useUserStore } from "./userStore";

const REST_BOARD_API = "http://localhost:8080/board";

export const useBoardStore = defineStore("boardStore", () => {
  const userStore = useUserStore();
  const articleList = ref([]);
  const article = ref(null);
  const errorMessage = ref('');

  const fetchArticlesByUserArea = async () => {
    try {
      const userArea = userStore.user.userArea;
      console.log("userId: " + userStore.user.userId);
      console.log("userArea: " + userStore.user.userArea);
      const response = await axios.get(`${REST_BOARD_API}/${userArea}`);
      articleList.value = response.data;
    } catch (error) {
      console.error("Error fetching articles by user area:", error);
    }
  };

  const fetchArticlesByUserAreaAndCategory = async (category) => {
    try {
      const userArea = userStore.user.userArea;
      const response = await axios.get(`${REST_BOARD_API}/${userArea}/${category}`);
      articleList.value = response.data;
    } catch (error) {
      console.error("Error fetching articles by user area and category:", error);
    }
  };

  const fetchArticle = async (articleId) => {
    try {
      const response = await axios.get(`${REST_BOARD_API}/${articleId}`);
      article.value = response.data;
      errorMessage.value = '';
    } catch (error) {
      if (error.response && error.response.status === 204) {
        errorMessage.value = '게시글을 찾을 수 없습니다.';
      } else {
        errorMessage.value = '게시글을 불러오는 중 오류가 발생했습니다.';
      }
      article.value = null;
      console.error("Error fetching article:", error);
    }
  };

  return {
    article,
    articleList,
    errorMessage,
    fetchArticlesByUserArea,
    fetchArticlesByUserAreaAndCategory,
    fetchArticle,
  };
});
