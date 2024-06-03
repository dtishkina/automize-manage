<script setup>

</script>

<template>
  <v-card class="header" app>
    <div class="header-content">
      <v-img src="\src\assets\settings-02.svg" class="img"></v-img>
      <v-card-text class="title">Automize Manage</v-card-text>
    </div>
  </v-card>
</template>

<style scoped>

.img{
  width: 34px;
}

.title{
  font-size: 38px;
}

.header{
  width: 100%;
  height: 60px;
  box-shadow: 0 0 0 0;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 100;
  margin-top: 12px;
  display: flex;
  justify-content: center;
}

.header-content{
  display: flex;
  width: 29%;
  height: 100%;
}
</style>
