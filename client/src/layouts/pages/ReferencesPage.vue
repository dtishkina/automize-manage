<template>
  <div class="references-page">
    <custom-header
        header-text="Справочники"
        description-text="Здесь вы можете посмотреть информацию о товарах"
    />
    <chip-goods></chip-goods>

  </div>
</template>

<script>

import CustomHeader from "@/components/UI/CustomHeader.vue";
import ChipGoods from "@/components/ChipGoods.vue";

export default {
  components: {
    CustomHeader,
    ChipGoods,
  },
}
</script>
