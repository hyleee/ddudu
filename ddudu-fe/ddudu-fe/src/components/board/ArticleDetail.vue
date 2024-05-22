<template>
  <div class="article-detail" v-if="store.article">
    <header class="article-header">
      <h1 class="headline">{{ store.article.articleTitle }}</h1>
      <p class="meta">{{ store.article.articleCreated }} / {{ store.article.category }}</p>
    </header>
    <img src="@/assets/js_duck.png" alt="Article Image"class="article-image">
    <div class="article-content">
      <p>{{ store.article.articleContent }}</p>
    </div>
    <p v-if="store.errorMessage" class="error">{{ store.errorMessage }}</p>
  </div>
  <div v-else>
    <p>Loading...</p>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { useArticleStore } from '@/stores/articleStore';

const route = useRoute();
const store = useArticleStore();

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

.headline {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 10px;
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

.error {
  color: red;
  font-size: 14px;
}
</style>
