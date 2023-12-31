<template>
  <div>
    <custom-header
        header-text="Журналы"
        description-text="Здесь вы можете посмотреть все заявки на товары"
    />
    <custom-block>
    </custom-block>
  </div>
</template>


<script>
import CustomBlock from "@/components/UI/CustomBlock.vue";
import CustomHeader from "@/components/UI/CustomHeader.vue";

export default {
  components: {CustomHeader, CustomBlock}
}
</script>

<style scoped>

</style>
