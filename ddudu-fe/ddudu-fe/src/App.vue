<template>
  <div>
    <!-- <RouterLink to="/">Home</RouterLink> |
    <RouterLink v-if="loginStore.accessToken === ''" to="/login"
      >Login</RouterLink
    >
    <button v-else @click="logout">Logout</button>
    |

    <RouterLink to="/plan">Plan</RouterLink> -->

    <RouterView />
  </div>
</template>

<script setup>
import Navigation from "@/components/common/Navigation.vue";
import { RouterView } from "vue-router";

// import { useLoginStore } from "@/stores/loginStore";
// const loginStore = useLoginStore();
// const logout = () => {
//   loginStore.logout();
// };
</script>

<style scoped></style>
