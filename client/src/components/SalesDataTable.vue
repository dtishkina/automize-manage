<template>
  <custom-block style="width: 800px">
    <v-data-table class="table element"
                  v-model:items-per-page="itemsPerPage"
                  :headers="headers"
                  :items-length="totalItems"
                  :items="serverItems"
                  :search="search"
                  @update:options="loadItems"
    >
      <template v-slot:top>
        <custom-block class="search-header">
        <v-text-field class="search-bar"
                      v-model="search"
                      density="compact"
                      rounded variant="outlined"
                      placeholder="Найти заявку">
        </v-text-field>

          <v-spacer></v-spacer>

          <custom-button @click="generatePdf"
                         button-text="Скачать отчёт в PDF"
                         style="border: 2px solid lavender; border-radius: 14px;
                padding-top: 8px; background-color:lavender"></custom-button>

          <v-spacer></v-spacer>

        <v-dialog v-model="dialog" max-width="460px">
          <template v-slot:activator="{ props }">
            <custom-button
              v-bind="props"
              style="border: 2px solid lavender; border-radius: 14px;
                                 padding-top: 8px; background-color:lavender;"
              button-text="Добавить заявку"
              :svg-path="'src/assets/plus.svg'">Новый товар</custom-button>
          </template>

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
                      label="Количество"
                    ></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>
                    <v-text-field
                      v-model="editedItem.createDate"
                      rounded
                      variant="outlined"
                      type="date"
                      label="Дата"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-card-text>

              <v-card-actions style="padding: 0 0 20px 56px">
                <v-container>
                  <v-spacer></v-spacer>
                  <v-btn
                    style="border: 2px solid lavender;
                   border-radius: 14px; margin: 0 8px;
                    width: 34%"
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

        <delete-dialog
          :show="dialogDelete"
          :itemName="editedItem.id"
          :itemId="editedItem.goodId"
          @close="closeDelete"
          @deleted="handleDeletion"
          @click="closeDelete"
        ></delete-dialog>
        </custom-block>
      </template>

      <template v-slot:item.actions="{ item }">
        <v-btn-group>
          <v-btn variant="text"
                 rounded="lg"
                 size="x-small"
                 @click="editItem(item)">
            <v-icon density="compact">
              <img :src="'src/assets/edit-05.svg'" alt="Icon">
            </v-icon>
          </v-btn>
          <v-btn variant="text"
                 rounded="lg"
                 size="x-small"
                 @click="deleteItem(item)">
            <v-icon density="compact">
              <img :src="'src/assets/trash-01.svg'" alt="Icon">
            </v-icon>
          </v-btn>
        </v-btn-group>
      </template>
    </v-data-table>
  </custom-block>
</template>


<script>

import CustomBlock from "@/components/UI/CustomBlock.vue";
import getAllSales from "@/services/getAllSales";
import CustomButton from "@/components/UI/CustomButton.vue";
import DeleteDialog from "@/components/DeleteSaleDialog.vue";
import axios from "axios";
import pdfMake from 'pdfmake/build/pdfmake';
import pdfFonts from 'pdfmake/build/vfs_fonts';
pdfMake.vfs = pdfFonts.pdfMake.vfs;


export default {
  components: {
    DeleteDialog,
    CustomButton,
    CustomBlock,
  },
  data() {
    return {
      search: '',
      itemsPerPage: 10,
      headers: [
        { title: 'ID заявки', value: 'id', align: 'start', sortable: true },
        { title: 'ID товара', value: 'goodId', align: 'start', sortable: true },
        { title: 'Количество', value: 'goodCount', align: 'start', sortable: true },
        { title: 'Дата', value: 'createDate', align: 'start', sortable: true },
        { title: '', key: 'actions', sortable: false },
      ],
      serverItems: [],
      loading: true,
      totalItems: 0,
      error: null,
      dialogDelete: false,
      dialog: false,
      editedIndex: -1,
      editedItem: {
        goodCount: '',
        createDate: '',
      },
      defaultItem:{
        id: '',
        goodId: '',
        goodCount: '',
        createDate: '',
      },
    };
  },
  created() {
    this.loadItems();
  },
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? 'Новая заявка' : 'Редактировать заявку'
    },
  },
  watch: {
    dialog(val) {
      val || this.close()
    },
    dialogDelete(val) {
      val || this.closeDelete()
    },
  },
  methods: {
    loadItems() {
      this.loading = true;
      getAllSales.fetch({
        page: 1,
        itemsPerPage: 10,
      }).then(({ items, total }) => {
        this.serverItems = items;
        this.totalItems = total;
        this.loading = false;
      }).catch((error) => {
        console.error('Error loading items:', error);
        this.error = error;
        this.loading = false;
      });
    },
    handleDeletion() {
      this.closeDelete();
      this.loadItems();
    },
    editItem(item) {
      this.editedIndex = this.serverItems.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },

    deleteItem(item) {
      this.editedIndex = this.serverItems.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialogDelete = true
    },

    close() {
      this.dialog = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },
    closeDelete() {
      this.dialogDelete = false
      this.show = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },

    save() {
      if (this.editedIndex > -1) {
        axios.patch('/rest/sales/' + this.editedItem.id, this.editedItem)
          .then(() => {
            this.loadItems();
          })
          .catch(error => {
            console.error('Error updating item:', error);
          });
      } else {
        axios.post('/rest/sales', this.editedItem)
          .then(response => {
            this.serverItems.push(response.data);
          })
          .catch(error => {
            console.error('Error adding new item:', error);
          });
      }
      this.close()
    },

    generatePdf() {
      const body = this.serverItems.map(item => [
        item.id,
        item.goodId,
        item.goodCount,
        item.createDate,
      ]);

      const tableHeader = ['ID товара', 'ID заявки', 'Количество', 'Дата'];
      body.unshift(tableHeader);

      const docDefinition = {
        content: [
          {
            table: {
              body: body
            }
          },
        ],
      };

      pdfMake.createPdf(docDefinition).download('sales.pdf');
    },
  },
};
</script>

<style>
.search-header{
  height: 75px;
  display: flex;
  flex-direction: row;
}
</style>
