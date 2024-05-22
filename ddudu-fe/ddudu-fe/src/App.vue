<template>
  <div>
    <UpperNavigation />
    <div class="navi-blank"></div>
    <RouterView />
    <Navigation />
  </div>
</template>

<script setup>
import UpperNavigation from "@/components/common/UpperNavigation.vue";
import Navigation from "@/components/common/Navigation.vue";
import { RouterView } from "vue-router";
</script>

<style scoped>
.navi-blank{
  margin-top: 54px;
}

</style>
