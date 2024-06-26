import axios from "axios";

const getAllGoods =  {
  async fetch({ page, itemsPerPage }) {
    const params = new URLSearchParams({
      _page: page,
      _limit: itemsPerPage,
    });
    try {
      const response = await axios.get(`/rest/goods/all_with_warehouse?${params.toString()}`);
      const total = parseInt(response.headers['x-total-count']);

      return { items: response.data, total };
    } catch (error) {
      console.error('Ошибка при получении данных: ', error);
      throw error;
    }
  }
};

export default getAllGoods;
