<template>
  <div class="exercise-item">
    <p>{{ exercise.bodyPart }}</p>
    <p>{{ exercise.exerciseName }}</p>
  </div>
</template>

<script setup>
import { defineProps } from 'vue';
import { useExerciseStore } from '@/stores/exerciseStore';

const store = useExerciseStore();


// 부모 컴포넌트로부터 exercise 객체를 prop으로 받습니다.
const props = defineProps({
  exercise: Object
});
console.log(store.exercisePlan.bodyPart);
</script>

<style scoped>
.exercise-item {
  border: 1px solid gray;
  margin: 10px;
  padding: 10px;
}
</style>
