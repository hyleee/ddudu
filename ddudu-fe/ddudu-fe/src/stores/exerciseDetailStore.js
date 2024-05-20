import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

const REST_EXERCISE_DETAIL_API_BASE = "http://localhost:8080/plan/detail";

export const useExerciseDetailStore = defineStore("exerciseDetailStore", () => {
  const exerciseDetailList = ref([]);
  const exerciseDetail = ref({
    detailId: "",
    exerciseKg: "",
    exerciseCount: "",
    planId: "",
  });
  const selectedForDeletion = ref(new Set());

  const getExerciseDetailList = async (planId) => {
    try {
      const response = await axios.get(
        `${REST_EXERCISE_DETAIL_API_BASE}/${planId}`
      );
      exerciseDetailList.value = response.data;
    } catch (e) {
      console.error(e);
    }
  };

  const addPlanDetail = async (planId, planDetail) => {
    try {
      const response = await axios.post(
        `${REST_EXERCISE_DETAIL_API_BASE}/${planId}`,
        planDetail
      );
      if (response.status === 201 && response.data) {
        exerciseDetailList.value.push(response.data);
      }
    } catch (error) {
      console.error("운동 상세 정보를 추가하는 중 에러 발생: ", error);
    }
  };

  const updatePlanDetailList = async (planId, planDetails) => {
    try {
      const response = await axios.put(
        `${REST_EXERCISE_DETAIL_API_BASE}/${planId}`,
        planDetails
      );
      if (response.status === 200) {
        exerciseDetailList.value = planDetails;
      }
    } catch (error) {
      console.error("운동 상세 정보를 한꺼번에 수정하는 중 에러 발생: ", error);
    }
  };

  const deletePlanDetail = async (planId, detailId) => {
    try {
      const response = await axios.delete(
        `${REST_EXERCISE_DETAIL_API_BASE}/${planId}/${detailId}`
      );
      if (response.status === 200) {
        exerciseDetailList.value = exerciseDetailList.value.filter(
          (detail) => detail.detailId !== detailId
        );
      }
    } catch (error) {
      console.error("운동 상세 정보를 삭제하는 중 에러 발생: ", error);
    }
  };

  const deleteSelectedExercises = async (planId) => {
    try {
      await Promise.all(
        Array.from(selectedForDeletion.value).map(async (detailId) => {
          await deletePlanDetail(planId, detailId);
        })
      );
      clearSelection();
    } catch (error) {
      console.error("선택된 운동 계획을 삭제하는 중 오류 발생: ", error);
    }
  };

  const toggleSelection = (detailId) => {
    if (selectedForDeletion.value.has(detailId)) {
      selectedForDeletion.value.delete(detailId);
    } else {
      selectedForDeletion.value.add(detailId);
    }
  };

  const clearSelection = () => {
    selectedForDeletion.value.clear();
  };

  return {
    exerciseDetail,
    exerciseDetailList,
    selectedForDeletion,
    getExerciseDetailList,
    addPlanDetail,
    updatePlanDetailList,
    deletePlanDetail,
    toggleSelection,
    clearSelection,
    deleteSelectedExercises,
  };
});
