<template>
  <div class="custom-block">
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: 'CustomBlock',
};
</script>

<style scoped>
.custom-block {
  border: 2px solid #e6e6fa;
  border-radius: 20px;
  padding: 15px;
}
</style>
