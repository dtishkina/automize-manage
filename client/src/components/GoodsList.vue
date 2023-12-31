<template>
  <div>
    <h1>Goods List</h1>
    <ul>
      <li v-for="goods in goodsList" :key="goods.id">
        {{ goods.name }} - {{ goods.price }}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      goodsList: [],
    };
  },
  mounted() {
    this.fetchGoods();
  },
  methods: {
    async fetchGoods() {
      try {
        const response = await fetch('http://your-server-url/rest/goods');
        const data = await response.json();
        this.goodsList = data;
      } catch (error) {
        console.error('Error fetching goods:', error);
      }
    },
  },
};
</script>
