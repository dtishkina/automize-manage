<script>
import AuthForm from "@/components/forms/AuthForm.vue";

export default {
  components: {
    AuthForm
  }
}
</script>

<template>
  <auth-form class="form"></auth-form>
</template>

<style scoped>

.form{
  width: 380px;
  margin: 100px auto 200px;
}

</style>
