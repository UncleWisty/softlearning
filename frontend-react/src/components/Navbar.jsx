import { Link } from 'react-router-dom';

export default function Navbar() {
  return (
    <nav className="nav-bar">
      <Link to="/" style={{ marginRight: '1rem' }}>Inicio</Link>
      <Link to="/clients" style={{ marginRight: '1rem' }}>Clientes</Link>
      <Link to="/vehicles" style={{ marginRight: '1rem' }}>Vehiculos</Link>
      <Link to="/books">Libros</Link>
    </nav>
  );
}