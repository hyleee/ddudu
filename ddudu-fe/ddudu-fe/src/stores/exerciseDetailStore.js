import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

const REST_EXERCISE_DETAIL_API = "http://localhost:8080/plan/detail"

export const useExerciseDetailStore = defineStore('exerciseDetailStore', () => {

  const exerciseDetailList = ref([])
  const exerciseDetail = ref({
    detailId: "",
    exerciseKg: "",
    exerciseCount: "",
    planId: "",
  })

  const getExerciseDetailList = function (planId) {
    axios.get(`${REST_EXERCISE_DETAIL_API}/${planId}`)
      .then((res) => {
        console.log(res)

        exerciseDetailList.value = res.data;
      })
      .catch((e) => {
        console.log(e)
      }
      )
  }

  const addPlanDetail = async (planId, planDetail) => {
    try {
      const response = await axios.post(`http://localhost:8080/plan/detail/${planId}`, planDetail);
      if (response.status === 200) {
        // 새 항목을 목록에 추가
        exerciseList.value.push(planDetail);
      }
    } catch (error) {
      console.error("운동 상세 정보를 추가하는 중 에러 발생: ", error);
    }
  };

  const updatePlanDetail = async (planId, detailId, planDetail) => {
    try {
      const response = await axios.put(`http://localhost:8080/plan/detail/${planId}/${detailId}`, planDetail);
      if (response.status === 200) {
        // 목록을 갱신
        const index = exerciseList.value.findIndex(detail => detail.detailId === detailId);
        if (index !== -1) {
          exerciseList.value[index] = planDetail;
        }
      }
    } catch (error) {
      console.error("운동 상세 정보를 수정하는 중 에러 발생: ", error);
    }
  };

  const deletePlanDetail = async (planId, detailId) => {
    try {
      const response = await axios.delete(`http://localhost:8080/plan/detail/${planId}/${detailId}`);
      if (response.status === 200) {
        // 목록에서 삭제
        exerciseList.value = exerciseList.value.filter(detail => detail.detailId !== detailId);
      }
    } catch (error) {
      console.error("운동 상세 정보를 삭제하는 중 에러 발생: ", error);
    }
  };

  return { exerciseDetail, exerciseDetailList, getExerciseDetailList, addPlanDetail, updatePlanDetail, deletePlanDetail, }
})
