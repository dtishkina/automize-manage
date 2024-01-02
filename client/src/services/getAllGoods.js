import axios from "axios";

const getAllGoods =  {
  async fetch({ page, itemsPerPage }) {
    const params = new URLSearchParams({
      _page: page,
      _limit: itemsPerPage,
    });
    try {
      const response = await axios.get(`/rest/goods?${params.toString()}`);
      const total = parseInt(response.headers['x-total-count']);

      return { items: response.data, total };
    } catch (error) {
      console.error('Ошибка при получении данных: ', error);
      throw error;
    }
  }
};
//
// const getAllWarehouseFirst = {
//   async fetch({ page, itemsPerPage }) {
//     const params = new URLSearchParams({
//       _page: page,
//       _limit: itemsPerPage,
//     });
//     try {
//       const response = await axios.get(`/rest/warehouseFirst?${params.toString()}`);
//       const total = parseInt(response.headers['x-total-count']);
//
//       return { items: response.data, total };
//     } catch (error) {
//       console.error('Ошибка при получении данных из warehouseFirst: ', error);
//       throw error;
//     }
//   }
// };

const getAllData = {
  async fetch({ page, itemsPerPage }) {
    const params = new URLSearchParams({
      _page: page,
      _limit: itemsPerPage,
    });

    try {
      // Параллельное выполнение запросов к goods и warehouseFirst
      const [goodsResponse, warehouseResponse] = await Promise.all([
        axios.get(`/rest/goods?${params.toString()}`),
        axios.get(`/rest/warehouse1?${params.toString()}`)
      ]);

      // Получение данных и общего количества для каждого запроса
      const goodsData = {
        items: goodsResponse.data,
        total: parseInt(goodsResponse.headers['x-total-count'])
      };
      const warehouseData = {
        items: warehouseResponse.data,
        total: parseInt(warehouseResponse.headers['x-total-count'])
      };

      return { goodsData, warehouseData };
    } catch (error) {
      console.error('Ошибка при объединении запросов: ', error);
      throw error;
    }
  }

}


export default getAllGoods;
// export default getAllData;
