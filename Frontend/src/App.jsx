//import { useState } from 'react'

//import './App.css'
import {BrowserRouter,Link,Route,Routes} from 'react-router-dom';
import Login from './pages/Login.jsx';
import Register from './pages/Register.jsx';
import Home from './pages/Home.jsx';
import ProtectedRoute from './components/ProtectedRoute.jsx';




function App() {
  //const [count, setCount] = useState(0)

  return (
    <BrowserRouter>
    <nav>
      <div style={{padding:"20px", }}>
    <Link to='/login'>login</Link>|{" "}
    <Link to='/signup'>signup</Link>|{" "}
    <Link to='/home'>Home</Link>
</div>
</nav>

    <Routes>
      <Route path="/login" element={<Login/>}/>
      <Route path="/signup" element={<Register/>}/>
      <Route path="/home" element={<ProtectedRoute><Home /></ProtectedRoute>}/>
    </Routes>
    </BrowserRouter>
  );
  
}


export default App;