<template>
  <button class="custom-button"
          :class="{ 'selected': isSelected }"
          @click="toggleSelection"
  >
    <img v-if="svgPath" :src="svgPath" alt="Icon">
    <span v-if="buttonText" style="margin-left: 8px">{{ buttonText }}</span>
  </button>
</template>

<script>
export default {
  name: 'custom-button',
  props: {
    buttonText: String,
    svgPath: String,
  },
  data() {
    return {
      isSelected: false,
    };
  },
  methods: {
    toggleSelection() {
      this.isSelected = !this.isSelected;
    },
  },
};
</script>

<style scoped>
.custom-button {
  display: flex;
  align-items: flex-start;
  padding: 11px;
  cursor: pointer;
  color: black;
  border: none;
  border-radius: 5px;
  transition: background-color 0.3s;
}

.custom-button:hover {
  background-color: lavender;
}

</style>
