<template>
  <v-dialog v-model="localShow"
            max-width="440px">
    <v-card class="custom-card">
      <custom-block>

        <v-card-title class="text-h6" style="text-align: center;">
          Вы действительно хотите выйти?
        </v-card-title>

        <v-card-actions style="margin: 0 64px">
          <v-container>
            <v-btn
              class="action-button"
              variant="outlined"
              @click="cancelLogout">
              Отмена
            </v-btn>
            <v-btn
              class="action-button-primary action-button"
              variant="outlined"
              @click="confirmLogout">
              Выйти
            </v-btn>
          </v-container>
        </v-card-actions>

      </custom-block>
    </v-card>
  </v-dialog>
</template>

<script>
import CustomBlock from "@/components/UI/CustomBlock.vue";

export default {
  name: "LogOutDialog",
  components: { CustomBlock },
  props: {
    modelValue: {
      type: Boolean,
      required: true,
    },
  },
  computed: {
    localShow: {
      get() {
        return this.modelValue;
      }
    }
  },
  methods: {
    cancelLogout() {
      this.$emit('cancelLogout');
      this.localShow = false;
    },
    confirmLogout() {
      this.$emit('confirmLogout');
      this.localShow = false;
    },
  },
}
</script>
