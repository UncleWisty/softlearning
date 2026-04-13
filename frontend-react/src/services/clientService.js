import apiClient from './apiClient';

const CLIENTS_ENDPOINT = '/clients';

export const clientService = {
  getAll: () => apiClient.get(CLIENTS_ENDPOINT),
  getById: (id) => apiClient.get(`${CLIENTS_ENDPOINT}/${id}`),
  create: (data) => apiClient.post(CLIENTS_ENDPOINT, data),
  update: (id, data) => apiClient.put(`${CLIENTS_ENDPOINT}/${id}`, data),
  delete: (id) => apiClient.delete(`${CLIENTS_ENDPOINT}/${id}`),
};
