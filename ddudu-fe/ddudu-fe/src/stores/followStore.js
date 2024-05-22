import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

const REST_FOLLOW_API = "http://localhost:8080/follow";

export const useFollowStore = defineStore(
  "follow",
  () => {
    const followers = ref([]);
    const followings = ref([]);
    const errorMessage = ref(null);

    const getFollowers = async (userId) => {
      try {
        const response = await axios.get(`${REST_FOLLOW_API}/${userId}/followers`);
        console.log("getFollowers의 response: ", response.data);
        followers.value = response.data;
        errorMessage.value = null;
      } catch (error) {
        errorMessage.value = "팔로워 목록을 불러오는 중 오류가 발생했습니다.";
        console.error("Failed to fetch followers list:", error);
      }
    };

    const getFollowings = async (userId) => {
      try {
        const response = await axios.get(`${REST_FOLLOW_API}/${userId}/followings`);
        followings.value = response.data;
        console.log("getFollowings의 response: ", response.data);
        errorMessage.value = null;
      } catch (error) {
        errorMessage.value = "팔로잉 목록을 불러오는 중 오류가 발생했습니다.";
        console.error("Failed to fetch followings list:", error);
      }
    };

    const followUser = async (followerId, followingId) => {
      try {
        const response = await axios.post(`${REST_FOLLOW_API}/${followerId}/${followingId}`);
        if (response.status === 201) {
          followings.value.push({ followerId, followingId }); // Update followings list immediately
          errorMessage.value = null;
        }
      } catch (error) {
        errorMessage.value = "팔로우 신청 중 오류가 발생했습니다.";
        console.error("Failed to follow user:", error);
      }
    };

    const unfollowUser = async (followerId, followingId) => {
      try {
        const response = await axios.delete(`${REST_FOLLOW_API}/${followerId}/${followingId}`);
        if (response.status === 204) {
          followings.value = followings.value.filter(following => !(following.followerId === followerId && following.followingId === followingId)); // Update followings list immediately
          errorMessage.value = null;
        }
      } catch (error) {
        errorMessage.value = "팔로우 해제 중 오류가 발생했습니다.";
        console.error("Failed to unfollow user:", error);
      }
    };

    const isFollowing = (followerId, followingId) => {
      if (Array.isArray(followings.value)) {
        return followings.value.some(following => following.followerId === followerId && following.followingId === followingId);
      }
      console.log("followings.value가 배열이 아닙니다.")
      console.log("followings.value: "+ followings.value)
      return false;
    };

    return { followers, followings, errorMessage, getFollowers, getFollowings, followUser, unfollowUser, isFollowing };
  }
);
