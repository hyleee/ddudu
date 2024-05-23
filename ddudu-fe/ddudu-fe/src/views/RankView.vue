<template>
  <div class="rank-view">
    <h1><strong>주간 랭킹</strong></h1>
    <div class="buttons">
      <button class="chest-button" @click="updateRank('chest')">가슴</button>
      <button class="back-button" @click="updateRank('back')">등</button>
      <button class="shoulders-button" @click="updateRank('shoulders')">어깨</button>
      <button class="legs-button" @click="updateRank('legs')">하체</button>
      <button class="arms-button" @click="updateRank('arms')">팔</button>
      <button class="core-button" @click="updateRank('core')">복근</button>
    </div>
    <div v-if="errorMessage" class="error">{{ errorMessage }}</div>
    <ul v-if="rankList && rankList.length" class="rank-list">
      <li v-for="(rank, index) in rankList" :key="index" class="rank-item">
        <img :src="getUserProfileImage(rank.userId)" class="profile-img" alt="user img" />
        <div class="rank-details">
          <strong>[{{ index + 1 }}등] {{ rank.userName }} ({{ rank.userId }})</strong>
          <span>{{ rank.totalWeight }}kg </span>
        </div>
        <div class="follow-button">
          <button 
            v-if="isFollowing(loginStore.loginUser.userId, rank.userId)" 
            @click="unfollow(rank.userId)" 
            class="follow-btn">
            Unfollow
          </button>
          <button 
            v-else 
            @click="follow(rank.userId)" 
            class="follow-btn">
            Follow
          </button>
        </div>
      </li>
    </ul>
    <div v-else>Loading...</div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useRankStore } from '@/stores/rankStore.js';
import { useLoginStore } from '@/stores/loginStore';
import { useFollowStore } from '@/stores/followStore';

const followStore = useFollowStore();
const loginStore = useLoginStore();

// Import all images from assets folder
const images = import.meta.glob('@/assets/*.png');

const store = useRankStore();
const route = useRoute();
const router = useRouter();

const rankList = ref([]);
const errorMessage = ref('');

const fetchRank = async (bodyPart) => {
  await store.fetchRank(bodyPart);
  rankList.value = store.rankList;
  errorMessage.value = store.errorMessage;
};

const updateRank = (bodyPart) => {
  router.push(`/rank/${bodyPart}`);
};

const getUserProfileImage = (userId) => {
  const imagePath = images[`/src/assets/${userId}.png`];
  if (imagePath) {
    return new URL(`/src/assets/${userId}.png`, import.meta.url).href;
  } else {
    return new URL('@/assets/default.png', import.meta.url).href;
  }
};

const follow = async (followingId) => {
  const followerId = loginStore.loginUser.userId;
  console.log("Following UserId:", followingId);
  console.log("Logged in UserId (followerId):", followerId);
  await followStore.followUser(followerId, followingId);
};

const unfollow = async (followingId) => {
  const followerId = loginStore.loginUser.userId;
  console.log("Following UserId:", followingId);
  console.log("Logged in UserId (followerId):", followerId);
  await followStore.unfollowUser(followerId, followingId);
};

const isFollowing = (followerId, userId) => {
  return followStore.isFollowing(followerId, userId);
};

onMounted(() => {
  const bodyPart = route.params.bodyPart || 'chest';
  fetchRank(bodyPart);
  watch(
    () => route.params.bodyPart,
    (newBodyPart) => {
      fetchRank(newBodyPart);
    }
  );
  watch(
    () => followStore.followings,
    (newFollowings) => {
      console.log("팔로잉 목록이 변경되었습니다: ", newFollowings);
    },
    { immediate: true }
  );
});
</script>

