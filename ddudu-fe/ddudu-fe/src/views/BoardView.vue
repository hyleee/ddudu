<template>
  <div class="board-view">
    <h1>우리 동네 운동 게시판</h1>
    <div class="search-bar">
      <input type="text" placeholder="Search" v-model="searchQuery" />
    </div>
    <div class="category-list">
      <button
        v-for="category in categoryList"
        :key="category"
        @click="selectCategory(category)"
      >
        {{ category }}
      </button>
    </div>
    <div class="article-list-container">
      <ArticleList
        :articleList="filteredArticleList"
        @viewArticle="viewArticle"
      />
      <p v-if="boardStore.noContentMessage" class="no-content-message">
        {{ boardStore.noContentMessage }}
      </p>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, watch } from "vue";
import { useRouter } from "vue-router";
import { useBoardStore } from "@/stores/boardStore";
import { useLoginStore } from "@/stores/loginStore";
import ArticleList from "@/components/board/ArticleList.vue";

const searchQuery = ref("");
const selectedCategory = ref("");
const categoryList = ref(["가슴", "등", "어깨", "하체", "팔", "코어"]);
const categoryMap = {
  가슴: "chest",
  등: "back",
  어깨: "shoulders",
  하체: "legs",
  팔: "arms",
  코어: "core",
};
const boardStore = useBoardStore();
const loginStore = useLoginStore();
const router = useRouter();

const filteredArticleList = computed(() => {
  let result = boardStore.articleList;
  if (selectedCategory.value) {
    result = result.filter(
      (article) => article.category === selectedCategory.value
    );
  }
  if (searchQuery.value) {
    result = result.filter((article) =>
      article.articleTitle.includes(searchQuery.value)
    );
  }
  return result;
});

const selectCategory = (category) => {
  const englishCategory = categoryMap[category];
  selectedCategory.value = englishCategory;
  fetchArticlesByUserAreaAndCategory();
};

const fetchArticlesByUserAreaAndCategory = async () => {
  if (selectedCategory.value) {
    await boardStore.fetchArticlesByUserAreaAndCategory(selectedCategory.value);
  } else {
    await boardStore.fetchArticlesByUserArea();
  }
};

const fetchArticleList = async () => {
  await boardStore.fetchArticlesByUserArea();
};

const viewArticle = (articleId) => {
  router.push({ name: "article", params: { articleId } });
};

watch(
  () => [loginStore.loginUser.userArea, selectedCategory.value],
  fetchArticlesByUserAreaAndCategory
);

onMounted(() => {
  fetchArticleList();
});
</script>

<style scoped>
.board-view {
  padding: 20px;
  height: 100%;
  display: flex;
  flex-direction: column;
}

.search-bar {
  margin-bottom: 20px;
}

.category-list {
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

.article-list-container {
  flex: 1;
  overflow-y: auto;
}

.no-content-message {
  text-align: center;
  color: #888;
  margin-top: 20px;
}
</style>
