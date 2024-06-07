<script setup>

import CustomBlock from "@/components/UI/CustomBlock.vue";
</script>

<template>
  <custom-block class="custom-block">
    <div class="block">
      <v-alert-title class="error-title">Ошибка 404</v-alert-title>
      <div class="error-message">Страница не найдена</div>
      <v-img class="img" src="\src\assets\error.png"></v-img>
    </div>
  </custom-block>
</template>

<style scoped>

.custom-block{
  border: 2px solid black;
  border-radius: 20px;
}

.block{
  width: 100%;
  padding: 50px;
}

.error-title{
  font-size: 60px;
  margin-bottom: 30px;
}

.error-message{
  font-size: 30px;
  color: mediumpurple;
}

.img{
  width: 26%;
  margin: 0 0 0 60%;
}

</style>
