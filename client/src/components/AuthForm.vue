<script>
import CustomBlock from "@/components/UI/CustomBlock.vue";
import axios from 'axios';

export default {
  components: {
    CustomBlock,
  },
  data() {
    return {
      username: '',
      password: '',
    };
  },
  methods: {
    login() {
      const formData = new FormData();
      formData.append('username', this.username);
      formData.append('password', this.password);

      axios.post('/login', formData)
        .then(() => {
          window.location.href = '/references';
        })
        .catch(error => {
          console.error('Error on login:', error);
        });
    },
  },
}
</script>

<template>
  <custom-block style=" border: 2px solid black; border-radius: 20px">
    <v-form @submit.prevent="login">

    <v-card-title style="margin-top: 16px">
      <span class="headline" style="margin: 50px">Добро пожаловать</span>
    </v-card-title>

    <v-spacer></v-spacer>

    <v-card-text>
      <v-container>
        <v-text-field label="Имя пользователя" required rounded variant="outlined"></v-text-field>
        <v-text-field label="Пароль" type="password" required rounded variant="outlined"></v-text-field>
      </v-container>
    </v-card-text>

    <v-card-actions style="margin-bottom: 16px">
      <v-btn  type="submit" class="submit-btn">Войти</v-btn>
    </v-card-actions>
    </v-form>
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
