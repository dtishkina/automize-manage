import axios from "axios";

const getGoodsList =  {
  async fetch() {
    try {
      const response = await axios.get(`/rest/goods`);
      return { items: response.data };
    } catch (error) {
      console.error('Ошибка при получении данных: ', error);
      throw error;
    }
  }
};

export default getGoodsList;
