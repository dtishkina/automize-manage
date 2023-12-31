<template>
  <div>
    <custom-header
        header-text="Справочники"
        description-text="Здесь вы можете посмотреть таблицу всех товаров"
    />
    <custom-block>
      <data-table></data-table>
    </custom-block>
  </div>
</template>

<script>

import CustomBlock from "@/components/UI/CustomBlock.vue";
import CustomHeader from "@/components/UI/CustomHeader.vue";
import DataTable from "@/components/DataTable.vue";

export default {
  components: {
    CustomBlock,
    CustomHeader,
    DataTable
  },
}
</script>

<style>

</style>
