import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { useLoginStore } from "@/stores/loginStore";

const REST_ARTICLE_API = "http://localhost:8080/article";

export const useArticleStore = defineStore("articleStore", () => {
  const loginStore = useLoginStore();

  const article = ref({
    articleId: 0,
    userId: "",
    articleTitle: "",
    articleContent: "",
    articleCreated: "",
    category: "",
  });
  const errorMessage = ref("");

  const fetchArticleById = async (articleId) => {
    console.log("articleId: " + articleId);
    try {
      const response = await axios.get(`${REST_ARTICLE_API}/${articleId}`);
      article.value = response.data;
      console.log("article.value: " + response.data);
      errorMessage.value = "";
    } catch (error) {
      if (error.response && error.response.status === 404) {
        errorMessage.value = "게시글을 찾을 수 없습니다.";
      } else {
        errorMessage.value = "게시글을 불러오는 중 오류가 발생했습니다.";
      }
      article.value = null;
      console.error("Error fetching article:", error);
    }
  };

  const createArticle = async (articleData) => {
    articleData.userId = loginStore.loginUser.userId;
    try {
      const response = await axios.post(REST_ARTICLE_API, articleData);
      if (response.status === 201) {
        errorMessage.value = "";
        return response.data;
      }
    } catch (error) {
      errorMessage.value = "게시글 생성 중 오류가 발생했습니다.";
      console.error("Error creating article:", error);
    }
  };

  const updateArticle = async (articleId, articleData) => {
    try {
      const response = await axios.put(
        `${REST_ARTICLE_API}/${articleId}`,
        articleData
      );
      if (response.status === 200) {
        errorMessage.value = "";
        return response.data;
      }
    } catch (error) {
      errorMessage.value = "게시글 수정 중 오류가 발생했습니다.";
      console.error("Error updating article:", error);
    }
  };

  const deleteArticle = async (articleId) => {
    try {
      const response = await axios.delete(`${REST_ARTICLE_API}/${articleId}`);
      if (response.status === 200) {
        errorMessage.value = "";
        return response.data;
      }
    } catch (error) {
      errorMessage.value = "게시글 삭제 중 오류가 발생했습니다.";
      console.error("Error deleting article:", error);
    }
  };

  return {
    article,
    errorMessage,
    fetchArticleById,
    createArticle,
    updateArticle,
    deleteArticle,
  };
});
