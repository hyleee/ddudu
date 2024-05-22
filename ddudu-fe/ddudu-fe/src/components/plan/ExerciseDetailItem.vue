<template>
  <div class="exercise-item">
    <!-- <h4>ExerciseDetailItem입니다.</h4> -->
    <div v-if="exerciseDetail" class="exercise-detail-content">
      <p class="set-title">{{ index + 1 }} 세 트</p>
      <div v-if="isEditing" class="edit-fields">
        <input v-model="exerciseDetail.exerciseKg" placeholder="무게 (kg)" />
        <input v-model="exerciseDetail.exerciseCount" placeholder="횟수" />
      </div>
      <div v-else class="exercise-display">
        <p class="detail">{{ exerciseDetail.exerciseKg }} kg</p>
        <p class="detail">{{ exerciseDetail.exerciseCount }} 회</p>
      </div>
    </div>
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
import { defineProps } from "vue";
import { useExerciseDetailStore } from "@/stores/exerciseDetailStore";

const props = defineProps({
  index: Number,
  isDeleting: Boolean,
  isEditing: Boolean,
});

const store = useExerciseDetailStore();
const isSelectedForDeletion = ref(false);

// computed로 exerciseDetail 가져오기
const exerciseDetail = computed(() => store.exerciseDetailList[props.index]);

const updateSelection = () => {
  if (exerciseDetail.value) {
    store.toggleSelection(exerciseDetail.value.detailId);
  }
};
</script>

<style scoped>
.exercise-item {
  padding: 10px;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-bottom: 10px;
}

.exercise-detail-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.set-title {
  white-space: nowrap; /* 줄 바뀜 방지 */
  margin-right: 10px; /* 오른쪽 여백 추가 */
}

.edit-fields, .exercise-display {
  display: flex;
  justify-content: space-between;
  width: 100%;
}

.edit-fields input {
  margin: 5px;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #ddd;
  flex: 1;
}

.exercise-display p {
  margin: 5px;
  padding: 10px;
  border-radius: 5px;
  background-color: #f5f5f5;
  flex: 1;
  text-align: center;
}

.detail {
  white-space: nowrap; /* 줄 바뀜 방지 */
  margin: 0 5px; /* 좌우 여백 추가 */
}
</style>
