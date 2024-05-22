<template>
  <div>
    <UpperNavigation />
    <RouterView />
    <Navigation />
  </div>
</template>

<script setup>
import UpperNavigation from "@/components/common/UpperNavigation.vue";
import Navigation from "@/components/common/Navigation.vue";
import { RouterView } from "vue-router";
</script>

<style scoped></style>
