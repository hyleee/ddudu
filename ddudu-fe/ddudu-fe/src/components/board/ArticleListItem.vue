<template>
  <RouterLink
    :to="{ name: 'article', params: { articleId: article.articleId } }"
    class="article-list-item"
  >
    <img src="@/assets/js_duck.png" alt="Article Image" class="article-image" />
    <div class="article-content">
      <p class="article-author">
        {{ article.userId }} / {{ article.userArea }}
      </p>
      <h3>{{ article.articleTitle }}</h3>
      <p class="article-preview">{{ article.articleContent }}</p>
      <p class="article-created">{{ article.articleCreated }}</p>
    </div>
  </RouterLink>
</template>

<script setup>
import { defineProps } from "vue";
import { RouterLink } from "vue-router";

const props = defineProps({
  article: Object,
});
</script>

<style scoped>
.article-list-item {
  display: flex;
  align-items: center;
  text-decoration: none;
  color: inherit;
}

.article-list-item:hover {
  background-color: #f0f0f0;
}

.article-image {
  width: 80px;
  height: 80px;
  margin-right: 20px;
}

.article-content {
  flex: 1;
}

.article-author {
  font-size: 14px;
  color: #888;
}

.article-preview {
  font-size: 16px;
  margin: 5px 0;
}

.article-created {
  font-size: 12px;
  color: #aaa;
}
</style>
