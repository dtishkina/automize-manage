<template>
  <v-navigation-drawer
    app
    v-model="drawer"
    temporary=""
    class="navbar"
  >
    <div class="navbar__logo" @click="$router.push('/')" @click.stop="toggleDrawer">
      <v-app-bar-nav-icon style="margin-right: 10px">
        <img :src="'src/assets/settings-02.svg'" alt="Icon"/>
      </v-app-bar-nav-icon>
      <v-app-bar-title>Automize <br> manage</v-app-bar-title>
    </div>
    <div class="navbar__btns">
      <custom-button
        style="margin-bottom: 5px; width: 100%;" @click="$router.push('/')"
        button-text="Справочники"
        :svg-path="'src/assets/rows-01.svg'"
      />
      <custom-button
        style="margin-bottom: 5px; width: 100%;" @click="$router.push('/logs')"
        button-text="Журналы"
        :svg-path="'src/assets/book-open-01.svg'"
      />
<!--      <custom-button-->
<!--        style="margin-bottom: 5px; width: 100%" @click="$router.push('/report')"-->
<!--        button-text="Отчеты"-->
<!--        :svg-path="'src/assets/file-03.svg'"-->
<!--      />-->
      <div class="navbar__another">
        <custom-button style="width: 100%" @click="$router.push('/composition')"
                       button-text="Выход"
                       :svg-path="'src/assets/log-out-03.svg'"
        />
      </div>
    </div>
  </v-navigation-drawer>

  <navbar class="side-app-bar">
    <v-app-bar-nav-icon @click.stop="drawer = !drawer">
      <img :src="'src/assets/menu-01.svg'" alt="Icon"/>
    </v-app-bar-nav-icon>
    <div class="navbar__btns">
      <custom-button
        style="margin-bottom: 5px; width: 100%;" @click="$router.push('/')"
        :svg-path="'src/assets/rows-01.svg'"
      />
      <custom-button
        style="margin-bottom: 5px; width: 100%;" @click="$router.push('/logs')"
        :svg-path="'src/assets/book-open-01.svg'"
      />
<!--      <custom-button-->
<!--        style="margin-bottom: 5px; width: 100%" @click="$router.push('/report')"-->
<!--        :svg-path="'src/assets/file-03.svg'"-->
<!--      />-->
    </div>
  </navbar>

</template>

<script>

import CustomButton from "@/components/UI/CustomButton.vue";


export default {
  components: {CustomButton},
  data() {
    return {
      drawer: false,
    }
  },
  methods: {
    toggleDrawer() {
      this.drawer = !this.drawer;
    },
  },
}
</script>

<style scoped>
.navbar {
  height: 100%;
  width: 220px;
  background-color: white;
  display: flex;
  flex-direction: column;
  padding: 20px;
}

.navbar__btns {
  margin: 80px 4px 0 0;
}

.navbar__another {
  margin-top: auto;
}

.navbar__logo{
  margin-top: 20px;
  font-size: x-large;
  font-weight: bold;
  cursor: pointer;
  display: flex;
  flex-direction: row;
  align-items: center;
}

.side-app-bar {
  height: 100vh;
  box-shadow: 2px 0 2px rgba(0, 0, 0, 0.03);
  margin: 5px;
}

</style>
