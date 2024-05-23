<template>
  <div class="diary">
    <div class="diary-title-container">
      <div>운동일지</div>
      <RouterLink :to="diaryRoute">
        <img src="@/assets/arrow.png" height="30px">
      </RouterLink>
    </div>
    <div v-if="store.diary.diaryContent || store.diary.todayWeight || store.diary.diaryPhoto" class="diary-container">
      <div class="picture-weight">
        <div class="picture-wrapper">
          <!-- <img v-if="store.diary.diaryPhoto" :src="store.diary.diaryPhoto" alt="사진" /> -->
          <img src="@/assets/today_weight.png" alt="Default 사진" />
        </div>
        <div class="weight">
          <span>{{ store.diary.todayWeight }} kg</span>
        </div>
      </div>
      <div class="textbox">
        <p>{{ store.diary.diaryContent }}</p>
      </div>
    </div>
    <ExerciseList />
  </div>
</template>

<script setup>
import { onMounted, watch, computed } from 'vue';
import { RouterLink, useRoute } from 'vue-router';
import ExerciseList from '@/components/plan/ExerciseList.vue';
import { useDiaryStore } from '@/stores/diaryStore';

const route = useRoute();
const store = useDiaryStore();

const diaryRoute = computed(() => {
  return store.diary.diaryContent || store.diary.todayWeight || store.diary.diaryPhoto
    ? { name: 'diarydetail', params: { userId: route.params.userId, exerciseDate: route.params.exerciseDate } }
    : { name: 'diarycreate' };
});

onMounted(() => {
  store.fetchDiary(route.params.userId, route.params.exerciseDate);
  console.log("route.params.userId: " + route.params.userId);
  console.log("route.params.exerciseDate: " + route.params.exerciseDate);
  console.log("Current Diary State on Mounted:", store.diary); // 현재 상태 확인을 위한 로그
});

watch(() => route.params.exerciseDate, (newDate) => {
  store.fetchDiary(route.params.userId, newDate);
  console.log("Current Diary State on Date Change:", store.diary); // 상태 변경 확인 로그
});
</script>

<style scoped>
.diary {
  width: 100%;
  padding: 20px;
  background-color: #f0f2f5;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.diary-title-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  border-bottom: 1px solid #ddd;
  margin-bottom: 20px;
}

.diary-title-container div {
  font-size: 1.2rem;
  font-weight: bold;
}

.diary-title-container RouterLink {
  display: inline-block; /* RouterLink 요소를 inline-block으로 설정 */
}

.empty-space {
  width: 30px; /* RouterLink 요소와 동일한 크기로 설정 */
}

.diary-container {
  display: flex;
  justify-content: space-around;
  align-items: stretch; /* 요소를 같은 높이로 맞추기 위해 수정 */
  background-color: #fff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
}

.picture-weight {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 35%;
}

.picture-wrapper {
  position: relative;
  width: 100%;
  padding-top: 100%;
  overflow: hidden;
  border-radius: 10px;
  border: 1px solid #ddd;
}

.picture-wrapper img {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 10px;
}

.weight {
  margin-top: 20px;
  padding: 10px;
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  font-size: 1.2rem;
  font-weight: bold;
}

.textbox {
  width: 50%;
  padding: 20px;
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  justify-content: space-between; /* 텍스트를 세로로 정렬 */
}

.textbox p {
  font-size: 1rem;
  line-height: 1.5;
  color: #333;
}

.add-photo-btn, .weight-btn, .save-btn {
  display: block;
  width: 100%;
  margin: 10px 0;
  padding: 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1rem;
}

.add-photo-btn:hover, .weight-btn:hover, .save-btn:hover {
  background-color: #0056b3;
}

textarea {
  width: 100%;
  height: 150px;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  resize: none;
  font-size: 1rem;
}
</style>
