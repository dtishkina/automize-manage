<template>
  <v-container style="padding: 0">
    <v-autocomplete style="margin-top: 14px;"
                    v-model="goodId"
                    label="Введите наименование, чтобы посмотреть изменение спроса"
                    :items="goodsItems"
                    item-value="id"
                    item-title="name"
                    rounded=""
                    variant="outlined"
    ></v-autocomplete>

    <custom-block style="padding: 0 10px">
      <v-container>
        <v-banner
          text="Выберите промежуток времени для изменения спроса товара"
          :stacked="false">
        </v-banner>
        <v-row justify="space-between" style="margin-top: 16px;">
          <v-col>
            <v-text-field v-model="startDate" label="Начало" type="date" required rounded variant="outlined"></v-text-field>
          </v-col>
          <v-col>
            <v-text-field v-model="endDate" label="Конец" type="date" required rounded variant="outlined"></v-text-field>
          </v-col>
        </v-row>
      </v-container>
    </custom-block>

    <custom-button @click="fetchDemandChanges"
                   button-text="Показать изменение спроса"
                   style="margin: 20px auto; border: 2px solid lavender; border-radius: 14px; background: lavender">

    </custom-button>

    <custom-block>
      <v-data-table
        v-model:items-per-page="itemsPerPage"
        :headers="headers"
        :items-length="totalItems"
        :items="serverItems"
        :loading="loading">
        >
      </v-data-table>
    </custom-block>
  </v-container>
</template>

<script >
import getGoodsList from "@/services/getGoodsList";
import CustomButton from "@/components/UI/CustomButton.vue";
import CustomBlock from "@/components/UI/CustomBlock.vue";
import axios from "axios";

export default {
  components: {CustomBlock, CustomButton},
  data(){
    return{
      goodsItems: [],
      startDate: '',
      endDate: '',
      goodId: null,
      itemsPerPage: 10,
      headers: [
        { title: 'Дата заявки', value: 'createDate', align: 'start', sortable: false },
        { title: 'Количество товара', value: 'goodCount', align: 'start', sortable: false },
      ],
      serverItems: [],
      loading: true,
      totalItems: 0,
      error: null,
    }
  },
  mounted() {
    this.fetchGoods();
  },
  methods: {
    fetchGoods() {
      getGoodsList.fetch().then(({ items }) => {
        this.goodsItems = items
      }).catch((error) => {
        console.error('Error loading items:', error);
      })
    },
    fetchDemandChanges() {
      if (this.goodId && this.startDate && this.endDate) {
        this.fetchDemandData(this.goodId, this.startDate, this.endDate);
      }
    },
    async fetchDemandData(goodId, startDate, endDate) {
      const formattedStartDate = startDate.split('T')[0];
      const formattedEndDate = endDate.split('T')[0];
      try {
        const response = await axios.get(`/rest/sales/${goodId}/demand?startDate=${formattedStartDate}&endDate=${formattedEndDate}`);
        this.serverItems = response.data;
        this.totalItems = response.data.length;
      } catch (error) {
        console.error('Error loading demand changes:', error);
      }
    }
  }
}
</script>

<style>

</style>
