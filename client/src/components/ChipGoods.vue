<script>
import DataTable from "@/components/tables/GoodsDataTable.vue";
import TopFiveGoods from "@/components/tables/TopFiveGoods.vue";
import SearchGoodDemand from "@/components/SearchGoodDemand.vue";

export default {
  components: {DataTable, TopFiveGoods, SearchGoodDemand},
  data: () => ({
    tags: [
      'Товары',
      'top #5',
      'Изменение спроса',
    ],
    chip: 'Товары'
  }),
  methods: {
    selectChip(chip) {
      this.chip = chip;
    },
    getComponentName(chip) {
      switch (chip) {
        case 'Товары':
          return 'DataTable';
        case 'top #5':
          return 'TopFiveGoods';
        case 'Изменение спроса':
          return 'SearchGoodDemand';
        default:
          return null;
      }
    },
  },
}
</script>

<template v-slot>
    <v-tabs class="custom-tabs"
      v-model="chip">
      <v-row>
        <v-col cols="auto">
          <v-chip-group
            class="chip-group"
            selected-class="selected-chip"
            v-model="chip"
          >
            <v-chip
              size="large"
              variant="outlined"
              class="chips"
              v-for="tag in tags"
              :key="tag"
              :value="tag"
              @click="selectChip(tag)"
            >
              {{ tag }}
            </v-chip>

          </v-chip-group>
        </v-col>
      </v-row>
    </v-tabs>

  <v-window v-model="chip">
    <component :is="getComponentName(chip)"></component>
  </v-window>

</template>

<style>

.chips{
  border: 2px solid #e6e6fa;
}

.selected-chip {
  border: 2px solid darkgray;
  background-color:  #e6e6fa;
}

.chip-group{
  align-items: flex-start;
}

.custom-tabs{
  justify-content: space-between;
  margin-bottom: 20px;
}
</style>
