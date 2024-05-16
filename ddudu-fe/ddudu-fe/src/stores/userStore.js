import { ref } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router' // Assuming router is properly configured

const REST_USER_API = "http://localhost:8080/user"

export const useUserStore = defineStore('user', () => {
  const user = ref({
    userProfile: '',
    userId: '',
    userPassword: '',
    userName: '',
    userEmail: '',
    userAge: '',
    userHeight: '',
    userWeight: '',
    userArea: '',
  })

  const userCreate = async function(user, file) {
    const formData = new FormData();
    formData.append('user', new Blob([JSON.stringify(user)], { type: "application/json" }));

    if (file) {
      formData.append('file', file);
    }

    try {
      const response = await axios({
        url: `${REST_USER_API}/regist`,
        method: 'POST',
        data: formData,
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      });
      console.log("Response:", response.data);
      router.push({ name: 'plan' });
    } catch (e) {
      console.error("Error:", e.response ? e.response.data : e.message);
    }
  }

  return { useUserStore, user, userCreate }
})
