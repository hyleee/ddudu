<template>
  <div>
    <h1>MyProfileView</h1>
    <!-- <ProfilePicture /> -->
    <BasicProfile />
  </div>
</template>

<script setup>
import BasicProfile from '@/mypage/BasicProfile.vue';
import ProfilePicture from '@/mypage/ProfilePicture.vue';
import { useUserStore } from '@/stores/userStore';
const store = useUserStore();

</script>

<style scoped></style>
