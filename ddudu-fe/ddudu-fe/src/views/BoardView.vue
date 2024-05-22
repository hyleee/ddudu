<template>
  <div class="board-view">
    <h1>우리 동네 운동 게시판</h1>
    <div class="search-bar">
      <input type="text" placeholder="Search" v-model="searchQuery">
    </div>
    <div class="category-list">
      <button v-for="category in categoryList" :key="category" @click="selectCategory(category)">
        {{ category }}
      </button>
    </div>
    <ArticleList :articleList="filteredArticleList" />
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import axios from 'axios';
import ArticleList from '@/components/board/ArticleList.vue';

const searchQuery = ref('');
const selectedCategory = ref('');
const categoryList = ref(['가슴', '등', '어깨', '하체', '팔', '코어']);
const articleList = ref([]);

const filteredArticleList = computed(() => {
  let result = articleList.value;
  if (selectedCategory.value) {
    result = result.filter(article => article.category === selectedCategory.value);
  }
  if (searchQuery.value) {
    result = result.filter(article => article.title.includes(searchQuery.value));
  }
  return result;
});

const selectCategory = (category) => {
  selectedCategory.value = category;
};

const fetchArticleList = async () => {
  try {
    const response = await axios.get('http://localhost:8080/board');
    articleList.value = response.data;
  } catch (error) {
    console.error('Error fetching articleList:', error);
  }
};

onMounted(() => {
  fetchArticleList();
});
</script>

<style scoped>
.board-view {
  padding: 20px;
}

.search-bar {
  margin-bottom: 20px;
}

.category-list button {
  margin-right: 10px;
  padding: 5px 10px;
  border: 1px solid #ccc;
  background: #515151;
  cursor: pointer;
}

.category-list button:hover {
  background: #b2d7ef;
}
</style>
