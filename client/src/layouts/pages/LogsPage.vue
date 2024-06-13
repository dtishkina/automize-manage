<template>
  <div>
    <custom-header
        header-text="Журналы"
        description-text="Здесь вы можете посмотреть все заявки на товары"
    />
    <sales-data-table></sales-data-table>
  </div>
</template>


<script>
import CustomHeader from "@/components/UI/CustomHeader.vue";
import SalesDataTable from "@/components/tables/SalesDataTable.vue";

export default {
  components: {SalesDataTable, CustomHeader}
}
</script>
