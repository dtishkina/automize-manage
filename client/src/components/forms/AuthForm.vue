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
      const formData = new URLSearchParams();
      formData.append('username', this.username);
      formData.append('password', this.password);

      axios.post('http://localhost:8080/login', formData)
        .then(() => {
          window.location.href = '/references';
        })
        .catch(error => {
          console.error('Error on login:', error);
          window.location.href = '/error';
        });
    }
  },
}
</script>

<template>
  <custom-block class="block">
    <v-form @submit.prevent="login">

    <v-card-title class="headline-block">
      <span class="headline">Добро пожаловать</span>
    </v-card-title>

    <v-spacer></v-spacer>

    <v-card-text>
      <v-container>
        <v-text-field
          v-model="username"
          label="Имя пользователя"
          required
          rounded
          variant="outlined">
        </v-text-field>
        <v-text-field
          v-model="password"
          label="Пароль"
          type="password"
          required
          rounded
          variant="outlined">
        </v-text-field>
      </v-container>
    </v-card-text>

    <v-card-actions class="actions-comp">
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

.actions-comp{
  margin-bottom: 16px;
}

.block{
  border: 2px solid black;
  border-radius: 20px
}

.headline{
  margin: 50px;
}

.headline-block{
  margin-top: 16px;
}

</style>
