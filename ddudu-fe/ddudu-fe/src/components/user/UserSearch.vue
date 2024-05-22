<template>
  <div class="container">
    <!-- <h1>Search Users</h1> -->
    <div class="search-bar">
      <input v-model="userName" placeholder="Enter user name" />
      <button @click="searchUsers">Search</button>
    </div>
    <div v-if="filteredUserList.length" class="results">
      <h2>Search Results:</h2>
      <ul>
        <li v-for="user in filteredUserList" :key="user.userId" class="user-item">
          <span>{{ user.userName }} - {{ user.userEmail }}</span>
          <button v-if="isFollowing(loginStore.loginUser.userId, user.userId)" @click="unfollow(user.userId)">Unfollow</button>
          <button v-else @click="follow(user.userId)">Follow</button>
        </li>
      </ul>
    </div>
    <div v-else>
      <p>No users found.</p>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, watch, onMounted } from "vue";
import { useUserStore } from "@/stores/userStore";
import { useFollowStore } from "@/stores/followStore";
import { useLoginStore } from "@/stores/loginStore";

const userName = ref("");
const userStore = useUserStore();
const followStore = useFollowStore();
const loginStore = useLoginStore();

const searchUsers = () => {
  userStore.searchUsersByName(userName.value);
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

// 로그인된 사용자를 제외한 필터링된 사용자 목록
const filteredUserList = computed(() => {
  return userStore.userList.filter(user => user.userId !== loginStore.loginUser.userId);
});

watch(
  () => followStore.followings,
  (newFollowings) => {
    console.log("팔로잉 목록이 변경되었습니다: ", newFollowings);
  },
  { immediate: true }
);

onMounted(async () => {
  console.log("Logged in UserId onMounted:", loginStore.loginUser.userId);
  await followStore.getFollowings(loginStore.loginUser.userId);
});
</script>

<style scoped>
.container {
  max-width: 600px;
  margin: 50px auto;
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  text-align: center;
}

h1 {
  font-size: 1.5rem;
  margin-bottom: 20px;
}

.search-bar {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.search-bar input {
  width: 60%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  margin-right: 10px;
}

.search-bar button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background-color: #007bff;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s;
}

.search-bar button:hover {
  background-color: #0056b3;
}

.results {
  margin-top: 20px;
  text-align: left;
}

.results h2 {
  font-size: 1.2rem;
  margin-bottom: 10px;
}

ul {
  list-style-type: none;
  padding: 0;
}

li.user-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #fff;
  border-radius: 5px;
  padding: 10px;
  margin-bottom: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

li.user-item span {
  flex-grow: 1;
}

li.user-item button {
  margin-left: 10px;
  padding: 5px 10px;
  border: none;
  border-radius: 5px;
  background-color: #007bff;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s;
}

li.user-item button:hover {
  background-color: #0056b3;
}

li.user-item button:nth-child(3) {
  background-color: #dc3545;
}

li.user-item button:nth-child(3):hover {
  background-color: #c82333;
}

p {
  font-size: 1rem;
  color: #666;
}
</style>
