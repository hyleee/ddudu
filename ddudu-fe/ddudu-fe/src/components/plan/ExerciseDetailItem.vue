<template>
  <div>
    <h4>ExerciseDetailItem입니다.</h4>
    <div>
      <p>{{ index + 1 }}</p>
      <p>{{ store.exerciseDetailList[index].exerciseKg }}</p>
      <p>{{ store.exerciseDetailList[index].exerciseCount }}</p>
    </div>
  </div>
</template>

<script setup>
import { defineProps, } from 'vue';
import { useExerciseDetailStore } from '@/stores/exerciseDetailStore';

const props = defineProps({
  index: Number
});

const store = useExerciseDetailStore();
// const exerciseDetail = computed(() => store.exerciseDetailList[props.index]);

</script>

<style scoped>
</style>
