<template>
  <div class="comment-list">
    <div class="header">
      <h2>댓글 목록</h2>
      <button class="add-comment-button" @click="toggleCommentForm">
        댓글 작성
      </button>
    </div>
    <div v-if="showCommentForm" class="comment-form">
      <textarea
        v-model="newCommentContent"
        placeholder="댓글을 입력하세요..."
        class="comment-textarea"
      ></textarea>
      <button class="submit-comment-button" @click="submitComment">
        작성 완료
      </button>
    </div>
    <p v-if="store.errorMessage" class="error">{{ store.errorMessage }}</p>
    <ul v-if="store.commentList.length">
      <CommentItem
        v-for="comment in store.commentList"
        :key="comment.commentId"
        :comment="comment"
      />
    </ul>
    <p v-else>No comments yet.</p>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useCommentStore } from "@/stores/commentStore";
import CommentItem from "@/components/board/CommentItem.vue";
import { useRoute } from "vue-router";
import { useLoginStore } from "@/stores/loginStore";

const route = useRoute();
const store = useCommentStore();
const loginStore = useLoginStore();

const showCommentForm = ref(false);
const newCommentContent = ref("");

const toggleCommentForm = () => {
  showCommentForm.value = !showCommentForm.value;
};

const submitComment = async () => {
  const articleId = route.params.articleId;
  const userId = loginStore.loginUser.userId;
  if (newCommentContent.value.trim() !== "") {
    const result = await store.addComment(articleId, {
      userId,
      commentContent: newCommentContent.value,
    });
    if (result) {
      newCommentContent.value = "";
      showCommentForm.value = false;
      await store.fetchCommentsByArticleId(articleId);
    }
  }
};

onMounted(async () => {
  const articleId = route.params.articleId;
  await store.fetchCommentsByArticleId(articleId);
});
</script>

<style scoped>
.comment-list {
  padding: 20px;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.add-comment-button {
  padding: 5px 10px;
  font-size: 14px;
  border: none;
  border-radius: 4px;
  background-color: #faa300;
  color: white;
  cursor: pointer;
}

.add-comment-button:hover {
  background-color: #d68800;
}

.comment-form {
  margin-bottom: 20px;
}

.comment-textarea {
  width: 100%;
  height: 80px;
  font-size: 14px;
  padding: 10px;
  margin-bottom: 10px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.submit-comment-button {
  padding: 5px 10px;
  font-size: 14px;
  border: none;
  border-radius: 4px;
  background-color: #f5dd61;
  color: white;
  cursor: pointer;
}

.submit-comment-button:hover {
  background-color: #d4bd50;
}

.error {
  color: red;
  font-size: 14px;
}
</style>
