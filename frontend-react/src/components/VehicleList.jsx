import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { vehicleService } from '../services/vehicleService';

export default function VehicleList() {
  const [vehicles, setVehicles] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  // const [show, setShow] = useState(true);

  useEffect(() => {
    loadVehicles();
  }, []);
  
  const loadVehicles = async () => {
    try {
      setLoading(true);
      const response = await vehicleService.getAll();
      const dataArray = Array.isArray(response.data) 
      ? response.data 
      : response.data.content || response.data.data || [];
      setVehicles(dataArray);
      setError(null);
    } catch (err) {
      setError('Error al cargar los vehiculos');
    } finally {
      setLoading(false);
      // setShow(true);
    }
  };
  
  const handleDelete = async (matricula) => {
    if (!window.confirm('¿Seguro que quieres eliminar este vehiculo?')) return;
    try {
      await vehicleService.delete(matricula);
      setVehicles(vehicles.filter(vehicle => vehicle.matricula !== matricula));
    } catch (err) {
      alert('Error al eliminar el Vehiculo');
    }
  };
  
  if (loading) return <p>Cargando vehiculos...</p>;
  if (error) return <p>{error}</p>;
  //no me acuerdo de hacer los botones que mostraban y ocultaban :( !!!!!!!!
  // if (show) return <button onClick={setShow(false)}>Cargar vehiculos</button>

//   ***************************************************************

  return (
    <div className="main">
      <h2>Lista de Vehiculos</h2>
      <Link to="/vehicles/new">
        <button style={{ marginBottom: '1rem' }}>Añadir Vehiculo</button>
      </Link>
      <ul>
        {vehicles.map(vehicle => (
          <li key={vehicle.matricula} style={{ marginBottom: '10px' }}>
            {vehicle.matricula}
            <Link to={`/vehicles/${vehicle.matricula}`} style={{ marginLeft: '15px', marginRight: '10px' }}>
              Ver detalles
            </Link>
            <button onClick={() => handleDelete(vehicle.matricula)}>Eliminar</button>
          </li>
        ))}
      </ul>
    </div>
  );
}