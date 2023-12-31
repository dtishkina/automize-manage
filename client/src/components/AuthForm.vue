<script>
import CustomBlock from "@/components/UI/CustomBlock.vue";

export default {
  components: {
    CustomBlock,
  },
}
</script>

<template>
    <custom-block style=" border: 2px solid black; border-radius: 20px">

      <v-card-title style="margin-top: 16px">
        <span class="headline" style="margin: 50px">Добро пожаловать</span>
      </v-card-title>

      <v-spacer></v-spacer>

      <v-card-text>
        <v-container>
              <v-text-field label="Имя пользователя" required rounded variant="outlined"></v-text-field>
              <v-text-field label="Пароль" required rounded variant="outlined"></v-text-field>
        </v-container>
      </v-card-text>

      <v-card-actions style="margin-bottom: 16px">
        <v-btn class="submit-btn">Войти</v-btn>
      </v-card-actions>

    </custom-block>
</template>

<style scoped>

.submit-btn{
  background-color: black;
  color: white;
  width: 280px;
  height: 42px;
  margin: auto;
  border-radius: 20px;
}
</style>