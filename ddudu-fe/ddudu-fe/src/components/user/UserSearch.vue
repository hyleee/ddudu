<template>
    <div class="container">
      <h1>Search Users</h1>
      <input v-model="userName" placeholder="Enter user name" />
      <button @click="searchUsers">Search</button>
      <div v-if="userStore.userList.length">
        <h2>Search Results:</h2>
        <ul>
          <li v-for="user in userStore.userList" :key="user.userId">
            {{ user.userName }} - {{ user.userEmail }}
          </li>
        </ul>
      </div>
      <div v-else>
        <p>No users found.</p>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from "vue";
  import { useUserStore } from "@/stores/userStore";
  
  const userName = ref("");
  const userStore = useUserStore();
  
  const searchUsers = () => {
    userStore.searchUsersByName(userName.value);
  };
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
  