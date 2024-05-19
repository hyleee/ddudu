import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

const REST_EXERCISE_API = "http://localhost:8080/plan"
const REST_API = "http://localhost:8080"

export const useExerciseStore = defineStore('exercise', () => {

  const exerciseList = ref([])

  const exercisePlan = ref({
    planId: '',
    exerciseDate: '',
    userId: '',
    bodyPart: '',
    exerciseName: '',
  })

  const getExerciseList = function (userId, exerciseDate) {
    axios.get(`${REST_EXERCISE_API}/${userId}/${exerciseDate}`)
      .then((res) => {
        // console.log(res)
        console.log(exerciseList)
        exerciseList.value = res.data;
      })
      .catch((error) => {
        console.error('Failed to fetch exercise list:', error);
      });
  }

  return { exercisePlan, exerciseList, getExerciseList }
})
