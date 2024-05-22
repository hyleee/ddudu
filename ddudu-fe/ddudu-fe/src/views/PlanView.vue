<template>
  <div class="container">
    <h1>PlanView입니다.</h1>
    <div class="content">
      <Calendar :userId="userId" :exerciseDate="exerciseDate" />
    </div>
    <Navigation />
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router';
import Calendar from '@/components/plan/Calendar.vue';
import Navigation from '@/components/common/Navigation.vue';

const route = useRoute();
const userId = route.params.userId;
const exerciseDate = route.params.exerciseDate;
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  height: 100vh;
}

.content {
  flex: 1;
  overflow-y: auto;
  margin-bottom: 60px; /* Navigation 컴포넌트의 높이만큼 여백을 추가합니다. */
}

h1 {
  text-align: center;
  margin: 20px 0;
}
</style>