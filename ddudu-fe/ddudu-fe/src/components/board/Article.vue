<template>
  <div class="article">
    <h1>{{ article.userId }}</h1>
    <p>{{ article.articleTitle }}</p>
    <p>{{ article.articleContent }}</p>
    <img :src="article.image" alt="Article Image">
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { useBoardStore } from '@/stores/boardStore';

const route = useRoute();
const article = ref({});
const store = useBoardStore();

onMounted(async () => {
  const articleId = route.params.id;
  await store.fetchArticle(articleId);
  article.value = store.article;
});
</script>

<style scoped>
.article {
  padding: 20px;
}

.article img {
  width: 100%;
  max-width: 500px;
  height: auto;
  display: block;
  margin: 20px 0;
}
</style>
