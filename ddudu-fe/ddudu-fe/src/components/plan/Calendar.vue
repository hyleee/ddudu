<template>
  <div>
    <h2>Calendar.vue입니다.</h2>
    <Diary />
  </div>
</template>

<script setup>
import { RouterLink } from 'vue-router'
import DiaryDetail from '@/components/plan/DiaryDetail.vue'
import Diary from '@/components/plan/Diary.vue'
</script>

<style scoped>

</style>