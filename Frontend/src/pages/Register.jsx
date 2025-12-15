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
   catch (error) {
  const message=error??
  error?.response?.data??
  "Cannot connect to the server"

/* To troubleshoot the reason behind inaccurate response from backend , try printing it as following. 
console.log("FULL ERROR:", error);
console.log("RESPONSE:", error.response);
console.log("DATA:", error.response?.data);
*/  
  setError(message)

}
finally{
  setLoading(false)
}
  };

  return (
    <div style={styles.container}>

      {error && <p style={{color:"red"}}>{error}</p>}
      <form
      style={styles.auth_form}
        onSubmit={handleSubmit}
        //className="bg-white p-6 rounded-lg shadow-md w-80"
      >
        <h2 style={styles.heading}>Sign Up</h2>

        <input
          type="text"
          placeholder="Full Name"
          //className="w-full p-2 border rounded mb-3"
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
          //className="w-full p-2 border rounded mb-3"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />

        <input
          type="password"
          placeholder="Password"
          //className="w-full p-2 border rounded mb-3"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />

        <button
        style={styles.button}
          type="submit"
        >
          {loading?"Creating account...":"Create Account"}
        </button>
      </form>
    </div>
  );
}

export default Register;


const styles={
container:{
  display:'flex',
  alignItems:'center',
  justifyContent:'center',
  flexDirection:'column'
}
}