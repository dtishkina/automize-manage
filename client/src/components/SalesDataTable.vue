<template>
  <custom-block class="table">
    <v-data-table class="table element"
                  v-model:items-per-page="itemsPerPage"
                  :headers="headers"
                  :items-length="totalItems"
                  :items="serverItems"
                  @update:options="loadItems"
    >

      <template v-slot:item.actions="{ item }">
        <v-icon size="small" class="me-2" @click="editItem(item)">
          mdi-pencil
        </v-icon>
        <v-icon size="small" @click="deleteItem(item)"> mdi-delete</v-icon>
      </template>
      <template v-slot:no-data>
        <v-btn color="primary" @click="loadItems"> Reset</v-btn>
      </template>
    </v-data-table>
  </custom-block>
</template>


<script>

import CustomBlock from "@/components/UI/CustomBlock.vue";
import getAllSales from "@/services/getAllSales";

export default {
  components: {
    CustomBlock,
  },
  data() {
    return {
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
    };
  },
  created() {
    this.loadItems();
  },
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? 'New Item' : 'Edit Item'
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
    editItem(item) {
      this.editedIndex = this.desserts.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },

    deleteItem(item) {
      this.editedIndex = this.desserts.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialogDelete = true
    },

    deleteItemConfirm() {
      this.desserts.splice(this.editedIndex, 1)
      this.closeDelete()
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
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },

    save() {
      if (this.editedIndex > -1) {
        Object.assign(this.desserts[this.editedIndex], this.editedItem)
      } else {
        this.desserts.push(this.editedItem)
      }
      this.close()
    },
  },
};
</script>

<style>

</style>
