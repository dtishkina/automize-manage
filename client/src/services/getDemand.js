import axios from "axios";

const getDemand = {
  async fetch(goodId, startDate, endDate) {
    try {
      const response = await axios.get(`/rest/sales/${goodId}/demand?startDate=${startDate}&endDate=${endDate}`);
      const total = parseInt(response.headers['x-total-count']);
      return {serverItems: response.data, total}
    } catch (error) {
      console.error('Error loading demand changes:', error);
    }
  }
}

export default getDemand;
