<template>
  <div class="exercise-detail">
    <!-- <h3>ExerciseDetail입니다.</h3> -->
    <div class="exercise-img">
      <img src="@/assets/squat.png" alt="squat.png" width="200px"/>
    </div>
    <div class="exercise-list-container">
      <ul class="exercise-list">
        <li
          v-for="(exerciseDetail, index) in store.exerciseDetailList"
          :key="exerciseDetail.detailId"
        >
          <ExerciseDetailItem
            :index="index"
            :isDeleting="isDeleting"
            :isEditing="editingSet"
          />
        </li>
        <li v-if="addingSet" class="new-set">
          <input v-model="newSet.exerciseKg" placeholder="무게 (kg)" />
          <input v-model="newSet.exerciseCount" placeholder="횟수" />
          <button @click="completeAddingSet">세트 추가 완료</button>
        </li>
      </ul>
    </div>
    <div class="buttons">
      <button @click="toggleEditingSet">
        {{ editingSet ? "세트 수정 완료" : "세트 수정" }}
      </button>
      <button @click="toggleAddingSet">
        {{ addingSet ? "세트 추가 완료" : "세트 추가" }}
      </button>
      <button @click="toggleDeletingSet">
        {{ isDeleting ? "세트 삭제 완료" : "세트 삭제" }}
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import { useExerciseDetailStore } from "@/stores/exerciseDetailStore.js";
import ExerciseDetailItem from "./ExerciseDetailItem.vue";

const store = useExerciseDetailStore();
const route = useRoute();
const editingSet = ref(false);
const addingSet = ref(false);
const isDeleting = ref(false);
const newSet = ref({
  exerciseKg: "",
  exerciseCount: "",
});

onMounted(async () => {
  const planId = route.params.planId;
  await store.getExerciseDetailList(planId);
});

const toggleEditingSet = async () => {
  if (editingSet.value) {
    await store.updatePlanDetailList(
      route.params.planId,
      store.exerciseDetailList
    );
  }
  editingSet.value = !editingSet.value;
  await store.getExerciseDetailList(route.params.planId); // Ensure the list is updated
};

const toggleAddingSet = () => {
  addingSet.value = !addingSet.value;
  if (!addingSet.value) {
    completeAddingSet();
  }
};

const toggleDeletingSet = async () => {
  isDeleting.value = !isDeleting.value;
  if (!isDeleting.value) {
    await deleteSelectedSets();
    await store.getExerciseDetailList(route.params.planId); // Ensure the list is updated
  }
};

const completeAddingSet = async () => {
  const planId = route.params.planId;
  await store.addPlanDetail(planId, {
    exerciseKg: newSet.value.exerciseKg,
    exerciseCount: newSet.value.exerciseCount,
  });
  newSet.value.exerciseKg = "";
  newSet.value.exerciseCount = "";
  await store.getExerciseDetailList(planId); // Ensure the list is updated
  addingSet.value = false;
};

const deleteSelectedSets = async () => {
  const planId = route.params.planId;
  await store.deleteSelectedExercises(planId);
  await store.getExerciseDetailList(planId); // Ensure the list is updated
};
</script>

<style scoped>
.exercise-detail {
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.exercise-img {
  display: flex;
  justify-content: center;
  margin: 20px 0;
}

.exercise-img img {
  border-radius: 10px;
}

.exercise-list-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.exercise-list {
  list-style-type: none;
  padding: 0;
  width: 100%;
}

.new-set {
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
}

.new-set input {
  margin: 5px;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #ddd;
  flex: 1;
}

.new-set button {
  margin-left: 10px;
  padding: 10px 20px;
  border-radius: 5px;
  background-color: #007bff;
  color: white;
  border: none;
  cursor: pointer;
}

.buttons {
  display: flex;
  justify-content: space-around;
  margin-top: 20px;
}

.buttons button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background-color: #007bff;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s;
  flex: 1;
  margin: 0 5px;
}

.buttons button:hover {
  background-color: #0056b3;
}
</style>
