<template>
  <div>
    <h2>Diary입니다.</h2>
    <div class="diary-header">
      <div>운동일지</div>
      <RouterLink to="/diary">화살표</RouterLink>
    </div>
  </div>
</template>

<script setup>
import { RouterLink } from 'vue-router'
</script>

<style scoped>
.diary-header {
  display: flex;
  justify-content: space-between;
  margin: 30px;
}
</style>