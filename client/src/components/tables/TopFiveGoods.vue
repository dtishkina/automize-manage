<template>
  <custom-block class="table">
    <v-data-table class="table element"
                  v-model:items-per-page="itemsPerPage"
                  :headers="headers"
                  :items-length="totalItems"
                  :items="serverItems"
                  @update:options="loadItems"
    >
      <template v-slot:bottom>
        <custom-button @click="generatePdf"
                       button-text="Скачать отчёт в PDF"
                       class="pdf-button">
        </custom-button>

        <v-spacer></v-spacer>
      </template>
    </v-data-table>
  </custom-block>
</template>


<script>

import CustomBlock from "@/components/UI/CustomBlock.vue";
import CustomButton from "@/components/UI/CustomButton.vue";
import getTopFiveGoods from "@/services/getTopFiveGoods";
import pdfMake from 'pdfmake/build/pdfmake';
import pdfFonts from 'pdfmake/build/vfs_fonts';
pdfMake.vfs = pdfFonts.pdfMake.vfs;


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
    generatePdf() {
      const body = this.serverItems.map(item => [
        item.id,
        item.name,
        item.priority,
        item.numberOfSales,
      ]);

      const tableHeader = ['ID', 'Наименование', 'Приоритет', 'Заявки'];
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

      pdfMake.createPdf(docDefinition).download('top5_goods.pdf');
    },
  },
}

</script>


<style scoped>

.pdf-button{
  border: 2px solid lavender;
  border-radius: 14px;
  padding-top: 8px;
  background-color:lavender;
}

</style>
