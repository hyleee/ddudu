<template>
    <div class="container">
      <h1>Search Users</h1>
      <input v-model="userName" placeholder="Enter user name" />
      <button @click="searchUsers">Search</button>
      <div v-if="filteredUserList.length">
        <h2>Search Results:</h2>
        <ul>
          <li v-for="user in filteredUserList" :key="user.userId">
            {{ user.userName }} - {{ user.userEmail }}
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
      margin-top: 100px;
    }
  
    input {
      margin-right: 10px;
    }
  
    button {
      margin-bottom: 20px;
    }
  
    ul {
      list-style-type: none;
      padding: 0;
    }
  
    li {
      margin-bottom: 10px;
    }
    </style>
  