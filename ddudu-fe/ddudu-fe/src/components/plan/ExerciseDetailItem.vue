<template>
  <div>
    <h4>ExerciseDetailItem입니다.</h4>
    <div v-if="exerciseDetail">
      <p>{{ index + 1 }}번째 세트</p>
      <div v-if="isEditing">
        <input v-model="exerciseDetail.exerciseKg" placeholder="무게 (kg)" />
        <input v-model="exerciseDetail.exerciseCount" placeholder="횟수" />
      </div>
      <div v-else>
        <p>무게: {{ exerciseDetail.exerciseKg }} kg</p>
        <p>횟수: {{ exerciseDetail.exerciseCount }}</p>
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

<style scoped></style>
