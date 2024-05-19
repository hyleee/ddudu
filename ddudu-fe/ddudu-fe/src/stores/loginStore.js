import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import router from "../router";

// 현재 날짜를 YYYY-MM-DD 형식으로 반환하는 함수
const getCurrentDate = () => {
  const date = new Date();
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0");
  const day = String(date.getDate()).padStart(2, "0");
  return `${year}-${month}-${day}`;
};

export const useLoginStore = defineStore("login", () => {
  const accessToken = ref("");
  const loginUser = ref({});

  const login = (userInfo) => {
    axios
      .post("http://localhost:8080/auth/login", userInfo)
      .then((res) => {
        console.log(res);
        // axios가 res의 data 속성에 응답 본문을 넣어줌.
        accessToken.value = res.data.accessToken;
        loginUser.value = { ...userInfo, name: res.data.name };
        console.log("accessToken: " + accessToken.value);
        console.log("userId: " + userInfo.userId);
        console.log("exerciseDate: " + getCurrentDate());
        router.push({
          name: "plan",
          params: {
            userId: userInfo.userId,
            exerciseDate: getCurrentDate(),
          },
        });
      })
      .catch((e) => {
        console.log("로그인 실패");
        console.log(e);
      });
  };

  const logout = () => {
    accessToken.value = "";
    loginUser.value = {};
  };

  const getMyPage = () => {
    axios
      .get("http://localhost:8080/user/mypage", {
        headers: {
          Authorization: `Bearer ${accessToken.value}`,
        },
      })
      .then((res) => {
        console.log(res);
      });
  };

  return { accessToken, loginUser, login, logout, getMyPage };
});
