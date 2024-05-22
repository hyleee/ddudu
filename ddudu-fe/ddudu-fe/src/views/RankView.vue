<template>
  <div class="rank-view">
    <h1>RankView입니다.</h1>
    <div class="buttons">
      <button @click="updateRank('chest')">Chest Rank</button>
      <button @click="updateRank('back')">Back Rank</button>
      <button @click="updateRank('shoulders')">Shoulders Rank</button>
      <button @click="updateRank('legs')">Legs Rank</button>
      <button @click="updateRank('arms')">Arms Rank</button>
      <button @click="updateRank('core')">Core Rank</button>
    </div>
    <div v-if="errorMessage" class="error">{{ errorMessage }}</div>
    <ul v-if="rankList && rankList.length" class="rank-list">
      <li v-for="(rank, index) in rankList" :key="index" class="rank-item">
        <div class="profile-img"></div>
        <div class="rank-details">
          <strong>{{ index + 1 }}. {{ rank.userName }} ({{ rank.userId }})</strong>
          <span>{{ rank.totalWeight }}kg ({{ rank.bodyPart }})</span>
        </div>
        <button class="follow-button">Follow</button>
      </li>
    </ul>
    <div v-else>Loading...</div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useRankStore } from '@/stores/rankStore.js';

const store = useRankStore();
const route = useRoute();
const router = useRouter();

const rankList = ref([]);
const errorMessage = ref('');

const fetchRank = async (bodyPart) => {
  await store.fetchRank(bodyPart);
  rankList.value = store.rankList;
  errorMessage.value = store.errorMessage;
};

const updateRank = (bodyPart) => {
  router.push(`/rank/${bodyPart}`);
};

onMounted(() => {
  const bodyPart = route.params.bodyPart || 'chest';
  fetchRank(bodyPart);
});

watch(
  () => route.params.bodyPart,
  (newBodyPart) => {
    fetchRank(newBodyPart);
  }
);
</script>

<style scoped>
.rank-view {
  padding: 20px;
}

.buttons {
  display: flex;
  justify-content: space-around;
  margin-bottom: 20px;
}

button {
  background-color: #444;
  color: white;
  border: none;
  padding: 10px;
  margin: 5px;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #555;
}

.rank-list {
  list-style-type: none;
  padding: 0;
}

.rank-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px;
  border-bottom: 1px solid #ccc;
}

.profile-img {
  width: 50px;
  height: 50px;
  background-color: #ccc;
  border-radius: 50%;
  margin-right: 10px;
}

.rank-details {
  flex: 1;
}

.follow-button {
  background-color: blue;
  color: white;
  border: none;
  padding: 5px 10px;
  border-radius: 5px;
  cursor: pointer;
}

.follow-button:hover {
  background-color: darkblue;
}

.error {
  color: red;
}
</style>
