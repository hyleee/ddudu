<template>
  <div>
    <h1>PlanView입니다.</h1>
    <div>
      <Calendar :userId="userId" :exerciseDate="exerciseDate" />
    </div>
    <div>
      <Navigation />
    </div>
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router';
import Calendar from '@/components/plan/Calendar.vue';
import ExerciseList from '@/components/plan/ExerciseList.vue';
import Navigation from '@/components/common/Navigation.vue';

const route = useRoute();
const userId = route.params.userId;
const exerciseDate = route.params.exerciseDate;

</script>

<style scoped>

</style>
