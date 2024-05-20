<template>
  <div class="container">
    <h4>사용자 등록</h4>
    <form @submit.prevent="userCreate" class="form">
      <div class="form-group">
        <input type="file" @change="handleFileUpload" accept="image/*" />
        <img v-if="imageUrl" :src="imageUrl" alt="Image Preview" />
      </div>
      <div class="form-group">
        <label for="id">ID : </label>
        <input type="text" id="id" v-model="store.user.userId">
      </div>
      <div class="form-group">
        <label for="password">PW : </label>
        <input type="password" id="password" v-model="store.user.userPassword">
      </div>
      <div class="form-group">
        <label for="name">이름 : </label>
        <input type="text" id="name" v-model="store.user.userName">
      </div>
      <div class="form-group">
        <label for="email">이메일 : </label>
        <input type="email" id="email" v-model="store.user.userEmail">
      </div>
      <div class="form-group">
        <label for="age">나이 : </label>
        <input type="number" id="age" v-model="store.user.userAge">
      </div>
      <div class="form-group">
        <label for="height">키 : </label>
        <input type="number" id="height" v-model="store.user.userHeight">
      </div>
      <div class="form-group">
        <label for="weight">체중 : </label>
        <input type="number" id="weight" v-model="store.user.userWeight">
      </div>
      <div class="form-group">
        <label for="area">지역 : </label>
        <input type="text" id="area" v-model="store.user.userArea">
      </div>
      <div class="form-group">
        <button type="submit">등록</button>
      </div>
    </form>
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

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center; /* 수직 중앙 배치 */
  min-height: 100vh;
  background-color: #f5f5f5;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  margin-right: 30px;
}

h4 {
  margin-bottom: 20px;
}

.form {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  max-width: 400px;
}

.form-group {
  width: 100%;
  margin-bottom: 10px; /* 요소 간의 간격 줄임 */
  display: flex;
  flex-direction: column;
}

label {
  margin-bottom: 5px;
  font-weight: bold;
}

input[type="text"],
input[type="password"],
input[type="email"],
input[type="number"],
input[type="file"] {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 16px;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #333;
  color: #fff;
  border: none;
  border-radius: 5px;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  text-align: center;
  margin-top: 10px;
}

button:hover {
  background-color: #555;
}

img {
  max-width: 100%;
  height: auto;
  border-radius: 10px;
  margin-top: 10px;
}
</style>





<!-- <template>
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

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  background-color: #f5f5f5;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
}

h4 {
  margin-bottom: 20px;
}

.form-group {
  width: 100%;
  max-width: 400px;
  margin-bottom: 15px;
  display: flex;
  flex-direction: column;
}

label {
  margin-bottom: 5px;
  font-weight: bold;
}

input[type="text"],
input[type="password"],
input[type="email"],
input[type="number"],
input[type="file"] {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 16px;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #333;
  color: #fff;
  border: none;
  border-radius: 5px;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  text-align: center;
  margin-top: 10px;
}

button:hover {
  background-color: #555;
}

img {
  max-width: 100%;
  height: auto;
  border-radius: 10px;
  margin-top: 10px;
}
</style> -->
