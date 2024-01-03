<template>
  <v-container style="padding: 0">
    <v-autocomplete style="margin-top: 14px;"
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
          text="Можете выбрать промежуток времени для спроса товара или продолжить"
          :stacked="false">
        </v-banner>
        <v-row justify="space-between" style="margin-top: 16px;">
          <v-col>
            <v-text-field label="Начало" type="date" required rounded variant="outlined"></v-text-field>
          </v-col>
          <v-col>
            <v-text-field label="Конец" type="date" required rounded variant="outlined"></v-text-field>
          </v-col>
        </v-row>
      </v-container>
    </custom-block>

    <custom-button button-text="Показать изменение спроса"
                   style="margin: 20px auto; border: 2px solid lavender; border-radius: 14px; background: lavender"></custom-button>
  </v-container>
</template>

<script >
import getGoodsList from "@/services/getGoodsList";
import CustomButton from "@/components/UI/CustomButton.vue";
import CustomBlock from "@/components/UI/CustomBlock.vue";

export default {
  components: {CustomBlock, CustomButton},
  data(){
    return{
      goodsItems: []
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
    }
  }
}
</script>

<style>

</style>
