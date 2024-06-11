<template>
  <custom-block class="table">
    <v-data-table class="table element"
                  v-model:items-per-page="itemsPerPage"
                  :headers="headers"
                  :items-length="totalItems"
                  :items="serverItems"
                  :search="search"
                  @update:options="loadItems">
      <template v-slot:top>
        <custom-block class="search-header">
          <v-text-field class="search-bar"
                        v-model="search"
                        density="compact"
                        rounded
                        variant="outlined"
                        placeholder="Найти товар">
          </v-text-field>
          <v-spacer></v-spacer>
          <custom-button @click="generatePdf"
                         button-text="Скачать отчёт в PDF"
                         style="border: 2px solid lavender; border-radius: 14px;
                                padding-top: 8px; background-color:lavender">
          </custom-button>
          <v-spacer></v-spacer>
          <custom-button @click="openDialog"
                         style="border: 2px solid lavender; border-radius: 14px;
                                padding-top: 8px; background-color:lavender"
                         button-text="Новый товар"
                         :svg-path="'src/assets/plus.svg'">Новый товар
          </custom-button>
          <delete-dialog
            :show="dialogDelete"
            :itemName="editedItem.name"
            :itemId="editedItem.id"
            @close="closeDelete"
            @deleted="handleDeletion"
            @click="closeDelete">
          </delete-dialog>
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
      <template v-slot:no-data>
        <v-btn color="primary" @click="loadItems">Reset</v-btn>
      </template>
    </v-data-table>
    <edit-good-dialog
      :dialog="dialog"
      :editedIndex="editedIndex"
      :editedItem="editedItem"
      @close="close"
      @save="save">
    </edit-good-dialog>
  </custom-block>
</template>

<style>
.element {
  padding: 10px;
}
.search-bar {
  width: 45%;
}
.search-header {
  height: 75px;
  display: flex;
  flex-direction: row;
}
</style>

<script>
import CustomBlock from "@/components/UI/CustomBlock.vue";
import getAllGoods from "@/services/getAllGoods";
import CustomButton from "@/components/UI/CustomButton.vue";
import DeleteDialog from "@/components/dialogs/DeleteGoodDialog.vue";
import EditGoodDialog from "@/components/dialogs/EditGoodDialog.vue";
import axios from "axios";
import pdfMake from 'pdfmake/build/pdfmake';
import pdfFonts from 'pdfmake/build/vfs_fonts';
pdfMake.vfs = pdfFonts.pdfMake.vfs;

export default {
  components: {
    DeleteDialog,
    CustomButton,
    CustomBlock,
    EditGoodDialog,
  },
  data() {
    return {
      search: '',
      itemsPerPage: 10,
      headers: [
        { title: 'ID', value: 'id', align: 'start', sortable: true },
        { title: 'Наименование', value: 'name', align: 'start', sortable: true },
        { title: 'Приоритет', value: 'priority', align: 'center', sortable: true },
        { title: 'На складе #1', key: 'goodCountFirst', align: 'center', sortable: true },
        { title: 'На складе #2', key: 'goodCountSecond', align: 'center', sortable: true },
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
        id: '',
        name: '',
        priority: '',
        goodCountFirst: '',
        goodCountSecond: '',
      },
      defaultItem: {
        id: '',
        name: '',
        priority: '',
        goodCountFirst: 0,
        goodCountSecond: 0,
      },
    };
  },
  created() {
    this.loadItems();
  },
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? 'Добавить товар' : 'Редактировать товар';
    },
  },
  watch: {
    dialog(val) {
      val || this.close();
    },
    dialogDelete(val) {
      val || this.closeDelete();
    },
  },
  methods: {
    loadItems() {
      this.loading = true;
      getAllGoods.fetch({
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
      this.editedIndex = this.serverItems.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },
    deleteItem(item) {
      this.editedIndex = this.serverItems.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialogDelete = true;
    },
    openDialog() {
      this.editedIndex = -1;
      this.editedItem = Object.assign({}, this.defaultItem);
      this.dialog = true;
    },
    close() {
      this.dialog = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },
    closeDelete() {
      this.dialogDelete = false;
      this.show = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },
    save() {
      if (this.editedIndex > -1) {
        axios.patch('/rest/goods/' + this.editedItem.id, this.editedItem)
          .then(() => {
            this.loadItems();
          })
          .catch(error => {
            console.error('Error updating item:', error);
          });
      } else {
        axios.post('/rest/goods', this.editedItem)
          .then(response => {
            this.serverItems.push(response.data);
          })
          .catch(error => {
            console.error('Error adding new item:', error);
          });
      }
      this.close();
    },
    generatePdf() {
      const body = this.serverItems.map(item => [
        item.id,
        item.name,
        item.priority,
        item.goodCountFirst,
        item.goodCountSecond,
      ]);
      const tableHeader = ['ID', 'Наименование', 'Приоритет', 'На складе #1', 'На складе #2'];
      body.unshift(tableHeader);
      const docDefinition = {
        content: [
          {
            table: {
              body: body,
            },
          },
        ],
      };
      pdfMake.createPdf(docDefinition).download('all_goods.pdf');
    },
  },
};
</script>
