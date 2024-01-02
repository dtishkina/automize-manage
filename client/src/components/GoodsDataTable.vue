<template>
  <custom-block class="table">
    <v-data-table class = "table element"
        v-model:items-per-page="itemsPerPage"
        :headers="headers"
        :items-length="totalItems"
        :items="serverItems"
                  show-select=""
        @update:options="loadItems"
    >

      <template v-slot:top>
        <custom-block class="search-header">
          <v-text-field class="search-bar"
                        density="compact"
                        rounded variant="outlined"
                        v-model="search.name"
                        placeholder="Найти по наименованию">
          </v-text-field>

          <v-spacer></v-spacer>

          <custom-button
            style="border: 2px solid lavender; border-radius: 14px;
                   padding: 0.7em; background-color:lavender"
            button-text="Новый товар"
            :svg-path="'src/assets/plus.svg'"></custom-button>
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
            <img  :src="'src/assets/trash-01.svg'" alt="Icon">
          </v-icon>

        </v-btn>
        </v-btn-group>
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
  display: flex;
  flex-direction: row;
}

</style>

<script>
import CustomBlock from "@/components/UI/CustomBlock.vue";
import getAllGoods from "@/services/getAllGoods";
import CustomButton from "@/components/UI/CustomButton.vue";
import getAllData from "@/services/getAllGoods";

export default {
  components: {
    CustomButton,
    CustomBlock,
  },
  data() {
    return {
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
      this.editedIndex = this.serverItems.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },

    deleteItem(item) {
      this.editedIndex = this.serverItems.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialogDelete = true
    },

    deleteItemConfirm() {
      this.serverItems.splice(this.editedIndex, 1)
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
        Object.assign(this.serverItems[this.editedIndex], this.editedItem)
      } else {
        this.serverItems.push(this.editedItem)
      }
      this.close()
    },
  },
};

</script>
