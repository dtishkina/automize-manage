<template>
  <v-app>
    <v-main>
      <div class="app-container">
        <navbar v-if="!$route.meta.hideNavbar" />
        <no-info-header v-if="$route.meta.showNoInfoHeader" />
        <div class="app-content">
          <router-view></router-view>
        </div>
      </div>
    </v-main>
  </v-app>
</template>

<script>
import Navbar from '@/components/NavbarComp.vue'
import NoInfoHeader from "@/components/UI/NoInfoHeader.vue";
export default {
  name: 'App',
  components: {
    NoInfoHeader,
    Navbar,
  },

}
</script>


<style>
.app-container {
  display: flex;
}

.app-content {
  display: flex;
  justify-content: center;
  width: 1144px;
  flex: 1;
  font-family: Manrope, ui-rounded;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: left;
  color: #2c3e50;
  margin: 40px 60px 120px;
}
</style>
