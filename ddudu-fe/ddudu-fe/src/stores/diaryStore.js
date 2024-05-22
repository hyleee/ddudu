import { ref } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

const REST_DIARY_API = "http://localhost:8080/diary"

export const useDiaryStore = defineStore('diaryStore', () => {
  const diary = ref({
    userId: '',
    diaryContent: '',
    todayWeight: '',
    diaryPhoto: '',
    exerciseDate: '',
  })

  const diaryCreate = async (newDiary) => {
    try {
      if (!newDiary.diaryPhoto) {
        newDiary.diaryPhoto = '/assets/js_duck.png';
      }
      await axios.post(`${REST_DIARY_API}/${newDiary.userId}/${newDiary.exerciseDate}`, newDiary, {
        headers: {
          'Content-Type': 'application/json'
        }
      })
      console.log('Diary 생성 완료')
    } catch (e) {
      console.error('Diary 생성 오류:', e)
    }
  }

  const fetchDiary = async (userId, exerciseDate) => {
    console.log("try")
    try {
      console.log("try userId: " + userId)
      console.log("try exerciseDate: " + exerciseDate)
      const response = await axios.get(`${REST_DIARY_API}/${userId}/${exerciseDate}`)
      console.log("Fetched Diary Data:", response.data) // 데이터 확인을 위한 로그

      if (response.status === 200) {
        // 데이터가 잘 들어오는 경우
        diary.value = response.data
      } else if (response.status === 204) {
        // No Content인 경우
        console.log('No diary content for this date')
        diary.value = {
          userId,
          exerciseDate,
          diaryContent: '',
          todayWeight: '',
          diaryPhoto: ''
        }
      }
    } catch (e) {
      if (e.response && e.response.status === 404) {
        // Not Found인 경우
        console.log('No diary found for this date')
        diary.value = {
          userId,
          exerciseDate,
          diaryContent: '',
          todayWeight: '',
          diaryPhoto: ''
        }
      } else {
        // 다른 오류인 경우
        console.error('Diary 조회 오류:', e)
        diary.value = {
          userId,
          exerciseDate,
          diaryContent: '',
          todayWeight: '',
          diaryPhoto: ''
        }
      }
    }
  }

  return { diary, diaryCreate, fetchDiary }
}, { persist: true })
