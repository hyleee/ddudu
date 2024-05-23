<template>
  <div class="article-detail" v-if="store.article">
    <header class="article-header">
      <div class="headline-container">
        <h1 class="headline">
          <template v-if="isEditing">
            <input v-model="editedTitle" class="edit-input" />
          </template>
          <template v-else>
            {{ store.article.articleTitle }}
          </template>
        </h1>
        <div class="button-group" v-if="isOwner && !isEditing">
          <button class="action-button" @click="enableEditing">수정</button>
          <button class="action-button" @click="showDeleteModal">삭제</button>
        </div>
        <div class="button-group" v-if="isEditing">
          <button class="action-button" @click="updateArticle">수정완료</button>
          <button class="action-button" @click="cancelEditing">수정취소</button>
        </div>
      </div>
      <p class="meta">
        {{ store.article.articleCreated }} / {{ store.article.category }}
      </p>
    </header>
    <img src="@/assets/js_duck.png" alt="Article Image" class="article-image" />
    <div class="article-content">
      <template v-if="isEditing">
        <textarea v-model="editedContent" class="edit-textarea"></textarea>
      </template>
      <template v-else>
        <p>{{ store.article.articleContent }}</p>
      </template>
    </div>
    <p v-if="store.errorMessage" class="error">{{ store.errorMessage }}</p>
    <CommentList />
    <div v-if="isDeleteModalVisible" class="modal">
      <div class="modal-content">
        <p>진짜 삭제할거야?ㅠㅠ</p>
        <button @click="deleteArticle" class="delete-confirm-button">
          진짜
        </button>
        <button @click="hideDeleteModal" class="delete-cancel-button">
          가짜
        </button>
      </div>
    </div>
  </div>
  <div v-else>
    <p>Loading...</p>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useArticleStore } from "@/stores/articleStore";
import { useLoginStore } from "@/stores/loginStore";
import CommentList from "@/components/board/CommentList.vue";

const route = useRoute();
const router = useRouter();
const store = useArticleStore();
const loginStore = useLoginStore();

const isEditing = ref(false);
const editedTitle = ref("");
const editedContent = ref("");
const isDeleteModalVisible = ref(false);

const isOwner = computed(
  () => store.article.userId === loginStore.loginUser.userId
);

const enableEditing = () => {
  isEditing.value = true;
  editedTitle.value = store.article.articleTitle;
  editedContent.value = store.article.articleContent;
};

const cancelEditing = () => {
  isEditing.value = false;
  editedTitle.value = "";
  editedContent.value = "";
};

const updateArticle = async () => {
  const updatedArticle = {
    ...store.article,
    articleTitle: editedTitle.value,
    articleContent: editedContent.value,
  };
  await store.updateArticle(store.article.articleId, updatedArticle);
  await store.fetchArticleById(store.article.articleId);
  isEditing.value = false;
};

const showDeleteModal = () => {
  isDeleteModalVisible.value = true;
};

const hideDeleteModal = () => {
  isDeleteModalVisible.value = false;
};

const deleteArticle = async () => {
  await store.deleteArticle(store.article.articleId);
  router.push({ name: "board" });
};

onMounted(async () => {
  const articleId = route.params.articleId;
  await store.fetchArticleById(articleId);
});
</script>

<style scoped>
.article-detail {
  padding: 20px;
}

.article-header {
  margin-bottom: 20px;
  background: #f9f9f9;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.headline-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.headline {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 10px;
  flex: 1;
  text-align: left;
}

.button-group {
  display: flex;
  gap: 10px;
}

.action-button {
  padding: 5px 10px;
  font-size: 14px;
  border: none;
  border-radius: 4px;
  background-color: #59d5e0;
  color: white;
  cursor: pointer;
}

.action-button:hover {
  background-color: #45b4c1;
}

.edit-input {
  font-size: 24px;
  padding: 5px;
  width: 100%;
  box-sizing: border-box;
}

.meta {
  color: #888;
  font-size: 14px;
  margin-top: 5px;
}

.article-image {
  width: 100%;
  height: auto;
  max-width: 500px;
  margin: 20px 0;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.article-content {
  font-size: 16px;
  line-height: 1.5;
  background: #f9f9f9;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.edit-textarea {
  width: 100%;
  height: 200px;
  font-size: 16px;
  padding: 10px;
  box-sizing: border-box;
}

.error {
  color: red;
  font-size: 14px;
}

.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  background: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  text-align: center;
}

.delete-confirm-button {
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  background-color: #3ccf42;
  color: white;
  cursor: pointer;
}

.delete-cancel-button {
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  background-color: #f4538a;
  color: white;
  cursor: pointer;
}

.delete-confirm-button:hover,
.delete-cancel-button:hover {
  opacity: 0.8;
}
</style>
