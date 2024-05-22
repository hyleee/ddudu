import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

const REST_ARTICLE_API = "http://localhost:8080/article";

export const useArticleStore = defineStore("articleStore", () => {
  const article = ref({
    articleId: 0,
    userId: "",
    articleTitle: "",
    articleContent: "",
    articleCreated: "",
    category: ""
  });
  const errorMessage = ref('');

  const fetchArticleById = async (articleId) => {
    try {
      const response = await axios.get(`${REST_ARTICLE_API}/${articleId}`);
      article.value = response.data;
      console.log(response.data);
      errorMessage.value = '';
    } catch (error) {
      if (error.response && error.response.status === 404) {
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
    errorMessage,
    fetchArticleById,
  };
});
