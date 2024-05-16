import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

const REST_USER_API = "http://localhost:8080/exercise"
const REST_API = "http://localhost:8080"

export const useWorkoutStore = defineStore('exercise', () => {

  const exerciseList = ref([])

  const exercise = ref({
    plan_id: '',
    exercise_date: '',
    user_id: '',
    body_part: '',
    exercise_name: '',
  })

  const getExerciseList = function () {
    axios.get(REST_EXERCISE_API)
      .then((res) => {
        console.log(res)
        exerciseList.value = res.data;
      })
      .catch((error) => {
        console.error('Failed to fetch user list:', error);
      });
  }


  return { useExerciseStore, exercise }
})
