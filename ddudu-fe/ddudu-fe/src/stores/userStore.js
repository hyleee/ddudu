import { ref } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import router from '@/router'; // Assuming router is properly configured

const REST_USER_API = "http://localhost:8080/user";


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
  });

  const fetchUser = async (userId) => {
    try {
      const response = await axios.get(`${REST_USER_API}/${userId}`);
      user.value = response.data; // 사용자 데이터를 user 상태에 설정
    } catch (e) {
      console.error("Error fetching user data:", e.response ? e.response.data : e.message);
    }
  };

  const userCreate = async (newUser, userFile) => {
    const formData = new FormData();
    formData.append('user', new Blob([JSON.stringify(newUser)], { type: "application/json" }));

    if (userFile) {
      formData.append('file', userFile);
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
      user.value = response.data;
      const userId = newUser.userId;
      if(userId) {
        router.push({
          name: "login",
          
        });
      } else {
        console.log("userId is missing in the response data.");
      }
    } catch (e) {
      console.error("Error:", e.response ? e.response.data : e.message);
    }
  };

  return { user, fetchUser, userCreate };
},

  { persist: true });
