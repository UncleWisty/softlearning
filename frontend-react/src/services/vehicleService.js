import apiClient from './apiClient';

const VEHICLES_ENDPOINT = '/vehicles';

export const vehicleService = {
  getAll: () => apiClient.get(VEHICLES_ENDPOINT),
  getByMatricula: (matricula) => apiClient.get(`${VEHICLES_ENDPOINT}/${matricula}`),
  create: (data) => apiClient.post(VEHICLES_ENDPOINT, data),
  update: (matricula, data) => apiClient.put(`${VEHICLES_ENDPOINT}/${matricula}`, data),
  delete: (matricula) => apiClient.delete(`${VEHICLES_ENDPOINT}/${matricula}`),
};

