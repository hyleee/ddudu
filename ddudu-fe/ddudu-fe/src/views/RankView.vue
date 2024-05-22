<template>
  <div>
    <h1>RankView입니다.</h1>
    <div>
      <button @click="fetchRank('chest')">Chest Rank</button>
      <button @click="fetchRank('back')">Back Rank</button>
      <button @click="fetchRank('shoulders')">Shoulders Rank</button>
      <button @click="fetchRank('legs')">Legs Rank</button>
      <button @click="fetchRank('arms')">Arms Rank</button>
      <button @click="fetchRank('core')">Core Rank</button>
    </div>
    <div v-if="errorMessage">{{ errorMessage }}</div>
    <ul v-if="rankList.length">
      <li v-for="(rank, index) in rankList" :key="index">
        <strong>{{ rank.userId }}</strong>: {{ rank.totalWeight }}kg ({{ rank.bodyPart }})
      </li>
    </ul>
    <div v-else>Loading...</div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useRankStore } from '@/stores/rankStore.js';

const store = useRankStore();

const rankList = store.rankList;
const errorMessage = store.errorMessage;
const fetchRank = store.fetchRank;

onMounted(() => {
  fetchRank('chest');
});
</script>

<style scoped>
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
</style>
