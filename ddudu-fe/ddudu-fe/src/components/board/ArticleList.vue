<template>
  <div class="article-list-container">
    <ul class="article-list">
      <li v-for="article in articleList" :key="article.articleId">
        <RouterLink :to="{ name: 'article', params: { articleId: article.articleId } }">
          <ArticleListItem :article="article" />
        </RouterLink>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { defineProps } from 'vue';
import { RouterLink } from 'vue-router';
import ArticleListItem from './ArticleListItem.vue';

const props = defineProps({
  articleList: Array
});
</script>

<style scoped>
.article-list-container {
  flex: 1;
  overflow-y: auto;
}

.article-list {
  list-style-type: none;
  padding: 0;
}

.article-list li {
  margin-bottom: 20px;
  border-bottom: 1px solid #ccc;
  padding-bottom: 20px;
}

.article-list a {
  text-decoration: none;
  color: inherit;
}
</style>
