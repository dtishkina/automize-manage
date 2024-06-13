<template>
  <v-dialog v-model="dialog"
            max-width="600px">
    <v-card class="custom-card">
      <custom-block class="dialog-custom-block">

        <v-card-title class="dialog-title">
          <span class="text-h5">{{ formTitle }}</span>
        </v-card-title>

        <v-card-text class="dialog-text">
          <v-row>
            <v-col>
              <v-text-field
                label="Наименование"
                rounded
                variant="outlined"
                v-model="editedItem.name">
              </v-text-field>
            </v-col>
            <v-col>
              <v-text-field
                v-model="editedItem.priority"
                rounded
                variant="outlined"
                label="Приоритет">
              </v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-text-field
                label="Количество на складе #1"
                rounded
                variant="outlined"
                v-model="editedItem.goodCountFirst">
              </v-text-field>
            </v-col>
            <v-col>
              <v-text-field
                label="Количество на складе #2"
                rounded
                variant="outlined"
                v-model="editedItem.goodCountSecond">
              </v-text-field>
            </v-col>
          </v-row>
        </v-card-text>

        <v-card-actions style="padding: 0 0 20px 100px">
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
  name: "EditGoodDialog",
  components: { CustomBlock },
  props: {
    dialog: {
      type: Boolean,
      required: true
    },
    editedIndex: {
      type: Number,
      required: true
    },
    editedItem: {
      type: Object,
      required: true
    }
  },
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? 'Новый товар' : 'Редактировать товар';
    }
  },
  methods: {
    close() {
      this.$emit('close');
    },
    save() {
      this.$emit('save');
    }
  }
});
</script>

