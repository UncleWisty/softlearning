import { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import { vehicleService } from '../services/vehicleService';

export default function ClientDetail() {
  const { matricula } = useParams();
  const [vehicle, setVehicle] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchVehicle = async () => {
      try {
        const response = await vehicleService.getByMatricula(matricula);
        setVehicle(response.data);
      } catch (err) {
        setError('Error al cargar el vehiculo');
      }
    };
    fetchVehicle();
  }, [matricula]);

  if (error) return <p>{error}</p>;
  if (!vehicle) return <p>Cargando...</p>;

  return (
    <div className="main">
      <h2>Detalles del Vehiculo</h2>
      <p><strong>Matricula:</strong> {vehicle.matricula}</p>
      <p><strong>Marca:</strong> {vehicle.marca}</p>
      <p><strong>Modelo:</strong> {vehicle.modelo}</p>
      <p><strong>Caracteristicas:</strong> {vehicle.caracteristicas}</p>
      <p><strong>Carga:</strong> {vehicle.carga}</p>
      <p><strong>Capacidad:</strong> {vehicle.capacidad}</p>
      <p><strong>Adquisicion:</strong> {vehicle.adquisicion}</p>
      <p><strong>Revision:</strong> {vehicle.revision}</p>
      <Link to="/vehicles">
        <button style={{ marginTop: '1rem' }}>Volver</button>
      </Link>
    </div>
  );
}