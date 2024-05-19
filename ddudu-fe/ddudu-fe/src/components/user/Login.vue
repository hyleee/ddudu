<template>
  <div class="container">
    <img src="@/assets/duck.png" alt="duck" height="200" />
    <RouterLink to="/signup">SignUp</RouterLink>
    <h2>Login.vue</h2>

    <input type="text" placeholder="userId" v-model="userId" />
    <input type="password" placeholder="userPassword" v-model="userPassword" />

    <button @click="handleLogin">login</button>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useLoginStore } from "@/stores/loginStore";

// 현재 날짜를 YYYY-MM-DD 형식으로 반환하는 함수
const getCurrentDate = () => {
  const date = new Date();
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0");
  const day = String(date.getDate()).padStart(2, "0");
  return `${year}-${month}-${day}`;
};

const loginStore = useLoginStore();
const userId = ref("");
const userPassword = ref("");

const handleLogin = () => {
  const userInfo = {
    userId: userId.value,
    userPassword: userPassword.value,
    exerciseDate: getCurrentDate(),
  };

  loginStore.login(userInfo);
};
</script>

<style scoped>
.container img {
  margin: 50% 30% 30%;
  display: flex;
  align-items: center;
}
</style>
