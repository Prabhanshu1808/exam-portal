import React from 'react'
import Home from './components/Home'
import Navbar from './components/Navbar'
import { BrowserRouter as Router, Routes, Route, Link} from "react-router-dom";
import Login from './components/login';
import Signup from './components/signup';

function App() {
  return (
    <div>
    <Navbar />
    <Router>
     
      <Routes>
        <Route path='/' element={<Home/>}/>
        <Route path='/login' element={<Login />}/>
        <Route path='/signup' element={<Signup />}/>
      </Routes>
    </Router>
    </div>
  )
}

export default App
