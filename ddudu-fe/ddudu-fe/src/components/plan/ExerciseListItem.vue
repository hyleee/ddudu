<template>
  <div class="exercise-item" @click="navigateToDetail">
    <p>{{ exercise.bodyPart }}</p>
    <p>{{ exercise.exerciseName }}</p>
    <template v-if="isDeleting">
      <input
        type="checkbox"
        v-model="isSelectedForDeletion"
        @click.stop="updateSelection"
      />
    </template>
  </div>
</template>

<script setup>
import { ref, computed } from "vue";
import { useRouter } from "vue-router";
import { useExerciseStore } from "@/stores/exerciseStore";

const props = defineProps({
  planId: Number,
  isDeleting: Boolean,
});

const store = useExerciseStore();
const router = useRouter();

// 현재 exercise 정보 가져오기
const exercise = computed(() => store.getExerciseById(props.planId));

// 로컬 상태 관리
const isSelectedForDeletion = ref(false);

// 선택 업데이트 함수
const updateSelection = () => {
  store.toggleSelection(props.planId);
};

// 상세 페이지로 이동하는 함수
const navigateToDetail = () => {
  router.push({
    name: "exerciseDetail",
    params: { planId: props.planId },
  });
};
</script>

<style scoped>
.exercise-item {
  border: 1px solid gray;
  margin: 10px;
  padding: 10px;
  cursor: pointer;
}
</style>
