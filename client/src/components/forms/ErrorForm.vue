<script setup>

import CustomBlock from "@/components/UI/CustomBlock.vue";
</script>

<template>
  <custom-block class="custom-block">
    <div class="block">
      <v-alert-title class="error-title">Ошибка 404</v-alert-title>
      <div class="error-message">Страница не найдена, попытайтесь еще раз!</div>
      <v-img class="img" src="C:\Users\tiski\IdeaProjects\automize-manage\client\src\assets\error.png"></v-img>
    </div>
  </custom-block>
</template>

<style scoped>

.custom-block{
  border: 2px solid black;
  border-radius: 20px;
  height: 20lh;
}

.block{
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
  width: 150px;
  height: 150px;
  margin: 0 0 0 300px;
}

</style>
