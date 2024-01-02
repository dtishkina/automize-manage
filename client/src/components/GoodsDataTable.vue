<template>
  <custom-block class="table">
    <v-data-table class = "table element"
        v-model:items-per-page="itemsPerPage"
        :headers="headers"
        :items-length="totalItems"
        :items="serverItems"
        @update:options="loadItems"
    >

      <template v-slot:top>
        <custom-block class="search-header">
          <v-text-field class="search-bar"
                        density="compact"
                        rounded variant="outlined"
                        v-model="search.name"
                        placeholder="Найти по имени">
          </v-text-field>
          <v-spacer></v-spacer>

        </custom-block>
      </template>

      <template v-slot:item.actions="{ item }">
        <v-icon size="small" class="me-2" @click="editItem(item)">
          mdi-pencil
        </v-icon>
        <v-icon size="small" @click="deleteItem(item)"> mdi-delete </v-icon>
      </template>
      <template v-slot:no-data>
        <v-btn color="primary" @click="loadItems"> Reset </v-btn>
      </template>


    </v-data-table>
  </custom-block>
</template>

<style>

.element {
  padding: 10px;
}

.search-bar{
  width: 45%;
}

.search-header{
  height: 75px;
}

</style>

<script>
import CustomBlock from "@/components/UI/CustomBlock.vue";
import getAllGoods from "@/services/getAllGoods";
import fetchAllData from "@/services/getAllGoods";
import getAllData from "@/services/getAllGoods";

export default {
  components: {
    CustomBlock,
  },
  data() {
    return {
      itemsPerPage: 10,
      headers: [
        { title: 'ID', value: 'id', align: 'start', sortable: true },
        { title: 'Наименование', value: 'name', align: 'start', sortable: true },
        { title: 'Приоритет', value: 'priority', align: 'center', sortable: true },
        { title: 'На складе #1', value: 'goodCountFirst', align: 'center', sortable: true },
        { title: 'На складе #2', value: 'goodsCountSecond', align: 'center', sortable: true },
        { title: '', key: 'actions', sortable: false },
      ],
      serverItems: [],
      loading: true,
      totalItems: 0,
      search: { name: '', id: '' },
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
