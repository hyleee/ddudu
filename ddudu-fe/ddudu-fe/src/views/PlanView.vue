<template>
  <div>
    <h1>PlanView입니다.</h1>
    <div>
      <Calender />
    </div>
    <div>
      <!-- <ExerciseList /> -->
      <Navigation />
    </div>

    
    
    
  </div>
</template>

<script setup>
import Calender from '@/components/plan/Calendar.vue'
import ExerciseList from '@/components/plan/ExerciseList.vue'
import Navigation from '@/components/common/Navigation.vue'


</script>

<style scoped>

</style>