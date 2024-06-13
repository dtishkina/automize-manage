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
      type: [Number, String],
      required: true
    },
  },

  methods: {
    cancelDelete() {
      this.$emit('closeDelete');
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
  <v-dialog v-model="show" max-width="360px">
    <v-card class="custom-card">
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
              class="action-button"
              variant="outlined"
              @click="cancelDelete">
              Отмена
            </v-btn>
            <v-btn
              class="action-button action-button-primary"
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
