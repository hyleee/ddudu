<template>
  <div class="diary-detail">
    <h2>Share</h2>
    <div class="image-upload">
      <div class="image-preview" v-if="imgUrl">
        <img :src="imgUrl" alt="Image Preview" />
      </div>
      <input type="file" @change="handleFileUpload" accept="image/*" />
    </div>
    <div class="weight-input">
      <label for="weight">오늘의 체중 : </label>
      <input type="number" id="weight" v-model="todayWeight" placeholder="오늘의 체중">
    </div>
    <div class="diary-content">
      <label for="content">내용 : </label>
      <textarea name="content" cols="30" rows="10" v-model="diaryContent"></textarea>
    </div>
    <div class="actions">
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
.diary-detail {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

h2 {
  font-size: 24px;
  margin-bottom: 20px;
}

.image-upload {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 20px;
}

.image-preview {
  width: 200px;
  height: 200px;
  border: 2px solid #007bff;
  border-radius: 10px;
  overflow: hidden;
  margin-bottom: 10px;
}

.image-preview img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

input[type="file"] {
  display: none;
}

.image-upload label {
  cursor: pointer;
  background-color: #007bff;
  color: white;
  padding: 10px 20px;
  border-radius: 10px;
  transition: background-color 0.3s;
}

.image-upload label:hover {
  background-color: #0056b3;
}

.weight-input, .diary-content {
  width: 100%;
  max-width: 400px;
  margin-bottom: 20px;
}

.weight-input label, .diary-content label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

.weight-input input, .diary-content textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 10px;
}

.actions {
  display: flex;
  justify-content: center;
  width: 100%;
  max-width: 400px;
}

.actions button {
  width: 100%;
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.actions button:hover {
  background-color: #0056b3;
}
</style>