<template>
  <custom-block>
    <v-data-table
                  v-model:items-per-page="itemsPerPage"
                  :headers="headers"
                  :items-length="totalItems"
                  :items="serverItems"
                  @update:options="updateItems">
    >
    </v-data-table>
  </custom-block>
</template>

<script>

import CustomBlock from "@/components/UI/CustomBlock.vue";

export default {
  components: {CustomBlock},
  props: {
    goodId: {
      type: Number,
      required: true
    },
    startDate: {
      type: [Date, String]
    },
    endDate: {
      type: [Date, String],
    },
  },
  data() {
    return {
      itemsPerPage: 10,
      headers: [
        { title: 'Дата заявки', value: 'createDate', align: 'start', sortable: false },
        { title: 'Количество товара', value: 'goodId', align: 'start', sortable: false },
      ],
      serverItems: [],
      loading: true,
      totalItems: 0,
      error: null,
    };
  },
  methods:{
    updateItems(items) {
      this.serverItems = items;
      this.totalItems = items.length;
    },
  }
}
</script>

<style scoped>

</style>
