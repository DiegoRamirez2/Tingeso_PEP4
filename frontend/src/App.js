
import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import HomeComponent from './components/HomeComponent';
import RegistarComponent from './components/RegistrarComponent';
import GenerarResumenComponent from './components/GenerarResumentComponent';

function App() {
  return (
    <div className='App'>
      <BrowserRouter>
        <Routes>
          <Route path = "/" element={<HomeComponent/>}/>
          <Route path = "/registrar" element={<RegistarComponent/>}/>
          <Route path = "/resumen" element={<GenerarResumenComponent/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
