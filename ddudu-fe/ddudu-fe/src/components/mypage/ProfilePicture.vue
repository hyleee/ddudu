<template>
  <div>
    <input type="file" @change="onFileChange" />
    <button @click="uploadFile">Upload</button>
    <div id="profile-container">
      <img :src="profilePicture" alt="Profile Picture" v-if="profilePicture" />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useLoginStore } from '@/stores/loginStore';

const profilePicture = ref(null);
const selectedFile = ref(null);
const loginStore = useLoginStore();

const loadProfilePicture = () => {
  const storedPicture = localStorage.getItem('profilePicture');
  if (storedPicture) {
    profilePicture.value = storedPicture;
  }
};

const onFileChange = (event) => {
  const file = event.target.files[0];
  if (file) {
    selectedFile.value = file;
    const reader = new FileReader();
    reader.onload = (e) => {
      profilePicture.value = e.target.result;
    };
    reader.readAsDataURL(file);
  }
};

const uploadFile = async () => {
  if (selectedFile.value) {
    const formData = new FormData();
    formData.append('file', selectedFile.value);
    formData.append('userId', loginStore.loginUser.userId); // 실제 userId를 동적으로 설정

    try {
      const response = await axios.post('http://localhost:8080/user/uploadProfilePicture', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      });

      const fileUrl = response.data;
      profilePicture.value = fileUrl;
      localStorage.setItem('profilePicture', fileUrl);
      alert('Profile picture uploaded successfully!');
    } catch (error) {
      console.error('File upload failed:', error);
    }
  }
};

onMounted(loadProfilePicture);
</script>

<style scoped>
#profile-container {
  margin-top: 20px;
}
img {
  max-width: 200px;
  height: auto;
  border-radius: 50%;
}
</style>
