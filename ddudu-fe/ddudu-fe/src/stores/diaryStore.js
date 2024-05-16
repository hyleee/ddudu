import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

const REST_DIARY_API = "http://localhost:8080/diary"

export const useDiaryStore = defineStore('diaryStore', () => {

  const diary = ref({ 
    userId: '',
    diaryContent: '',
    todayWeight: '',
    diaryPhoto: '',
    exerciseDate: '',
  })

  const diaryCreate = function(diary){
    axios({
      url : `${REST_DIARY_API}/${userId}/${exerciseDate}`,
      method: 'POST',
      data: diary
    })
    .then(()=>{
      console.log('diary 생성 완료')
    })
    .catch((e)=>{
      console.log(e)
    })
  }


  return { useDiaryStore, diary, diaryCreate }
})
