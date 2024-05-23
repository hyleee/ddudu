<template>
  <div class="upper-navigation">
    <!-- <img src="@/assets/logo.png" alt="logo" width="30px"> -->
    <RouterLink :to="planRoute">
      <img src="@/assets/title_logo.png" width="115px"/>
    </RouterLink>
    <RouterLink to="/usersearch">
      <img src="@/assets/search.png" alt="search" width="30px">
    </RouterLink>
  </div>
</template>

<script setup>
import { RouterLink, useRoute } from 'vue-router';
import { computed } from 'vue';

const route = useRoute();

const planRoute = computed(() => {
  const userId = route.params.userId || 'defaultUserId'; // 필요에 따라 기본값을 설정
  const exerciseDate = route.params.exerciseDate || new Date().toISOString().split('T')[0]; // 현재 날짜를 기본값으로 설정
  return { name: 'plan', params: { userId, exerciseDate } };
});
</script>

<style scoped>
h1 {
  color: #e0983a;
}
.upper-navigation {
  display: flex;
  justify-content: space-between;
  background-color: #f8f9fa;
  padding: 10px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  position: fixed;
  top: 0;
  width: 100%;
  z-index: 1000; /* 다른 요소보다 위에 위치하도록 설정 */
}
img {
  max-width: 100%;
  height: auto;
}
</style>
