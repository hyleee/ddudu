<template>
  <div>
    <h1>MyProfileView</h1>
    {{ store.userName }}

  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useUserStore } from '@/stores/userStore';

const store = useUserStore();

const userName = 

</script>

<style scoped>

</style>