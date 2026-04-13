import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { clientService } from '../services/clientService';

export default function ClientList() {
  const [clients, setClients] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    loadClients();
  }, []);

  const loadClients = async () => {
    try {
      setLoading(true);
      const response = await clientService.getAll();
      const dataArray = Array.isArray(response.data) 
        ? response.data 
        : response.data.content || response.data.data || [];
      setClients(dataArray);
      setError(null);
    } catch (err) {
      setError('Error al cargar los clientes');
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id) => {
    if (!window.confirm('¿Seguro que quieres eliminar este cliente?')) return;
    try {
      await clientService.delete(id);
      setClients(clients.filter(client => client.idClient !== id));
    } catch (err) {
      alert('Error al eliminar el cliente');
    }
  };

  if (loading) return <p>Cargando clientes...</p>;
  if (error) return <p>{error}</p>;

//   ***************************************************************

  return (
    <div className="main">
      <h2>Lista de Clientes</h2>
      <Link to="/clients/new">
        <button style={{ marginBottom: '1rem' }}>Añadir Cliente</button>
      </Link>
      <ul>
        {clients.map(client => (
          <li key={client.idClient} style={{ marginBottom: '10px' }}>
            {client.namePerson}
            <Link to={`/clients/${client.idClient}`} style={{ marginLeft: '15px', marginRight: '10px' }}>
              Ver detalles
            </Link>
            <button onClick={() => handleDelete(client.idClient)}>Eliminar</button>
          </li>
        ))}
      </ul>
    </div>
  );
}