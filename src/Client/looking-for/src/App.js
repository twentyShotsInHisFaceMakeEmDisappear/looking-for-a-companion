import React from 'react';
import './App.css';
import { Routes, Route, BrowserRouter} from 'react-router-dom';
import Home from "./Pages/Home/Home";
import Navbar from './Components/Navbar/Navbar';
function App() {
  return (
      <BrowserRouter>
          <Navbar/>
        <Routes>
          <Route path="/home" element={<Home />} />
        </Routes>
      </BrowserRouter>
  );
}

export default App;
