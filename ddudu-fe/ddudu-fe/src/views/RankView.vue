<template>
  <div class="rank-view">
    <h1>주간 랭킹</h1>
    <div class="buttons">
      <button @click="updateRank('chest')">Chest</button>
      <button @click="updateRank('back')">Back</button>
      <button @click="updateRank('shoulders')">Shoulders</button>
      <button @click="updateRank('legs')">Legs</button>
      <button @click="updateRank('arms')">Arms</button>
      <button @click="updateRank('core')">Core</button>
    </div>
    <div v-if="errorMessage" class="error">{{ errorMessage }}</div>
    <ul v-if="rankList && rankList.length" class="rank-list">
      <li v-for="(rank, index) in rankList" :key="index" class="rank-item">
        <div class="profile-img"></div>
        <div class="rank-details">
          <strong>{{ index + 1 }}등 {{ rank.userName }} ({{ rank.userId }})</strong>
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
  background-color: #f5f5f5;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

h1 {
  text-align: center;
  margin-bottom: 30px;
  margin-top: 30px;
  color: #333;
}

.buttons {
  display: flex;
  justify-content: space-around;
  margin-bottom: 20px;
}

button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 20px;
  margin: 5px;
  border-radius: 10px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #0056b3;
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
  border-radius: 10px;
  background-color: #fff;
  margin-bottom: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
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
  display: flex;
  flex-direction: column;
}

.follow-button {
  background-color: #3CCF42;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 10px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.follow-button:hover {
  background-color: #2aaf34;
}

.error {
  color: red;
  text-align: center;
  margin-top: 10px;
}
</style>
