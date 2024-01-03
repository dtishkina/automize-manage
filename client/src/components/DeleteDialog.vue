<script>

import {defineComponent} from "vue";
import CustomBlock from "@/components/UI/CustomBlock.vue";
import axios from "axios";

export default defineComponent({
  components: {CustomBlock},
  props: {
    show: {
      type: Boolean,
      required: true
    },
    itemName: {
      type: String,
      required: true
    },
    itemId: {
      type: [Number],
      required: true
    },
  },
  methods: {
    cancelDelete() {
      this.$emit('close');
    },
    confirmDelete() {
      console.log(this.itemId)
      axios.delete(`rest/goods/${this.itemId}`)
        .then(() => {
          this.$emit('deleted');
        })
        .catch(error => {
          console.error('Error deleting the item:', error);
        });
    },
  }
})
</script>

<template>
  <v-dialog v-model="show" max-width="330px">
    <v-card style="border-radius: 20px">
      <custom-block>

          <v-card-title class="text-h6" style="text-align: center;">
            Хотите удалить товар
            <v-card-subtitle class="text-h6" style="padding: 5px">
              {{ itemName }}?
            </v-card-subtitle>
          </v-card-title>

        <v-card-actions style="margin: 0 17px">
          <v-container>
            <v-btn
              style="border: 2px solid lavender; border-radius: 14px; margin: 0 8px;"
              variant="outlined"
              @click="cancelDelete">
              Отмена
            </v-btn>
            <v-btn
              style="border: 2px solid lavender; border-radius: 14px; background-color:lavender; margin: 0 8px;"
              variant="outlined"
              @click="confirmDelete">
              Удалить
            </v-btn>
          </v-container>
        </v-card-actions>

      </custom-block>
    </v-card>
  </v-dialog>
</template>

<style>

</style>
