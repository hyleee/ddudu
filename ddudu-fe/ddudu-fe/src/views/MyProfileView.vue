<template>
  <div>
    <h1>MyProfileView</h1>
    <BasicProfile />
  </div>
</template>

<script setup>
import BasicProfile from '@/components/mypage/BasicProfile.vue';
import ProfilePicture from '@/components/mypage/ProfilePicture.vue';
import { useUserStore } from '@/stores/userStore';
const store = useUserStore();

</script>

<style scoped></style>
