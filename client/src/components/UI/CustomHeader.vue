<script>
  export default {
    name: 'custom-header',
    props: {
      headerText: String,
      descriptionText: String
    }
  };
</script>

<template>
  <header class="custom-header">
    <h1>{{ headerText }} </h1>
    <div class="line"></div>
    <h5> {{ descriptionText }}</h5>
  </header>
</template>

<style scoped>

.custom-header{
  display: flex;
  flex-direction: column;
}

.line{
  width: 100%;
  height: 1px;
  background-color: #d3d9d6;
  margin: 7px 0 20px 0;
}

h5{
  color: #abafbc;
  margin-bottom: 30px;
}

</style>
