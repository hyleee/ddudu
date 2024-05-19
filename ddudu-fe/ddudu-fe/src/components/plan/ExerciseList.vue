<!-- <template>
  <div class="exercise-list">
    <h2>ExerciseList</h2>
    <div class="exercise-title-container">
      <h3>오늘 운동계획</h3>
      <div class="image">
        <img src="@/assets/plus_button.png">
        <img src="@/assets/pencil.png">
      </div>
    </div>
    <ul>
      <li v-for="exercise in exerciseList" :key="exercise.planId">
        <ExerciseListItem :exercise="exercise" />
      </li>
    </ul>
  </div>
</template> -->

<!-- <script setup>
import ExerciseListItem from './ExerciseListItem.vue'
import { useExerciseStore } from '@/stores/exerciseStore.js'
import { computed, onMounted } from 'vue'
import { useRoute } from 'vue-router'

// Pinia store 사용
const store = useExerciseStore()

// 현재 라우트 정보를 가져옴
const route = useRoute()

// store의 exerciseList를 반응형으로 관리
const exerciseList = computed(() => store.exerciseList)

// 컴포넌트가 마운트될 때 운동 목록을 불러옴
onMounted(async () => {
  const userId = route.params.userId // 라우트 파라미터에서 userId를 가져옴
  const exerciseDate = route.params.exerciseDate // 라우트 파라미터에서 exerciseDate를 가져옴
  await store.getExerciseList(userId, exerciseDate) // 운동 목록을 불러오는 함수 호출
})
</script> -->

<!-- <style scoped>
.exercise-list img {
  margin: 20px;
}

.exercise-title-container {
  border: 1px solid black;
}
</style> -->




<template>
  <div class="exercise-list">
    <h2>ExerciseList</h2>
    <div class="exercise-title-container">
      <h3>오늘 운동계획</h3>
      <div class="image">
        <img src="@/assets/plus_button.png" @click="addSet" alt="세트 추가 버튼">
        <img src="@/assets/pencil.png">
      </div>
    </div>
    <ul>
      <!-- 운동 목록을 반복하여 각 운동 항목을 ExerciseListItem 컴포넌트로 전달 -->
      <li v-for="exercise in exerciseList" :key="exercise.planId">
        <ExerciseListItem :exercise="exercise" />
      </li>
    </ul>
    <div>
      <h3>세트 추가</h3>
      <button @click="addSet">세트 추가</button>
      <div v-for="(set, index) in sets" :key="index">
        <p>{{ index + 1 }}번째 세트!</p>
        <input v-model="set.weight" placeholder="무게" />
        <input v-model="set.reps" placeholder="횟수" />
      </div>
    </div>
  </div>
</template>

<script setup>
import ExerciseListItem from './ExerciseListItem.vue'
import { useExerciseStore } from '@/stores/exerciseStore.js'
import { computed, onMounted, ref } from 'vue'
import { useRoute } from 'vue-router'

// Pinia store 사용
const store = useExerciseStore()

// 현재 라우트 정보를 가져옴
const route = useRoute()

// store의 exerciseList를 반응형으로 관리
const exerciseList = computed(() => store.exerciseList)

// 세트 목록 관리
const sets = ref([])

// 세트 추가 함수
const addSet = () => {
  sets.value.push({ weight: '', reps: '' })
}

// 컴포넌트가 마운트될 때 운동 목록을 불러옴
onMounted(async () => {
  const userId = route.params.userId // 라우트 파라미터에서 userId를 가져옴
  const exerciseDate = route.params.exerciseDate // 라우트 파라미터에서 exerciseDate를 가져옴
  await store.getExerciseList(userId, exerciseDate) // 운동 목록을 불러오는 함수 호출
})
</script>

<style scoped>
.exercise-list img {
  margin: 20px;
}

.exercise-title-container {
  border: 1px solid black;
}

input {
  margin: 5px;
}
</style>
