<template>
  <div class="exercise-item" @click="navigateToDetail">
    <img :src="exerciseImage" class="profile-img" />
    <div class="exercise-details">
      <!-- {{ exercise.bodyPart }} -->
      {{ exercise.exerciseName }}
    </div>
    <template v-if="isDeleting">
      <input
        type="checkbox"
        v-model="isSelectedForDeletion"
        @click.stop="updateSelection"
      />
    </template>
  </div>
</template>

<script setup>
import { ref, computed } from "vue";
import { useRouter } from "vue-router";
import { useExerciseStore } from "@/stores/exerciseStore";

// Define props
const props = defineProps({
  planId: Number,
  isDeleting: Boolean,
});

// Use the exercise store
const store = useExerciseStore();
const router = useRouter();

// Get the current exercise information
const exercise = computed(() => store.getExerciseById(props.planId));

// Manage local state
const isSelectedForDeletion = ref(false);

// Update the selection state
const updateSelection = () => {
  store.toggleSelection(props.planId);
};

// Navigate to the detail page
const navigateToDetail = () => {
  router.push({
    name: "exerciseDetail",
    params: { planId: props.planId },
  });
};

// Dynamically import all images from the assets folder
const images = import.meta.glob('@/assets/*.png');

// Compute the exercise image based on the body part
const exerciseImage = computed(() => {
  const imagePath = images[`/src/assets/${exercise.value.bodyPart}.png`];
  if (imagePath) {
    return `/src/assets/${exercise.value.bodyPart}.png`;
  } else {
    return `/src/assets/default.png`;
  }
});
</script>

<style scoped>
.exercise-item {
  display: flex;
  align-items: center;
  padding: 10px;
  padding-left: 40px;
  padding-right: 40px;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-bottom: 10px;
  cursor: pointer;
}

.profile-img {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  margin-right: 10px;
}

.exercise-details {
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: flex-end; /* Align items to the right */
  text-align: right; /* Right align the text */
  margin-right: 20px;
  font-size: 20px;
}

.exercise-details strong {
  font-size: 16px;
  margin-bottom: 5px;
}

.exercise-details span {
  font-size: 14px;
  color: #666;
}
</style>
