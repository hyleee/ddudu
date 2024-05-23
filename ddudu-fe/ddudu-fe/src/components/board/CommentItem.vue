<template>
  <li class="comment-item">
    <div class="comment-box">
      <p class="comment-author">{{ comment.userId }}</p>
      <p class="comment-content">{{ comment.commentContent }}</p>
      <p class="comment-date">{{ formattedDate }}</p>
    </div>
  </li>
</template>

<script setup>
import { defineProps, computed } from "vue";

const props = defineProps({
  comment: Object,
});

const formattedDate = computed(() => {
  const date = new Date(props.comment.commentCreated);
  return date.toLocaleString("en-GB", { timeZone: "UTC" }); // Formatting the date
});
</script>

<style scoped>
.comment-item {
  list-style-type: none; /* 리스트 마커(동그라미) 제거 */
  margin-bottom: 10px;
}

.comment-box {
  padding: 10px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.comment-author {
  font-weight: bold;
  margin-bottom: 5px;
}

.comment-content {
  margin-top: 5px;
  margin-bottom: 10px;
}

.comment-date {
  color: #888;
  font-size: 12px;
}
</style>
