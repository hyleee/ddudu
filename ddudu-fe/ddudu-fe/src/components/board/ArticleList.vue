<template>
  <div class="article-list">
    <ul>
      <li v-for="article in articleList" :key="article.articleId">
        <ArticleListItem :article="article" />
      </li>
    </ul>
  </div>
</template>

<script setup>
import { defineProps } from 'vue';
import ArticleListItem from './ArticleListItem.vue';

const props = defineProps({
  articleList: Array
});
</script>

<style scoped>
.article-list ul {
  list-style-type: none;
  padding: 0;
}

.article-list li {
  margin-bottom: 20px;
  border-bottom: 1px solid #ccc;
  padding-bottom: 20px;
}
</style>
