<template>
  <div>
    <h1>MyProfileView</h1>
    <h3>{{ userStore.user.userName }}</h3>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useUserStore } from '@/stores/userStore';
import { useLoginStore } from '@/stores/loginStore';

const userStore = useUserStore();
const loginStore = useLoginStore();

onMounted(() => {
  // 로그인된 사용자의 ID를 사용하여 사용자 데이터를 가져옵니다
  const userId = loginStore.loginUser.userId;
  console.log("loginStore.accessToken: " + loginStore.accessToken)
  if (loginStore.accessToken && userId) {
    userStore.fetchUser(userId);
  }
});
</script>

<style scoped>
</style>