import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { bookService } from '../services/bookService';

export default function BookList() {
  const [books, setBooks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    loadBooks();
  }, []);

  const loadBooks = async () => {
    try {
      setLoading(true);
      const response = await bookService.getAll();
      const dataArray = Array.isArray(response.data) 
        ? response.data 
        : response.data.content || response.data.data || [];
      setBooks(dataArray);
      setError(null);
    } catch (err) {
      setError('Error al cargar los libros');
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id) => {
    if (!window.confirm('¿Seguro que quieres eliminar este libro?')) return;
    try {
      await bookService.delete(id);
      setBooks(books.filter(book => book.idBook !== id));
    } catch (err) {
      alert('Error al eliminar el libro');
    }
  };

  if (loading) return <p>Cargando libros...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div className="main">
      <h2>Lista de Libros</h2>
      <Link to="/books/new">
        <button style={{ marginBottom: '1rem' }}>Añadir Libro</button>
      </Link>
      <ul>
        {books.map(book => (
            <li key={book.idProduct} style={{ marginBottom: '10px' }}>
            {book.title}
            <Link to={`/books/${book.idProduct}`} style={{ marginLeft: '15px', marginRight: '10px' }}>
                Ver detalles
            </Link>
            <button onClick={() => handleDelete(book.idProduct)}>Eliminar</button>
            </li>
        ))}
        </ul>
    </div>
  );
}