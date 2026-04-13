import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import Home from './pages/Home';
import ClientList from './components/ClientList';
import BookList from './components/BookList';
import ClientForm from './components/ClientForm';
import ClientDetail from './components/ClientDetail';
import BookForm from './components/BookForm';
import BookDetail from './components/BookDetail';
import VehicleList from './components/VehicleList';
import VehicleForm from './components/VehicleForm';
import VehicleDetail from './components/VehicleDetail';

export default function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/clients" element={<ClientList />} />
        <Route path="/clients/new" element={<ClientForm />} />
        <Route path="/clients/:id" element={<ClientDetail />} />
        <Route path="/books" element={<BookList />} />
        <Route path="/books/new" element={<BookForm />} />
        <Route path="/books/:id" element={<BookDetail />} />
        <Route path="/vehicles" element={<VehicleList />} />
        <Route path="/vehicles/new" element={<VehicleForm />} />
        <Route path="/vehicles/:matricula" element={<VehicleDetail />} />
      </Routes>
    </BrowserRouter>
  );
}