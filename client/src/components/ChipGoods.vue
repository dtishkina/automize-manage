<script>
import DataTable from "@/components/GoodsDataTable.vue";
import TopFiveGoods from "@/components/TopFiveGoods.vue";

export default {
  components: {DataTable, TopFiveGoods},
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
          return null;
        default:
          return null;
      }
    },
  },
}
</script>

<template v-slot>
    <v-tabs justify="space-between" style="margin-bottom: 20px;"
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

</style>
