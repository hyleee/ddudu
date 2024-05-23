import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import router from "@/router";

const REST_USER_API = "http://localhost:8080/user";

export const useUserStore = defineStore(
  "user",
  () => {
    const user = ref({
      userProfile: "",
      userId: "",
      userPassword: "",
      userName: "",
      userEmail: "",
      userAge: "",
      userHeight: "",
      userWeight: "",
      userArea: "",
    });

    const userList = ref([]);

    const fetchUser = async (userId) => {
      try {
        const response = await axios.get(`${REST_USER_API}/${userId}`);
        user.value = response.data; // 사용자 데이터를 user 상태에 설정
        console.log(response.data);
      } catch (e) {
        console.error(
          "Error fetching user data:",
          e.response ? e.response.data : e.message
        );
      }
    };

    const fetchUserData = async (userId) => { // 추가된 부분
      try {
        const response = await axios.get(`${REST_USER_API}/${userId}`);
        return response.data; // 사용자 데이터를 반환
      } catch (e) {
        console.error(
          "Error fetching user data:",
          e.response ? e.response.data : e.message
        );
      }
    };

    const userCreate = async (newUser, userFile) => {
      const formData = new FormData();
      formData.append(
        "user",
        new Blob([JSON.stringify(newUser)], { type: "application/json" })
      );

      if (userFile) {
        formData.append("file", userFile);
      }

      try {
        const response = await axios({
          url: `${REST_USER_API}/regist`,
          method: "POST",
          data: formData,
          headers: {
            "Content-Type": "multipart/form-data",
          },
        });
        user.value = response.data;
        const userId = newUser.userId;
        if (userId) {
          router.push({ name: "login" });
        } else {
          console.log("userId is missing in the response data.");
        }
      } catch (e) {
        console.error("Error:", e.response ? e.response.data : e.message);
      }
    };

    const searchUsersByName = async (userName) => {
      try {
        const response = await axios.get(`${REST_USER_API}/search/${userName}`);
        userList.value = response.data; // 사용자 목록 데이터를 users 상태에 설정
        console.log(response.data);
      } catch (e) {
        console.error(
          "Error searching userList by name:",
          e.response ? e.response.data : e.message
        );
      }
    };

    return { user, userList, fetchUser, fetchUserData, userCreate, searchUsersByName };
  },
  { persist: true }
);
