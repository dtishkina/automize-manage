<template>
  <v-data-table-server class = "element"
      v-model:items-per-page="itemsPerPage"
      :headers="headers"
      :items-length="totalItems"
      :items="serverItems"
      :loading="loading"
      item-value="name"
      @update:options="loadItems"
  >
    <template v-slot:top>
      <custom-block class="element search-header">
        <v-text-field class="search-header-item"
                      rounded variant="outlined"
                      v-model="search.id"
                      placeholder="Найти по ID"
                      >
        </v-text-field>
        <v-text-field class="search-header-item"
                      rounded variant="outlined"
                      v-model="search.name"
                      placeholder="Найти по имени">

        </v-text-field>
        <v-text-field class="search-header-item"
                      rounded variant="outlined"
                      v-model="search.priority"
                      placeholder="Найти по приоритету"
                      >
        </v-text-field>
      </custom-block>
    </template>
  </v-data-table-server>
</template>

<style>

.element {
  padding: 20px;
}

.search-header{
  display: flex;
  justify-content: space-between;
}

.search-header-item{
  margin: 10px 10px 0 10px;
}

</style>

<script>
import axios from "axios";
import CustomBlock from "@/components/UI/CustomBlock.vue";

const FakeAPI = {
  async fetch({ page, itemsPerPage, sortBy, search }) {
    const params = new URLSearchParams({
      _page: page,
      _limit: itemsPerPage,
      _sort: sortBy.length ? `${sortBy[0].key},${sortBy[0].order}` : '',
      name: search.name || '',
      id: search.id ? search.id.toString() : '',
    });
    try {
      const response = await axios.get(`/rest/goods?${params.toString()}`);
      const total = parseInt(response.headers['x-total-count']);

      return { items: response.data, total };
    } catch (error) {
      console.error('Ошибка при получении данных: ', error);
      throw error;
    }
  },
};

export default {
  components: {CustomBlock},
  data() {
    return {
      itemsPerPage: 5,
      headers: [
        { title: 'ID', value: 'id', align: 'start', sortable: true },
        { title: 'Наименование', value: 'name', align: 'start', sortable: true },
        { title: 'Приоритет', value: 'priority', align: 'center', sortable: true },
      ],
      serverItems: [],
      loading: true,
      totalItems: 0,
      search: { name: '', id: '' },
      error: null,
    };
  },
  watch: {
    'search.name': function() {
      this.loadItems();
    },
    'search.id': function() {
      this.loadItems();
    },
  },
  created() {
    this.loadItems();
  },
  methods: {
    loadItems() {
      this.loading = true;

      FakeAPI.fetch({
        page: 1,
        itemsPerPage: this.itemsPerPage,
        sortBy: [],
        search: { name: this.search.name, id: this.search.id },
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
  },
};

</script>
