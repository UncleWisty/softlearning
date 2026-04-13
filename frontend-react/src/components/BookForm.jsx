import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { bookService } from '../services/bookService';
import './BookForm.css';

export default function BookForm() {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    idProduct: '',
    name: '',
    description: '',
    price: '',
    stock: '',
    isbn: '',
    title: '',
    author: '',
    publisher: '',
    publishYear: '',
    weight: '',
    height: '',
    width: '',
    depth: ''
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
      await bookService.create(formData);
      navigate('/books');
    } catch (err) {
      setError(err.message);
    }
  };

  return (
    <div className="form-container">
      <h2>Añadir Libro</h2>
      {error && <p className="error-message">{error}</p>}
      <form className="book-form" onSubmit={handleSubmit}>
        <input name="idProduct" placeholder="ID Producto" onChange={handleChange} required />
        <input name="name" placeholder="Nombre genérico" onChange={handleChange} required />
        <input name="title" placeholder="Título del libro" onChange={handleChange} required />
        <input name="isbn" placeholder="ISBN" onChange={handleChange} required />
        <input name="author" placeholder="Autor" onChange={handleChange} required />
        <input name="publisher" placeholder="Editorial" onChange={handleChange} required />
        <input name="description" placeholder="Descripción" onChange={handleChange} required />
        <input name="publishYear" type="number" placeholder="Año de publicación" onChange={handleChange} required />
        <input name="price" type="number" step="0.01" placeholder="Precio" onChange={handleChange} required />
        <input name="stock" type="number" placeholder="Stock inicial" onChange={handleChange} required />
        <input name="weight" type="number" step="0.01" placeholder="Peso" onChange={handleChange} required />
        <input name="height" type="number" step="0.01" placeholder="Altura" onChange={handleChange} required />
        <input name="width" type="number" step="0.01" placeholder="Anchura" onChange={handleChange} required />
        <input name="depth" type="number" step="0.01" placeholder="Profundidad" onChange={handleChange} required />
        <button type="submit">Guardar</button>
      </form>
    </div>
  );
}