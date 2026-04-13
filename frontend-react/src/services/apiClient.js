import axios from 'axios';

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

const apiClient = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

apiClient.interceptors.response.use(
  response => response,
  error => {
    const message = error.response?.data || error.message || 'Error en la solicitud';
    return Promise.reject(new Error(message));
  }
);

export default apiClient;
