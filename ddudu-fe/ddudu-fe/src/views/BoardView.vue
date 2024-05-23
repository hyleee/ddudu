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
        class="category-button"
        :class="{'sun-button': category === '가슴', 'mon-button': category === '등', 'tue-button': category === '어깨', 'wed-button': category === '하체', 'thu-button': category === '팔', 'fri-button': category === '코어'}"
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
    <button class="create-article-button" @click="createArticle">글쓰기</button>
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

const createArticle = () => {
  router.push({ name: "articleCreate" });
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
  display: flex;
}

.category-button {
  margin-right: 10px;
  padding: 5px 10px;
  border: 1px solid #ccc;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s;
}

.sun-button {
  background-color: #ff6f61;
}

.mon-button {
  background-color: #ff6f61;
}

.tue-button {
  background-color: #ff6f61;
}

.wed-button {
  background-color: #00aaff;
}

.thu-button {
  background-color: #00aaff;
}

.fri-button {
  background-color: #00aaff;
}

.sun-button:hover {
  background-color: #e66055;
}

.mon-button:hover,
.tue-button:hover,
.wed-button:hover,
.thu-button:hover {
  background-color: #555;
}

.fri-button:hover {
  background-color: #0090dd;
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

.create-article-button {
  position: fixed;
  bottom: 80px; /* 하단 네비게이션 바 위로 위치 */
  right: 20px;
  z-index: 1000;
  padding: 10px 20px;
  background-color: #faa300;
  color: white;
  border: none;
  border-radius: 50px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  cursor: pointer;
  transition: background-color 0.3s;
}

.create-article-button:hover {
  background-color: #d68800;
}
</style>
