import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

const REST_EXERCISE_API = "http://localhost:8080/plan";

export const useExerciseStore = defineStore(
  "exercise",
  () => {
    const exerciseList = ref([]);
    const exercise = ref({
      planId: "",
      exerciseDate: "",
      userId: "",
      bodyPart: "",
      exerciseName: "",
    });
    const errorMessage = ref(null);
    const selectedForDeletion = ref(new Set());

    const getExerciseList = async (userId, exerciseDate) => {
      try {
        const res = await axios.get(
          `${REST_EXERCISE_API}/${userId}/${exerciseDate}`
        );
        exerciseList.value = res.data;
        errorMessage.value = null;
      } catch (error) {
        errorMessage.value = "운동 계획을 불러오는 중 오류가 발생했습니다.";
        console.error("Failed to fetch exercise list:", error);
      }
    };

    const getExerciseById = (planId) => {
      return exerciseList.value.find((ex) => ex.planId === planId) || {};
    };

    const createExercise = async (newExercise) => {
      try {
        const res = await axios.post(
          `${REST_EXERCISE_API}/${newExercise.exerciseDate}`,
          newExercise
        );
        if (res.status === 200) {
          await getExerciseList(newExercise.userId, newExercise.exerciseDate);
          errorMessage.value = null;
        } else {
          errorMessage.value =
            "새 운동 계획을 추가하는 중 오류가 발생했습니다.";
        }
      } catch (error) {
        errorMessage.value = "새 운동 계획을 추가하는 중 오류가 발생했습니다.";
        console.error("Failed to create exercise:", error);
      }
    };

    const deleteExercise = async (planId) => {
      try {
        const res = await axios.delete(`${REST_EXERCISE_API}/${planId}`);
        if (res.status === 200) {
          exerciseList.value = exerciseList.value.filter(
            (exercise) => exercise.planId !== planId
          );
          errorMessage.value = null;
        } else {
          errorMessage.value = "운동 계획을 삭제하는 중 오류가 발생했습니다.";
        }
      } catch (error) {
        errorMessage.value = "운동 계획을 삭제하는 중 오류가 발생했습니다.";
        console.error("Failed to delete exercise:", error);
      }
    };

    const deleteSelectedExercises = async () => {
      try {
        const promises = Array.from(selectedForDeletion.value).map((planId) =>
          deleteExercise(planId)
        );
        await Promise.all(promises);
      } catch (error) {
        errorMessage.value =
          "선택된 운동 계획을 삭제하는 중 오류가 발생했습니다.";
        console.error("Failed to delete selected exercises:", error);
      }
    };

    const toggleSelection = (planId) => {
      if (selectedForDeletion.value.has(planId)) {
        selectedForDeletion.value.delete(planId);
      } else {
        selectedForDeletion.value.add(planId);
      }
    };

    const clearSelection = () => {
      selectedForDeletion.value.clear();
    };

    return {
      exerciseList,
      exercise,
      errorMessage,
      selectedForDeletion,
      getExerciseList,
      createExercise,
      deleteExercise,
      deleteSelectedExercises,
      toggleSelection,
      clearSelection,
      getExerciseById,
    };
  },
  {
    persist: true,
  }
);
