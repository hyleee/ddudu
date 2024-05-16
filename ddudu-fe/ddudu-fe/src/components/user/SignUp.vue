<template>
  <div>
    <h4>사용자 등록</h4>
    <div>
      <input type="file" @change="handleFileUpload" accept="image/*" />
      <img v-if="imageUrl" :src="imageUrl" alt="Image Preview" />
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
      <label for="height">키 : </label>
      <input type="number" id="height" v-model="store.user.height">
    </div>
    <div>
      <label for="weight">체중 : </label>
      <input type="number" id="weight" v-model="store.user.weight">
    </div>
    <div>
      <label for="area">지역 : </label>
      <input type="text" id="area" v-model="store.user.area">
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
