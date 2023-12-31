<template>
  <v-app>
    <v-main>
      <div class="app-container">
<!--        <navbar></navbar>-->
        <div class="app-content">
          <router-view></router-view>
        </div>
      </div>
    </v-main>
  </v-app>
</template>

<script>
import Navbar from '@/components/NavbarComp.vue'
export default {
  name: 'App',

  components: {
    Navbar,
  },

}
</script>


<style>
.app-container {
  display: flex;
}

.app-content {
  flex: 1;
  font-family: Manrope, ui-rounded;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: left;
  color: #2c3e50;
  margin: 40px 60px;
}
</style>
