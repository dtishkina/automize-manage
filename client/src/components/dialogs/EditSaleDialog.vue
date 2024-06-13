<template>
  <v-dialog v-model="dialog"
            max-width="460px">
    <v-card class="custom-card">
      <custom-block class="dialog-custom-block">

        <v-card-title class="dialog-title">
          <span class="text-h5">{{ formTitle }}</span>
        </v-card-title>

        <v-card-text class="dialog-text">
          <v-row>
            <v-col>
              <v-text-field
                label="ID товара"
                rounded
                variant="outlined"
                v-model="editedItem.goodId">
              </v-text-field>
            </v-col>
            <v-col>
              <v-text-field
                v-model="editedItem.goodCount"
                rounded
                variant="outlined"
                label="Количество">
              </v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-text-field
                v-model="editedItem.createDate"
                rounded
                variant="outlined"
                type="date"
                label="Дата">
              </v-text-field>
            </v-col>
          </v-row>
        </v-card-text>

        <v-card-actions style="padding: 0 0 20px 56px">
          <v-container>
            <v-spacer></v-spacer>
            <v-btn
              class="action-button"
              variant="outlined"
              size="large"
              @click="close">
              Отмена
            </v-btn>
            <v-btn
              class="action-button-primary action-button"
              variant="outlined"
              size="large"
              @click="save">
              Сохранить
            </v-btn>
          </v-container>
        </v-card-actions>

      </custom-block>
    </v-card>
  </v-dialog>
</template>

<script>
import { defineComponent } from "vue";
import CustomBlock from "@/components/UI/CustomBlock.vue";

export default defineComponent({
  name: "EditSaleDialog",
  components: { CustomBlock },
  props: {
    dialog: Boolean,
    editedIndex: Number,
    editedItem: {
      type: Object,
      required: true,
    },
  },
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? 'Новая заявка' : 'Редактировать заявку';
    },
  },
  methods: {
    close() {
      this.$emit('close');
    },
    save() {
      this.$emit('save');
    },
  },
});
</script>
