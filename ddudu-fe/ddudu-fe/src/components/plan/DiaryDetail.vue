<template>
  <div>
    <h2>DiaryDetail.vue</h2>
    <div>
      <label for="content">내용 : </label>
      <br>
      <textarea name="content" cols="30" rows="10" v-model="diaryContent"></textarea>
    </div>
    <div>
      <input type="file" @change="handleFileUpload" accept="image/*" />
      <img v-if="imgUrl" :src="imgUrl" alt="Image Preview" />
    </div>
    <div>
      <label for="weight">오늘의 체중 : </label>
      <input type="number" id="weight" v-model="todayWeight" placeholder="오늘의 체중">
    </div>
    <div>
      <button @click="diaryCreate">등록</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRoute } from 'vue-router'
import { useDiaryStore } from '@/stores/diaryStore'

const route = useRoute()
const store = useDiaryStore()

const diaryContent = ref('')
const todayWeight = ref('')
const imgUrl = ref('')

const diaryCreate = async () => {
  const diary = {
    userId: route.params.userId,
    exerciseDate: route.params.exerciseDate,
    diaryContent: diaryContent.value,
    todayWeight: todayWeight.value,
    diaryPhoto: imgUrl.value
  }
  await store.diaryCreate(diary)
}

const handleFileUpload = (event) => {
  const file = event.target.files[0]
  const reader = new FileReader()
  reader.onload = (e) => {
    imgUrl.value = e.target.result
  }
  reader.readAsDataURL(file)
}
</script>

<style scoped>
/* Add your styles here */
</style>
