<template>
  <div>
    <h4>사용자 등록</h4>
    <div>
      <input type="file" @change="handleFileUpload" accept="image/*" />
      <img v-if="imageUrl" :src="imageUrl" alt="Image Preview" />
    </div>
    <div>
      <label for="id">ID : </label>
      <input type="text" id="id" v-model="store.user.userId">
    </div>
    <div>
      <label for="password">PW : </label>
      <input type="text" id="password" v-model="store.user.userPassword">
    </div>
    <div>
      <label for="name">이름 : </label>
      <input type="text" id="name" v-model="store.user.userName">
    </div>
    <div>
      <label for="email">이메일 : </label>
      <input type="email" id="email" v-model="store.user.userEmail">
    </div>
    <div>
      <label for="age">나이 : </label>
      <input type="number" id="age" v-model="store.user.userAge">
    </div>
    <div>
      <label for="height">키 : </label>
      <input type="number" id="height" v-model="store.user.userHeight">
    </div>
    <div>
      <label for="weight">체중 : </label>
      <input type="number" id="weight" v-model="store.user.userWeight">
    </div>
    <div>
      <label for="area">지역 : </label>
      <input type="text" id="area" v-model="store.user.userArea">
    </div>
    <div>
      <button @click="userCreate">등록</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/userStore";

const store = useUserStore();
const imageUrl = ref(null);
const file = ref(null);

function handleFileUpload(event) {
  const uploadedFile = event.target.files[0];
  if (uploadedFile && uploadedFile.type.startsWith('image')) {
    file.value = uploadedFile;
    const reader = new FileReader();
    reader.onload = (e) => {
      imageUrl.value = e.target.result;
    };
    reader.readAsDataURL(uploadedFile);
  } else {
    alert("Please upload an image file.");
  }
}

function userCreate() {
  store.userCreate(store.user, file.value);
}
</script>

<style scoped></style>
