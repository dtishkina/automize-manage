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
      type: [Number, String],
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
      axios.delete(`rest/sales/${this.itemName}`)
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
  <v-dialog v-model="show" max-width="390px">
    <v-card class="custom-card">
      <custom-block>

          <v-card-text class="text-h6" style="text-align: center;">
            Вы действительно хотите удалить заявку
            {{ itemName }} на товар {{ itemId }}?
          </v-card-text>

        <v-card-actions>
          <v-container class="action-wrapper">
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

