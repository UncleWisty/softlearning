import apiClient from './apiClient';

const BOOKS_ENDPOINT = '/books';

export const bookService = {
  getAll: () => apiClient.get(BOOKS_ENDPOINT),
  getById: (id) => apiClient.get(`${BOOKS_ENDPOINT}/${id}`),
  create: (data) => apiClient.post(BOOKS_ENDPOINT, data),
  update: (id, data) => apiClient.put(`${BOOKS_ENDPOINT}/${id}`, data),
  delete: (id) => apiClient.delete(`${BOOKS_ENDPOINT}/${id}`),
};
