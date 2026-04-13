import { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import { clientService } from '../services/clientService';

export default function ClientDetail() {
  const { id } = useParams();
  const [client, setClient] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchClient = async () => {
      try {
        const response = await clientService.getById(id);
        setClient(response.data);
      } catch (err) {
        setError('Error al cargar el cliente');
      }
    };
    fetchClient();
  }, [id]);

  if (error) return <p>{error}</p>;
  if (!client) return <p>Cargando...</p>;

  return (
    <div className="main">
      <h2>Detalles del Cliente</h2>
      <p><strong>ID Cliente:</strong> {client.idClient}</p>
      <p><strong>Nombre:</strong> {client.namePerson}</p>
      <p><strong>Email:</strong> {client.email}</p>
      <p><strong>Teléfono:</strong> {client.phone}</p>
      <p><strong>Dirección:</strong> {client.adress}</p>
      <p><strong>DNI/NIF:</strong> {client.idPerson}</p>
      <Link to="/clients">
        <button style={{ marginTop: '1rem' }}>Volver</button>
      </Link>
    </div>
  );
}