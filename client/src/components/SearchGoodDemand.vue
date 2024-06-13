<template>
  <v-container style="padding: 0">
    <v-autocomplete class="autocomplete-component"
                    v-model="goodId"
                    label="Введите наименование, чтобы посмотреть изменение спроса"
                    :items="goodsItems"
                    item-value="id"
                    item-title="name"
                    rounded
                    variant="outlined"
    ></v-autocomplete>

    <custom-block class="choice-panel">
      <v-container>
        <v-banner
          text="Выберите промежуток времени для изменения спроса товара"
          :stacked="false">
        </v-banner>
        <v-row style="margin-top: 16px;">
          <v-col>
            <v-text-field
              v-model="startDate"
              label="Начало"
              type="date"
              required
              rounded
              variant="outlined">
            </v-text-field>
          </v-col>
          <v-col>
            <v-text-field
              v-model="endDate"
              label="Конец"
              type="date"
              required
              rounded
              variant="outlined">
            </v-text-field>
          </v-col>
        </v-row>
      </v-container>
    </custom-block>

    <custom-button @click="fetchDemandChanges"
                   button-text="Показать изменение спроса"
                   class="demand-button">
    </custom-button>

    <custom-block v-if="isTableVisible">
      <custom-block style="margin-bottom: 30px">
        <v-data-table
          v-model:items-per-page="itemsPerPage"
          :headers="headers"
          :items-length="totalItems"
          :items="serverItems"
          @update:options="fetchDemandData"
          style="width: 730px;"
          >
        </v-data-table>
      </custom-block>

      <v-spacer></v-spacer>

      <Bar :data="chartData" v-if="isTableVisible"/>

    </custom-block>

  </v-container>
</template>

<script >
import getGoodsList from "@/services/getGoodsList";
import CustomButton from "@/components/UI/CustomButton.vue";
import CustomBlock from "@/components/UI/CustomBlock.vue";
import getDemand from "@/services/getDemand";
import { Bar } from 'vue-chartjs'
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from 'chart.js'

ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale)

export default {
  components: {CustomBlock, CustomButton, Bar},
  data(){
    return{
      goodsItems: [],
      startDate: '',
      endDate: '',
      goodId: null,
      itemsPerPage: this.totalItems,
      totalItems: 0,
      headers: [
        { title: 'Дата заявки', value: 'createDate', align: 'center', sortable: false },
        { title: 'Количество товара', value: 'goodCount', align: 'center', sortable: false },
      ],
      serverItems: [],
      error: null,
      isTableVisible: false,
      chartData: {
        labels: [],
        datasets: [],
      },
    }
  },
  created() {
    this.fetchGoods();
  },
  computed: {
    chartData() {
      if (!this.serverItems || !Array.isArray(this.serverItems)) {
        return {};
      }

      return {
        labels: this.serverItems.map(item => item.createDate),
        datasets: [
          {
            label: 'Изменение спроса',
            backgroundColor: 'lavender',
            data: this.serverItems.map(item => item.goodCount),
          },
        ],
      };
    },
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
        const formattedStartDate = this.startDate.split('T')[0];
        const formattedEndDate = this.endDate.split('T')[0];
        this.fetchDemandData(this.goodId, formattedStartDate, formattedEndDate);
      }
    },
    fetchDemandData(goodId, startDate, endDate) {
      getDemand.fetch(goodId, startDate, endDate).then(response =>{
        if (response) {
          this.serverItems = response.serverItems;
          this.isTableVisible = true;
        }
      }).catch((error) => {
        console.error('Error loading demand:', error);
      });
    }
  }
}
</script>

<style>

.autocomplete-component{
  margin-top: 14px;
  width: 800px;
}

.choice-panel{
  padding: 0 10px !important;
  justify-content: space-between;
}

.demand-button{
  margin: 20px auto;
  border: 2px solid lavender;
  border-radius: 14px;
  background: lavender;
}
</style>
