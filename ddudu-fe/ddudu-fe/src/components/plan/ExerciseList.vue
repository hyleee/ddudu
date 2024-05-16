<template>
  <div>
    <h2>ExerciseList</h2>
    <ul>
      <li v-for="exercise in exerciseList">
        <ExerciseListItem :exercise="store.exercise" :key="store.planId" />
      </li>
    </ul>
  </div>
</template>

<script setup>
import ExerciseListItem from './ExerciseListItem.vue'
import { useExerciseStore } from '@/stores/exerciseStore.js'

const store = useExerciseStore();

</script>

<style scoped></style>