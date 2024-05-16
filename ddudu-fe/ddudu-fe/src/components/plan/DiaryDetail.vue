<template>
  <div>
    <h2>DiaryDetail.vue</h2>
    
    <div>
      <label for="age">내용 : </label>
      <br>
      <textarea name="content" cols="30" rows="10" v-model="store.diary.diaryContent"></textarea>
    </div>
    <div>
      <input type="file" @change="handleFileUpload" accept="img/*" />
      <img v-if="imgUrl" :src="imgUrl" alt="Image Preview" />
    </div>
    <div>
      <label for="age"></label>
      <input type="number" id="age" v-model="store.diary.todayWeight" placeholder="오늘의 체중">
    </div>
    <div>
      <button @click="diaryCreate">등록</button>
    </div>
  </div>
</template>

<script setup>
import { useDiaryStore } from '@/stores/diaryStore.js'

const store = useDiaryStore();

</script>

<style scoped>

</style>