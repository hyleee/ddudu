<template>
  <div class="profile-container">
    <img :src="`http://localhost:8080/resources/${userStore.user.userProfile}`" class="rounded-circle" alt="user img" width="150px"/>
    <div class="profile-card">
      <h2 class="profile-title">Basic Profile</h2>
      <h3 class="profile-username">{{ userStore.user.userName }}</h3>
      <h3 class="profile-area">{{  userStore.user.userArea }}</h3>
      <div class="profile-stats">
        <p class="stat-item">게시물 수: <span>{{ articleCount }}</span></p>
        <p class="stat-item">댓글 수: <span>{{ commentCount }}</span></p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { useUserStore } from '@/stores/userStore';
import { useLoginStore } from '@/stores/loginStore';
import axios from 'axios';

const userStore = useUserStore();
const loginStore = useLoginStore();

const articleCount = ref(0);
const commentCount = ref(0);

const fetchCounts = async () => {
  try {
    const userId = loginStore.loginUser.userId;
    if (!userId) {
      console.error('User ID is undefined');
      return;
    }
    const commentResponse = await axios.get(`http://localhost:8080/user/${userId}/commentCount`);
    const articleResponse = await axios.get(`http://localhost:8080/user/${userId}/articleCount`);
    articleCount.value = articleResponse.data;
    commentCount.value = commentResponse.data;
  } catch (error) {
    console.error('Error fetching counts:', error);
  }
};

onMounted(async () => {
  const userId = loginStore.loginUser.userId;
  if (loginStore.accessToken && userId) {
    await userStore.fetchUser(userId);
    await fetchCounts();
  } else {
    console.error('Login required or user ID is undefined');
  }
});
</script>

<style scoped>
.profile-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 90vh;
  background-color: #f0f2f5;
  padding: 20px;
}

.profile-card {
  background: #ffffff;
  border-radius: 12px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  padding: 20px;
  max-width: 400px;
  width: 100%;
  text-align: center;
}

.profile-title {
  font-size: 24px;
  font-weight: 600;
  margin-bottom: 10px;
  color: #333;
}

.profile-username {
  font-size: 20px;
  font-weight: 500;
  margin-bottom: 20px;
  color: #555;
}
.profile-area {
  font-size: 20px;
  font-weight: 500;
  margin-bottom: 20px;
  color: #555;
}

.profile-stats {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.stat-item {
  font-size: 16px;
  margin: 10px 0;
  color: #666;
}

.stat-item span {
  font-weight: 600;
  color: #333;
}

img {
  margin-bottom: 100px;
}
</style>
