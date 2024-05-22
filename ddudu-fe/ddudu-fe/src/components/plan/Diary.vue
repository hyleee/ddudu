<template>
  <div class="diary">
    <h3>Diary입니다.</h3>
    <div class="diary-title-container">
      <div>운동일지</div>
      <RouterLink to="/diary"><img src="@/assets/arrow.png" height="30px"></RouterLink>
    </div>
    <div class="diary-container">
      <div class="picture-weight">
        <div class="picture">
          사진
        </div>
        <div class="weight">
          체중
        </div>
      </div>
      <div class="textbox">
        textbox
      </div>
    </div>
    <ExerciseList />
  </div>
</template>

<script setup>
import { RouterLink } from 'vue-router'
import ExerciseList from '@/components/plan/ExerciseList.vue'
</script>

<style scoped>
.diary {
  width: 100%;
  height: auto;
  /* height: 300px; */
}

.diary-title-container {
  display: flex;
  justify-content: space-between;
  border: 1px solid black;
  height: 100px;
}

.diary-container {
  display: flex;
  justify-content: space-around;
  align-items: flex-start;
  width: 100%;
  /* height: 400px; */
  border: 1px solid black;
  margin-bottom: 20px;
}

.picture-weight {
  border: 1px solid black;
  display: flex;
  flex-direction: column;
  width: 35%;
}

.picture {
  border: 1px solid black;
  width: 100%;
  height: 300px;
  margin-bottom: 20px;
}

.weight {
  width: 100%;
  height: 70px;
  border: 1px solid black;
}

.textbox {
  border: 1px solid black;
  width: 50%;
  height: 390px;
  display: flex;
}
</style>