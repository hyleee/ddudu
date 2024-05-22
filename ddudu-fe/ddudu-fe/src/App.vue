<template>
  <div class="app-container">
    <UpperNavigation />
    <div class="content-container">
      <RouterView />
    </div>
    <Navigation />
  </div>
</template>

<script setup>
import UpperNavigation from "@/components/common/UpperNavigation.vue";
import Navigation from "@/components/common/Navigation.vue";
import { RouterView } from "vue-router";
</script>

<style scoped>
.app-container {
  display: flex;
  flex-direction: column;
  height: 100vh;
}

.content-container {
  flex: 1;
  overflow-y: auto;
  padding-top: 54px; /* UpperNavigation 높이만큼 위 패딩 추가 */
  padding-bottom: 54px; /* Navigation 높이만큼 아래 패딩 추가 */
  box-sizing: border-box;
}
</style>
