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

<style>

@font-face {
    font-family: 'ChosunGu';
    src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.0/ChosunGu.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

html, body {
  font-family: 'ChosunGu', sans-serif; /* 여기서 폰트 지정 */
}

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
