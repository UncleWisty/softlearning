import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { clientService } from '../services/clientService';
import './ClientForm.css';

export default function ClientForm() {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    idClient: '',
    idPerson: '',
    namePerson: '',
    email: '',
    phone: '',
    adress: '',
    registrationDate: '01-02-2026, 12:00:00'
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
        await clientService.create(formData);
        navigate('/clients');
    } catch (err) {
        setError(err.message);
    }
    };

  return (
    <div className="form-container">
      <h2>Añadir Cliente</h2>
      {error && <p className="error-message">{error}</p>}
      <form className="client-form" onSubmit={handleSubmit}>
        <input name="idClient" type="number" placeholder="ID Cliente" onChange={handleChange} required />
        <input name="namePerson" placeholder="Nombre" onChange={handleChange} required />
        <input name="idPerson" placeholder="DNI/NIF" onChange={handleChange} required />
        <input name="email" type="email" placeholder="Email" onChange={handleChange} required />
        <input name="phone" placeholder="Teléfono" onChange={handleChange} required />
        <input name="adress" placeholder="Dirección" onChange={handleChange} required />
        <button type="submit">Guardar</button>
      </form>
    </div>
  );
}