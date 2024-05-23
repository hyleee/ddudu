<template>
  <div class="profile-container">
    <div class="profile-header">
      <img :src="userProfileImage" class="profile-img" alt="user img" />
      <h2 class="profile-name">{{ userStore.user.userName }}</h2>
      <p class="profile-username">@{{ userStore.user.userId }}</p>
      <div class="profile-stats">
        <div class="stat-item">
          <span class="stat-number">{{ articleCount }}</span>
          <span class="stat-label">게시물</span>
        </div>
        <div class="stat-item">
          <span class="stat-number">{{ commentCount }}</span>
          <span class="stat-label">댓글</span>
        </div>
      </div>
      <div class="profile-actions">
        <button class="follow-btn" @click="showFollowers">팔로워</button>
        <button class="message-btn" @click="showFollowings">팔로잉</button>
      </div>
    </div>
    <BarChart />
    <FollowModal @close="closeModal"/>
  </div>
</template>

<script setup>
import { onMounted, ref, computed } from 'vue';
import { useUserStore } from '@/stores/userStore';
import { useLoginStore } from '@/stores/loginStore';
import { useFollowStore } from '@/stores/followStore';
import axios from 'axios';
import BarChart from "@/components/analysis/BarChart.vue";
import FollowModal from "@/components/mypage/FollowModal.vue";

const userStore = useUserStore();
const loginStore = useLoginStore();
const followStore = useFollowStore();

const articleCount = ref(0);
const commentCount = ref(0);

const userProfileImage = computed(() => {
  const userId = loginStore.loginUser.userId;
  const imagePath = `/src/assets/${userId}.png`;
  try {
    return new URL(imagePath, import.meta.url).href;
  } catch (error) {
    return new URL('@/assets/default.png', import.meta.url).href;
  }
});

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

const showFollowers = async () => {
  const userId = loginStore.loginUser.userId;
  await followStore.getFollowers(userId);
  followStore.showModal = true;
  followStore.modalTitle = "팔로워 목록";
  followStore.modalUserList = await Promise.all(followStore.followers.map(async (follower) => {
    const followerData = await userStore.fetchUserData(follower.followerId); // fetchUserData로 변경
    return { userId: follower.followerId, userName: followerData.userName };
  }));
};

const showFollowings = async () => {
  const userId = loginStore.loginUser.userId;
  await followStore.getFollowings(userId);
  followStore.showModal = true;
  followStore.modalTitle = "팔로잉 목록";
  followStore.modalUserList = await Promise.all(followStore.followings.map(async (following) => {
    const followingData = await userStore.fetchUserData(following.followingId); // fetchUserData로 변경
    return { userId: following.followingId, userName: followingData.userName };
  }));
};

const closeModal = () => {
  followStore.showModal = false;
};

onMounted(async () => {
  const userId = loginStore.loginUser.userId;
  if (loginStore.accessToken && userId) {
    await userStore.fetchUser(userId);
    await fetchCounts();
    await followStore.getFollowers(userId);
    await followStore.getFollowings(userId);
  } else {
    console.error('Login required or user ID is undefined');
  }
});
</script>

<style scoped>
.profile-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #fafafa;
  min-height: 100vh;
  padding: 20px;
}

.profile-header {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #ffffff;
  border-radius: 20px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  padding: 30px;
  margin-bottom: 20px;
  width: 100%;
  max-width: 600px;
  text-align: center;
}

.profile-img {
  width: 150px;
  height: 150px;
  border-radius: 50%;
  margin-bottom: 20px;
  object-fit: cover;
}

.profile-name {
  font-size: 24px;
  font-weight: bold;
  color: #333;
  margin-bottom: 5px;
}

.profile-username {
  font-size: 16px;
  color: #888;
  margin-bottom: 20px;
}

.profile-stats {
  display: flex;
  justify-content: space-around;
  width: 100%;
  margin-bottom: 20px;
}

.stat-item {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.stat-number {
  font-size: 18px;
  font-weight: bold;
  color: #333;
}

.stat-label {
  font-size: 14px;
  color: #888;
}

.profile-actions {
  display: flex;
  justify-content: space-around;
  width: 100%;
}

.follow-btn, .message-btn {
  background-color: #00aaff;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 10px;
  cursor: pointer;
  transition: background-color 0.3s;
  margin: 5px;
}

.follow-btn:hover, .message-btn:hover {
  background-color: #00aaff;
}
</style>
