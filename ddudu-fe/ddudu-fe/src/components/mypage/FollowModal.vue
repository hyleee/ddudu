<template>
    <div class="modal" v-if="show">
      <div class="modal-content">
        <span class="close" @click="closeModal">&times;</span>
        <h2>{{ title }}</h2>
        <ul v-if="userList && userList.length">
          <li v-for="user in userList" :key="user.userId" class="user-item">
            <img :src="getUserProfileImage(user.userId)" class="profile-img" alt="user img" />
            <div>
              <strong>{{ user.userName }}</strong>
              <span>@{{ user.userId }}</span>
            </div>
            </li>
          </ul>
        </div>
      </div>
    </template>
  
    <script setup>
    import { computed } from 'vue';
    import { useFollowStore } from '@/stores/followStore';
  
    const followStore = useFollowStore();
  
    const show = computed(() => followStore.showModal);
    const title = computed(() => followStore.modalTitle);
    const userList = computed(() => followStore.modalUserList);
  
    const closeModal = () => {
      followStore.showModal = false;
    };
  
    const getUserProfileImage = (userId) => {
      const images = import.meta.glob('@/assets/*.png');
      const imagePath = images[`/src/assets/${userId}.png`];
      if (imagePath) {
        return new URL(`/src/assets/${userId}.png`, import.meta.url).href;
      } else {
        return new URL('@/assets/default.png', import.meta.url).href;
      }
    };
    </script>
  
    <style scoped>
    .modal {
      display: flex;
      justify-content: center;
      align-items: center;
      position: fixed;
      z-index: 1;
      left: 0;
      top: 0;
      width: 100%;
      height: 100%;
      overflow: auto;
      background-color: rgba(0, 0, 0, 0.4);
    }
  
    .modal-content {
      background-color: #fefefe;
      margin: 15% auto;
      padding: 20px;
      border: 1px solid #888;
      width: 80%;
      max-width: 600px;
      border-radius: 10px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    }
  
    .close {
      color: #aaa;
      float: right;
      font-size: 28px;
      font-weight: bold;
    }
  
    .close:hover,
    .close:focus {
      color: black;
      text-decoration: none;
      cursor: pointer;
    }
  
    .user-item {
      display: flex;
      align-items: center;
      margin-bottom: 10px;
    }
  
    .profile-img {
      width: 50px;
      height: 50px;
      border-radius: 50%;
      margin-right: 10px;
      object-fit: cover;
    }
    </style>
  