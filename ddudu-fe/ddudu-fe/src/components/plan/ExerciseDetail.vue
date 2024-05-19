<template>
  <div>
    <h3>ExerciseDetail입니다.</h3>
    <div class="exercise-img">
      <img src="@/assets/squat.png" alt="squat.png" width="200px" />
    </div>
    <div>
      <ul>
        <li v-for="(exerciseDetail, index) in store.exerciseDetailList" :key="exerciseDetail.detailId">
          <ExerciseDetailItem :index="index" />
        </li>
      </ul>
    </div>
    <div>
      <button>세트 수정</button>
      <button>세트 추가</button>
    </div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { useExerciseDetailStore } from '@/stores/exerciseDetailStore.js';
import ExerciseDetailItem from './ExerciseDetailItem.vue';

const store = useExerciseDetailStore();
const route = useRoute();

onMounted(async () => {
  const planId = route.params.planId;
  console.log(planId);
  await store.getExerciseDetailList(planId);
  console.log(store.exerciseDetailList); // 데이터를 잘 불러오는지 확인
});
</script>

<style scoped>
.exercise-img {
  display: flex;
  justify-content: center;
  margin: 100px;
}
</style>
