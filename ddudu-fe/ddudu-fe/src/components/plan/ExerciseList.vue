<template>
  <div class="exercise-list">
    <h2>ExerciseList</h2>
    <div class="exercise-title-container">
      <h3>오늘 운동계획</h3>
      <div class="image">
        <img :src="plusImage" @click="addNewExercise" alt="세트 추가 버튼"/>
        <img
          :src="isDeleting ? checkImage : deleteImage"
          @click="toggleDeleteMode"
          alt="삭제 버튼"
        />
      </div>
    </div>
    <ul class="exercise-list-items">
      <li v-for="exercise in exerciseList" :key="exercise.planId">
        <ExerciseListItem :planId="exercise.planId" :isDeleting="isDeleting" />
      </li>
    </ul>
    <div v-if="isAddingNewExercise" class="new-exercise">
      <h4>새 운동 계획 추가</h4>
      <select v-model="newExercise.bodyPart">
        <option disabled value="">운동 부위 선택</option>
        <option value="shoulders">Shoulders</option>
        <option value="arms">Arms</option>
        <option value="legs">Legs</option>
        <option value="chest">Chest</option>
        <option value="back">Back</option>
        <option value="core">Core</option>
      </select>
      <input v-model="newExercise.exerciseName" placeholder="운동 이름" />
      <button @click="saveNewExercise">저장</button>
      <button @click="cancelNewExercise">취소</button>
    </div>
    <div v-if="errorMessage" class="error-message">
      {{ errorMessage }}
    </div>
  </div>
</template>

<script setup>
import ExerciseListItem from "./ExerciseListItem.vue";
import { useExerciseStore } from "@/stores/exerciseStore.js";
import { computed, onMounted, ref } from "vue";
import { useRoute } from "vue-router";

// Pinia store 사용
const store = useExerciseStore();

// 현재 라우트 정보를 가져옴
const route = useRoute();

// store의 exerciseList와 errorMessage를 반응형으로 관리
const exerciseList = computed(() => store.exerciseList);
const errorMessage = computed(() => store.errorMessage);

// 이미지 경로 설정
import checkImage from "@/assets/check.png";
import deleteImage from "@/assets/delete.png";
import plusImage from "@/assets/plus_button.png";

// 상태 관리 변수
const isAddingNewExercise = ref(false);
const isDeleting = ref(false);
const newExercise = ref({ bodyPart: "", exerciseName: "" });

// 컴포넌트가 마운트될 때 운동 목록을 불러옴
onMounted(async () => {
  const userId = route.params.userId; // 라우트 파라미터에서 userId를 가져옴
  const exerciseDate = route.params.exerciseDate; // 라우트 파라미터에서 exerciseDate를 가져옴
  console.log("userId: ", userId);
  console.log("exerciseDate: ", exerciseDate);

  await store.getExerciseList(userId, exerciseDate); // 운동 목록을 불러오는 함수 호출
});

// 새로운 운동 계획 추가 함수
const addNewExercise = () => {
  isAddingNewExercise.value = true;
};

// 새로운 운동 계획 저장 함수
const saveNewExercise = async () => {
  newExercise.value.exerciseDate = store.selectedDate; // 라우트 파라미터 대신 store에서 선택된 날짜 사용
  newExercise.value.userId = route.params.userId;
  await store.createExercise(newExercise.value);
  isAddingNewExercise.value = false;
  newExercise.value = { bodyPart: "", exerciseName: "" };
};

// 새로운 운동 계획 취소 함수
const cancelNewExercise = () => {
  isAddingNewExercise.value = false;
  newExercise.value = { bodyPart: "", exerciseName: "" };
};

// 삭제 모드 토글 함수
const toggleDeleteMode = async () => {
  isDeleting.value = !isDeleting.value;
  if (!isDeleting.value) {
    await store.deleteSelectedExercises(); // 선택된 항목 삭제
    store.clearSelection();
  }
};
</script>

<style scoped>
.exercise-list {
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.exercise-title-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  border-bottom: 1px solid #ddd;
  margin-bottom: 20px;
}

.image img {
  width: 30px;
  height: 30px;
  margin-right: 5px;
  margin-left: 10px;
  cursor: pointer;
}

.exercise-list-items {
  list-style-type: none;
  padding: 0;
}

.new-exercise {
  margin-top: 20px;
}

.new-exercise input, .new-exercise select {
  margin: 5px;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #ddd;
}

.error-message {
  color: red;
  margin-top: 10px;
}
</style>
