<template>
  <div class="container">
    <!-- <h1>BodyInfoView</h1> -->
    <BodyInfo />
  </div>
</template>

<script setup>
import BodyInfo from "@/components/analysis/BodyInfo.vue";
</script>

<style scoped>
.container {
  max-width: 600px;
  margin: 50px auto;
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

h1 {
  font-size: 1.5rem;
  text-align: center;
  margin-bottom: 20px;
}
</style>
