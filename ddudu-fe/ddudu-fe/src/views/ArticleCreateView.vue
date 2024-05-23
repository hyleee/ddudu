<template>
  <div class="create-article">
    <h1>Create New Article</h1>
    <form @submit.prevent="submitArticle">
      <div class="form-group">
        <label for="title">제목</label>
        <input type="text" v-model="articleTitle" id="title" required />
      </div>
      <div class="form-group">
        <label for="content">내용</label>
        <textarea v-model="articleContent" id="content" required></textarea>
      </div>
      <div class="form-group">
        <label for="category">카테고리</label>
        <select v-model="category" id="category" required>
          <option value="chest">가슴</option>
          <option value="back">등</option>
          <option value="shoulders">어깨</option>
          <option value="legs">하체</option>
          <option value="arms">팔</option>
          <option value="abs">복근</option>
        </select>
      </div>
      <button type="submit">Submit</button>
    </form>
    <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useArticleStore } from "@/stores/articleStore";

const articleStore = useArticleStore();
const router = useRouter();

const articleTitle = ref("");
const articleContent = ref("");
const category = ref("chest");
const errorMessage = ref("");

const submitArticle = async () => {
  const articleData = {
    articleTitle: articleTitle.value,
    articleContent: articleContent.value,
    category: category.value,
  };
  const result = await articleStore.createArticle(articleData);
  if (result) {
    router.push({ name: "board" });
  } else {
    errorMessage.value = articleStore.errorMessage;
  }
};
</script>

<style scoped>
.create-article {
  max-width: 600px;
  margin: 50px auto;
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

h1 {
  font-size: 1.5rem;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 20px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
}

.form-group input,
.form-group textarea,
.form-group select {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
}

button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background-color: #FAA300;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #0056b3;
}

.error {
  color: red;
  margin-top: 20px;
}
</style>