<style scoped>
.rank-view {
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

h1 {
  text-align: center;
  margin-bottom: 30px;
  margin-top: 30px;
  color: #333;
}

.buttons {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 10px;
  margin-bottom: 20px;
}

button {
  color: white;
  border: none;
  padding: 10px 20px;
  margin: 5px;
  border-radius: 10px;
  cursor: pointer;
  transition: background-color 0.3s;
  flex: 1 1 100px;
  /* Adjusts the buttons to be flexible and fit the container */
  max-width: 120px;
  /* Ensures buttons do not exceed a certain width */
}

.chest-button {
  background-color: #00aaff;
}

.back-button {
  background-color: #00aaff;
}

.shoulders-button {
  background-color: #00aaff;
}

.legs-button {
  background-color: #F4538A;
}

.arms-button {
  background-color: #F4538A;
}

.core-button {
  background-color: #F4538A;
}

button:hover {
  opacity: 0.8;
}

.rank-list {
  list-style-type: none;
  padding: 0;
}

.rank-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px;
  border-radius: 10px;
  background-color: #fff;
  margin-bottom: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.profile-img {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  margin-right: 10px;
}

.rank-details {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.follow-button {
  display: flex;
}

.follow-btn {
  background-color: #F5DD61;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 10px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.follow-btn:hover {
  background-color: #2aaf34;
}

.error {
  color: red;
  text-align: center;
  margin-top: 10px;
}
</style>


<!-- <template>
  <div class="rank-view">
    <h1><strong>주간 랭킹</strong></h1>
    <div class="buttons">
      <button class="chest-button" @click="updateRank('chest')">가슴</button>
      <button class="back-button" @click="updateRank('back')">등</button>
      <button class="shoulders-button" @click="updateRank('shoulders')">어깨</button>
      <button class="legs-button" @click="updateRank('legs')">하체</button>
      <button class="arms-button" @click="updateRank('arms')">팔</button>
      <button class="core-button" @click="updateRank('core')">복근</button>
    </div>
    <div v-if="errorMessage" class="error">{{ errorMessage }}</div>
    <ul v-if="rankList && rankList.length" class="rank-list">
      <li v-for="(rank, index) in rankList" :key="index" class="rank-item">
        <img :src="getUserProfileImage(rank.userId)" class="profile-img" alt="user img" />
        <div class="rank-details">
          <strong>[{{ index + 1 }}등] {{ rank.userName }} ({{ rank.userId }})</strong>
          <span>{{ rank.totalWeight }}kg </span>
        </div>
        <div class="follow-button">
          <button v-if="isFollowing(loginStore.loginUser.userId, rank.userId)"
            @click="unfollow(rank.userId)">Unfollow</button>
          <button v-else @click="follow(rank.userId)">Follow</button>
        </div>
      </li>
    </ul>
    <div v-else>Loading...</div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useRankStore } from '@/stores/rankStore.js';
import { useLoginStore } from '@/stores/loginStore';
import { useFollowStore } from '@/stores/followStore';

const followStore = useFollowStore();

const loginStore = useLoginStore();

// Import all images from assets folder
const images = import.meta.glob('@/assets/*.png');

const store = useRankStore();
const route = useRoute();
const router = useRouter();

const rankList = ref([]);
const errorMessage = ref('');

const fetchRank = async (bodyPart) => {
  await store.fetchRank(bodyPart);
  rankList.value = store.rankList;
  errorMessage.value = store.errorMessage;
};

const updateRank = (bodyPart) => {
  router.push(`/rank/${bodyPart}`);
};

const getUserProfileImage = (userId) => {
  const imagePath = images[`/src/assets/${userId}.png`];
  if (imagePath) {
    return new URL(`/src/assets/${userId}.png`, import.meta.url).href;
  } else {
    return new URL('@/assets/default.png', import.meta.url).href;
  }
};

const follow = async (followingId) => {
  const followerId = loginStore.loginUser.userId;
  console.log("Following UserId:", followingId);
  console.log("Logged in UserId (followerId):", followerId);
  await followStore.followUser(followerId, followingId);
};

const unfollow = async (followingId) => {
  const followerId = loginStore.loginUser.userId;
  console.log("Following UserId:", followingId);
  console.log("Logged in UserId (followerId):", followerId);
  await followStore.unfollowUser(followerId, followingId);
};

const isFollowing = (followerId, userId) => {
  return followStore.isFollowing(followerId, userId);
};

onMounted(() => {
  const bodyPart = route.params.bodyPart || 'chest';
  fetchRank(bodyPart);
  watch(
    () => route.params.bodyPart,
    (newBodyPart) => {
      fetchRank(newBodyPart);
    }
  );
  watch(
    () => followStore.followings,
    (newFollowings) => {
      console.log("팔로잉 목록이 변경되었습니다: ", newFollowings);
    },
    { immediate: true }
  );
});
</script>

<style scoped>
.rank-view {
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

h1 {
  text-align: center;
  margin-bottom: 30px;
  margin-top: 30px;
  color: #333;
}

.buttons {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 10px;
  margin-bottom: 20px;
}

button {
  color: white;
  border: none;
  padding: 10px 20px;
  margin: 5px;
  border-radius: 10px;
  cursor: pointer;
  transition: background-color 0.3s;
  flex: 1 1 100px;
  /* Adjusts the buttons to be flexible and fit the container */
  max-width: 120px;
  /* Ensures buttons do not exceed a certain width */
}

.chest-button {
  background-color: #00aaff;
}

.back-button {
  background-color: #00aaff;
}

.shoulders-button {
  background-color: #00aaff;
}

.legs-button {
  background-color: #F4538A;
}

.arms-button {
  background-color: #F4538A;
}

.core-button {
  background-color: #F4538A;
}

button:hover {
  opacity: 0.8;
}

.rank-list {
  list-style-type: none;
  padding: 0;
}

.rank-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px;
  border-radius: 10px;
  background-color: #fff;
  margin-bottom: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.profile-img {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  margin-right: 10px;
}

.rank-details {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.follow-button {
  background-color: #F5DD61;
  color: white;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.follow-button:hover {
  background-color: rgba(0, 0, 0, 0.1);
}

.error {
  color: red;
  text-align: center;
  margin-top: 10px;
}
</style> -->
