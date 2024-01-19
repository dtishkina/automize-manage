import axios from "axios";

const getTopFiveGoods =  {
  async fetch({}) {
    try {
      const response = await axios.get(`/rest/goods/top5?`);
      const total = parseInt(response.headers['x-total-count']);
      return { items: response.data, total };
    } catch (error) {
      console.error('Ошибка при получении данных: ', error);
      throw error;
    }
  }
};

export default getTopFiveGoods;

