<template>
  <v-dialog v-model="dialog" max-width="460px">
    <v-card style="border-radius: 20px;">
      <custom-block style="padding: 0 30px">
        <v-card-title style="margin: 20px 0;">
          <span class="text-h5">{{ formTitle }}</span>
        </v-card-title>
        <v-card-text style="padding: 0 10px">
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
              style="border: 2px solid lavender;
                     border-radius: 14px; margin: 0 8px; width: 34%"
              variant="outlined"
              size="large"
              @click="close">
              Отмена
            </v-btn>
            <v-btn
              style="border: 2px solid lavender; border-radius: 14px; background-color:lavender; margin: 0 8px;"
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

<style scoped>
</style>
