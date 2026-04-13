import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { vehicleService } from '../services/vehicleService';
import './VehicleForm.css';

export default function VehicleForm() {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    matricula: '',
    marca: '',
    modelo: '',
    caracteristicas: '',
    carga: 0,
    capacidad: 0,
    adquisicion: '01-02-2026, 12:00:00',
    revision: '01-02-2028, 12:00:00'
  });
  const [error, setError] = useState(null);

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.type === 'number' ? Number(e.target.value) : e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
        await vehicleService.create(formData);
        navigate('/vehicles');
    } catch (err) {
        setError(err.message);
    }
    };

  return (
    <div className="form-container">
      <h2>Añadir Vehiculo</h2>
      {error && <p className="error-message">{error}</p>}
      <form className="vehicle-form" onSubmit={handleSubmit}>
        <input name="matricula" type="text" placeholder="Matricula" onChange={handleChange} required />
        <input name="marca" placeholder="Marca" onChange={handleChange} required />
        <input name="modelo" placeholder="Modelo" onChange={handleChange} required />
        <input name="caracteristicas" type="text" placeholder="Caracteristicas" onChange={handleChange}/>
        <input name="carga" placeholder="Carga" onChange={handleChange} required />
        <input name="capacidad" placeholder="Capacidad" onChange={handleChange} required />
        <label htmlFor="adquisicion">Fecha Adquisicion</label>
        <input name="adquisicion" placeholder="dd-mm-yyy, hh:mm:ss" onChange={handleChange}/>
        <label htmlFor="adquisicion">Fecha Revision</label>
        <input name="revision" placeholder="dd-mm-yyy, hh:mm:ss" onChange={handleChange}/>
        <button type="submit">Guardar</button>
      </form>
    </div>
  );
}