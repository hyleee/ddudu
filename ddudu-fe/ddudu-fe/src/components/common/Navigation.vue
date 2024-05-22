<template>
  <div>
    <div class="navi-button">
      <RouterLink :to="`/plan/${userIdParam}/${exerciseDateParam}`"
        ><img src="@/assets/calendar.png" alt="calendar_img" width="30px"

      /></RouterLink>
      <RouterLink to="/bodyinfo"
        ><img src="@/assets/chart_img.png" alt="chart_img" width="30px"
      /></RouterLink>
      <RouterLink to="/board"
        ><img src="@/assets/board_img.png" alt="board_img" width="30px"
      /></RouterLink>
      <RouterLink to="/rank"
        ><img src="@/assets/rank_img.png" alt="rank_img" width="30px"
      /></RouterLink>
      <RouterLink to="/profile"
        ><img src="@/assets/user.png" alt="user_img" width="30px"
      /></RouterLink>
    </div>
  </div>
</template>

<script setup>
import { RouterLink } from 'vue-router';
import { ref, computed } from 'vue';
import { useExerciseStore } from '@/stores/exerciseStore';

const store = useExerciseStore();

const userIdParam = computed(() => store.userIdParam);
const exerciseDateParam = computed(() => store.exerciseDateParam);

console.log("store.userId: " + userIdParam.value);
console.log("store.exerciseDate: " + exerciseDateParam.value);
</script>

<style scoped>
.navi-button {
  display: flex;
  justify-content: space-around;
  align-items: center;
  position: fixed;
  bottom: 0;
  width: 100%;
  background-color: #fff;
  padding: 10px 0;
  box-shadow: 0 -2px 5px rgba(0, 0, 0, 0.1);
}

.navi-button img {
  max-width: 100%;
  height: auto;
}
</style>
