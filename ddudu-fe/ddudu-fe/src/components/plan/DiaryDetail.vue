<template>
  <div class="diary-detail">
    <h2>내 운동 일지</h2>
    <div class="image-upload">
      <div class="image-preview" v-if="imgUrl">
        <img :src="imgUrl" alt="Image Preview" />
      </div>
      <input type="file" @change="handleFileUpload" accept="image/*" />
    </div>
    <div class="weight-input">
      <label for="weight">오늘의 체중 : </label>
      <input type="number" id="weight" v-model="todayWeight" placeholder="오늘의 체중" :disabled="!isEditing">
    </div>
    <div class="diary-content">
      <label for="content">내용 : </label>
      <textarea name="content" cols="30" rows="10" v-model="diaryContent" :disabled="!isEditing"></textarea>
    </div>
    <div class="actions">
      <button @click="toggleEdit" v-if="!isEditing">수정</button>
      <button @click="updateDiary" v-if="isEditing">수정 완료</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { useDiaryStore } from '@/stores/diaryStore'
import { useLoginStore } from '@/stores/loginStore'

const route = useRoute()
const diaryStore = useDiaryStore()
const loginStore = useLoginStore()

const diaryContent = ref('')
const todayWeight = ref('')
const imgUrl = ref('')
const isEditing = ref(false)

// 이미지 파일들을 가져오기 위해 import.meta.glob 사용
const images = import.meta.glob('@/assets/*.png')

onMounted(async () => {
  await diaryStore.fetchDiary(loginStore.loginUser.userId, route.params.exerciseDate)
  const diary = diaryStore.diary;
  diaryContent.value = diary.diaryContent;
  todayWeight.value = diary.todayWeight;
  // imgUrl을 초기 diaryPhoto 값으로 설정
  if (diary.diaryPhoto) {
    const matchedImage = images[`/src/assets/${diary.diaryPhoto}`];
    if (matchedImage) {
      matchedImage().then((mod) => {
        imgUrl.value = mod.default;
      });
    } else {
      imgUrl.value = new URL('@/assets/logo.png', import.meta.url).href;
    }
  } else {
    imgUrl.value = new URL('@/assets/logo.png', import.meta.url).href;
  }
  console.log('diary.diaryPhoto:', diary.diaryPhoto);
  console.log('imgUrl:', imgUrl.value);
});

const toggleEdit = () => {
  isEditing.value = !isEditing.value;
}

const updateDiary = async () => {
  const diary = {
    userId: loginStore.loginUser.userId,
    exerciseDate: route.params.exerciseDate,
    diaryContent: diaryContent.value,
    todayWeight: todayWeight.value,
    diaryPhoto: imgUrl.value
  }
  await diaryStore.updateDiary(diary)
  toggleEdit();
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
  border: 2px solid #59D5E0; /* 테두리 색상 */
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
  background-color: #59D5E0; /* 버튼 색상 */
  color: white;
  padding: 10px 20px;
  border-radius: 10px;
  transition: background-color 0.3s;
}

.image-upload label:hover {
  background-color: #45B5C1; /* 호버 색상 */
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
  background-color: white; /* 인풋 배경 색상 */
}

.actions {
  display: flex;
  justify-content: center;
  width: 100%;
  max-width: 200px; /* 버튼 가로 길이 줄임 */
}

.actions button {
  width: 100%;
  padding: 10px 20px;
  background-color: #FAA300; /* 버튼 색상 */
  color: white;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.actions button:hover {
  background-color: #D48600; /* 호버 색상 */
}
</style>
