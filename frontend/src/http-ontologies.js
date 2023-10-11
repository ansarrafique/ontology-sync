import axios from 'axios';

const axiosConfig = {
  baseURL: 'http://localhost:8080',
};

const axiosInstance = axios.create(axiosConfig);

export default axiosInstance;
