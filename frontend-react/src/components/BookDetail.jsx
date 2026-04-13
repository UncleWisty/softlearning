import { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import { bookService } from '../services/bookService';

export default function BookDetail() {
  const { id } = useParams();
  const [book, setBook] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchBook = async () => {
      try {
        const response = await bookService.getById(id);
        setBook(response.data);
      } catch (err) {
        setError(err.message);
      }
    };
    fetchBook();
  }, [id]);

  if (error) return <p>{error}</p>;
  if (!book) return <p>Cargando...</p>;

  return (
    <div className="main">
      <h2>Detalles del Libro</h2>
      <p><strong>ID:</strong> {book.idProduct}</p>
      <p><strong>Nombre:</strong> {book.name}</p>
      <p><strong>Título:</strong> {book.title}</p>
      <p><strong>ISBN:</strong> {book.isbn}</p>
      <p><strong>Autor:</strong> {book.author}</p>
      <p><strong>Editorial:</strong> {book.publisher}</p>
      <p><strong>Año:</strong> {book.publishYear}</p>
      <p><strong>Descripción:</strong> {book.description}</p>
      <p><strong>Precio:</strong> {book.price}</p>
      <p><strong>Stock:</strong> {book.stock}</p>
      <p><strong>Peso:</strong> {book.weight}</p>
      <p><strong>Altura:</strong> {book.height}</p>
      <p><strong>Anchura:</strong> {book.width}</p>
      <p><strong>Profundidad:</strong> {book.depth}</p>
      <Link to="/books">
        <button style={{ marginTop: '1rem' }}>Volver</button>
      </Link>
    </div>
  );
}