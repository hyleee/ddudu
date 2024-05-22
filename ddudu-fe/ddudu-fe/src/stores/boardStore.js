import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { useLoginStore } from "./loginStore";
import { useUserStore } from "./userStore";

const REST_BOARD_API = "http://localhost:8080/board";

export const useBoardStore = defineStore("boardStore", () => {
  const loginStore = useLoginStore();
  const userStore = useUserStore();
  const articleList = ref([]);
  const article = ref(null);
  const errorMessage = ref("");
  const noContentMessage = ref("");

  const fetchArticlesByUserArea = async () => {
    try {
      const userId = loginStore.loginUser.userId;
      await userStore.fetchUser(userId); // userStore를 사용하여 유저 정보를 가져옵니다.
      const userArea = userStore.user.userArea;
      console.log("userId: " + userId);
      console.log("userArea: " + userArea);
      const response = await axios.get(`${REST_BOARD_API}/${userArea}`);
      articleList.value = response.data;
    } catch (error) {
      console.error("Error fetching articles by user area:", error);
    }
  };

  const fetchArticlesByUserAreaAndCategory = async (category) => {
    try {
      const userId = loginStore.loginUser.userId;
      await userStore.fetchUser(userId); // userStore를 사용하여 유저 정보를 가져옵니다.
      const userArea = userStore.user.userArea;
      console.log("userId: " + userId);
      console.log("userArea: " + userArea);
      console.log("category: " + category);
      const response = await axios.get(
        `${REST_BOARD_API}/${userArea}/${category}`
      );
      if (response.status === 204) {
        noContentMessage.value = "우리 동네에는 해당 카테고리 글이 없어요ㅠㅠ";
        articleList.value = [];
      } else {
        noContentMessage.value = "";
        articleList.value = response.data;
      }
    } catch (error) {
      if (error.response && error.response.status === 404) {
        noContentMessage.value = "해당 지역 및 카테고리에 게시글이 없습니다.";
      } else {
        console.error(
          "Error fetching articles by user area and category:",
          error
        );
        noContentMessage.value = "게시글을 불러오는 중 오류가 발생했습니다.";
      }
      articleList.value = [];
    }
  };

  const fetchArticle = async (articleId) => {
    try {
      const response = await axios.get(`${REST_BOARD_API}/${articleId}`);
      article.value = response.data;
      errorMessage.value = "";
    } catch (error) {
      if (error.response && error.response.status === 204) {
        errorMessage.value = "게시글을 찾을 수 없습니다.";
      } else {
        errorMessage.value = "게시글을 불러오는 중 오류가 발생했습니다.";
      }
      article.value = null;
      console.error("Error fetching article:", error);
    }
  };

  return {
    article,
    articleList,
    errorMessage,
    noContentMessage,
    fetchArticlesByUserArea,
    fetchArticlesByUserAreaAndCategory,
    fetchArticle,
  };
});