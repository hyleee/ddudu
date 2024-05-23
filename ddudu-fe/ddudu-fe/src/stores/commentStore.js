import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

const REST_COMMENT_API = "http://localhost:8080/comment";

export const useCommentStore = defineStore("commentStore", () => {
  const commentList = ref([]);
  const errorMessage = ref("");

  const formatDate = (dateString) => {
    const options = {
      year: "numeric",
      month: "2-digit",
      day: "2-digit",
      hour: "2-digit",
      minute: "2-digit",
    };
    const date = new Date(dateString);
    return date.toLocaleString("en-GB", options);
  };

  const fetchCommentsByArticleId = async (articleId) => {
    try {
      const response = await axios.get(`${REST_COMMENT_API}/${articleId}`);
      commentList.value = response.data.map((comment) => ({
        ...comment,
        commentCreated: formatDate(comment.commentCreated),
      }));
      errorMessage.value = "";
    } catch (error) {
      if (error.response && error.response.status === 404) {
        errorMessage.value = "해당 게시글에 댓글이 없습니다.";
      } else {
        errorMessage.value = "댓글을 불러오는 중 오류가 발생했습니다.";
      }
      commentList.value = [];
      console.error("Error fetching comments:", error);
    }
  };

  const addComment = async (articleId, commentData) => {
    try {
      const response = await axios.post(
        `${REST_COMMENT_API}/${articleId}`,
        commentData
      );
      if (response.status === 201) {
        errorMessage.value = "";
        await fetchCommentsByArticleId(articleId);
      } else {
        errorMessage.value = "댓글 작성 중 오류가 발생했습니다.";
      }
    } catch (error) {
      errorMessage.value = "댓글 작성 중 오류가 발생했습니다.";
      console.error("Error adding comment:", error);
    }
  };

  return {
    commentList,
    errorMessage,
    fetchCommentsByArticleId,
    addComment,
  };
});
