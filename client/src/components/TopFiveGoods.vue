<template>
  <custom-block class="table">
    <v-data-table class="table element"
                  v-model:items-per-page="itemsPerPage"
                  :headers="headers"
                  :items-length="totalItems"
                  :items="serverItems"
                  @update:options="loadItems"
    >
    </v-data-table>
  </custom-block>
</template>


<script>

import CustomBlock from "@/components/UI/CustomBlock.vue";
import CustomButton from "@/components/UI/CustomButton.vue";
import getTopFiveGoods from "@/services/getTopFiveGoods";

export default {
  components: {
    CustomButton,
    CustomBlock,
  },
  data() {
    return {
      itemsPerPage: 5,
      headers: [
        {title: 'ID', value: 'id', align: 'start', sortable: true},
        {title: 'Наименование', value: 'name', align: 'start', sortable: true},
        {title: 'Приоритет', value: 'priority', align: 'center', sortable: true},
        {title: 'Заявки', value: 'numberOfSales', align: 'center', sortable: true},
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
  methods: {
    loadItems() {
      this.loading = true;
      getTopFiveGoods.fetch({
        page: 1,
        itemsPerPage: 10,
      }).then(({items, total}) => {
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
}

</script>


<style scoped>

</style>
