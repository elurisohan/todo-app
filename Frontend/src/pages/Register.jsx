import { useState } from "react";
import { registerUser } from "../services/authService";
import { useNavigate } from "react-router-dom";

function Register() {
  const [name, setName] = useState("");
  const [username,setUsername]=useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [loading,setLoading]=useState(false);
  const [error,setError]=useState(null)

  const navigate=useNavigate();


  //after a user signs up. they should be navigated to login page again. Here, we send bundled registration details to the registeruser methof in authservice
  const handleSubmit =async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(false);

    try{
      const registration_request=await registerUser({name,username,email,password})
      navigate('/login')
    }
    catch(err){
      throw (err.message||"Registration Failed")
    }
    finally{
      setLoading(false)
    }    
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <form
        onSubmit={handleSubmit}
        className="bg-white p-6 rounded-lg shadow-md w-80"
      >
        <h2 className="text-2xl font-semibold text-center mb-4">Sign Up</h2>

        <input
          type="text"
          placeholder="Full Name"
          className="w-full p-2 border rounded mb-3"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />

        <input
        type="text"
        value={username}
        onChange={(e)=>setUsername(e.target.value)}
        placeholder="Username"
        required
        />

        <input
          type="email"
          placeholder="Email"
          className="w-full p-2 border rounded mb-3"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />

        <input
          type="password"
          placeholder="Password"
          className="w-full p-2 border rounded mb-3"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />

        <button
          type="submit"
          
          className="w-full bg-green-600 text-white py-2 rounded hover:bg-green-700"
        >
          {loading?"Creating account...":"Create Account"}
        </button>
      </form>
    </div>
  );
}

export default Register;
