<template>
  <div class="container">
    <img src="@/assets/duck.png" alt="duck" height="200" />
    <!-- <h2>Login.vue</h2> -->

    <!-- form 태그 추가 -->
    <form @submit.prevent="handleLogin" class="login-form">
      <input type="text" placeholder="userId" v-model="userId" />
      <input type="password" placeholder="userPassword" v-model="userPassword" />
      <button type="submit">login</button>
    </form>

    <RouterLink to="/signup" class="signup-button">SignUp</RouterLink>
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

.container img {
  margin-bottom: 20px;
  display: block;
  margin-left: auto;
  margin-right: auto;
}

.login-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  max-width: 400px;
  padding: 20px;
  background-color: #fff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  margin-top: 50px; /* 아래로 조금 더 이동 */
}

input[type="text"],
input[type="password"] {
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
  margin-bottom: 10px; /* 버튼들 사이 간격 */
}

button:hover {
  background-color: #555;
}

.signup-button {
  display: inline-block;
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
  text-decoration: none; /* RouterLink 기본 스타일 제거 */
  margin-top: 40px;
  margin-bottom: 10px; /* 버튼들 사이 간격 */
}

.signup-button:hover {
  background-color: #555;
}
</style>
