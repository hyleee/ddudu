<template>
  <div>
    <h4>사용자 등록</h4>
    <div>
      <input type="file" @change="handleFileUpload" accept="img/*" />
      <img v-if="imgUrl" :src="imgUrl" alt="Image Preview" />
    </div>
    <div>
      <label for="id">ID : </label>
      <input type="text" id="id" v-model="store.user.id">
    </div>
    <div>
      <label for="password">PW : </label>
      <input type="text" id="password" v-model="store.user.password">
    </div>
    <div>
      <label for="name">이름 : </label>
      <input type="text" id="name" v-model="store.user.name">
    </div>
    <div>
      <label for="email">이메일 : </label>
      <input type="email" id="email" v-model="store.user.email">
    </div>
    <div>
      <label for="age">나이 : </label>
      <input type="number" id="age" v-model="store.user.age">
    </div>
    <div>
      <label for="age">키 : </label>
      <input type="number" id="age" v-model="store.user.age">
    </div>
    <div>
      <label for="age">체중 : </label>
      <input type="number" id="age" v-model="store.user.age">
    </div>
    <div>
      <label for="age">지역 : </label>
      <input type="number" id="age" v-model="store.user.age">
    </div>
    <div>
      <button @click="userCreate">등록</button>
    </div>
  </div>
</template>

<script setup>

import { ref } from "vue";
import { useUserStore } from "@/stores/userStore.js";

const store = useUserStore()


const imageUrl = ref(null);

function handleFileUpload(event) {
  const file = event.target.files[0]; // 사용자가 선택한 파일 가져오기
  if (file && file.type.startsWith('image')) {
    const reader = new FileReader();
    reader.onload = (e) => {
      imageUrl.value = e.target.result; // 이미지 URL을 읽어서 imageUrl에 저장
    };
    reader.readAsDataURL(file); // 파일 내용을 Data URL로 읽기
  } else {
    alert("Please upload an image file.");
  }
}

const userCreate = function () {
  store.userCreate(user.value)
}
</script>

<style scoped></style>