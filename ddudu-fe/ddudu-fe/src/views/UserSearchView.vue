<template>
  <div class="search-container">
    <h1>친구 검색</h1>
    <h4>친구를 찾아보세요</h4>
  </div>
  <UserSearch />
</template>
  
<script setup>
import UserSearch from "@/components/user/UserSearch.vue";
</script>
  
<style scoped>
.search-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 100px;
}
</style>