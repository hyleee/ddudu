<template>
  <div class="modal show">
    <div class="modal-bg" @click="$emit('close')">
      <div class="modal-form" @click.stop>
        <div class="modal-top flex aifs jcsb">
          <h1 class="modal-title">Schedules<br>in {{ year }}. {{ month }}. / {{ day }}.</h1>
          <button @click="$emit('close')">※</button>
        </div>
        <div class="modal-content">
          <div class="schedule-list">
            <div class="flex aic jcc" v-if="!schedules.length" style="width: 100%; height: 100%;">
              There are no schedules.. :(
            </div>
            <div class="schedule flex aic jcsb" v-for="schedule in schedules" :key="schedule.id">
              <h3>{{ schedule.title }}</h3>
              <button @click="removeSchedule(schedule.id)">⊙</button>
            </div>
          </div>
          <div class="form-inf">
            <button @click="addSchedule">Add Schedule...</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from 'vue';
import { useScheduleManager } from '../composables/useScheduleManager';

// props를 정의합니다
const props = defineProps({
  year: Number,
  month: Number,
  day: Number,
});

// ScheduleManager의 함수와 데이터를 가져옵니다
const { schedules, addSchedule, removeSchedule, refreshScheduleList } = useScheduleManager();

// props가 변경될 때마다 스케줄 목록을 새로고침합니다
watch([() => props.year, () => props.month, () => props.day], () => {
  refreshScheduleList();
});
</script>

<style>
@import '@/assets/style.css';
</style>
