import { getProjects } from "../services/projectService";
import { AuthContext } from "../context/AuthContext";
import {  useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";


function Home(){
    const [projects, setProjects]  = useState([]);
    const [loading,setLoading] = useState(true);
    const [error,setError] =useState(null);
    const {logout}=useContext(AuthContext);
    const navigate=useNavigate();

    useEffect(()=>{
            async function loadProjects(){
                try{
                const response=await getProjects();
                setProjects(response)
                      }
                catch(err){
                    setError(err.message || "Failed to load projects");
                            }
                finally{
                    setLoading(false);
                }
                        } loadProjects();
                    },[]);
  

    if (loading){
        return <p style={{padding:"20px"}}>Loading your data</p>
    }
    if (error){
        return <p style={{padding:"20px",color:"red"}}>{error}</p>
    }
    /*Why not onClick={logout()}?
                                                        onClick={logout()} calls logout immediately when the component renders, not when the button is clicked.
                                                        onClick={logout} passes the function reference so React can call it later on click.*/
    return (
        <div id="root">
        <h1>My Projects</h1>

        <button disabled>Profile</button>
        <button onClick={}>+</button>
     
        <button onClick={()=>{
            logout ();
            navigate('/login')
        }}>Logout</button>
   
        {projects.length===0?(<p>No projects</p>):
        (<ul>
        {projects.map((project)=>(
            <li key={project.id} style={{
                padding:"10px"
            }}>
                <strong>{project.name}</strong>
                <p>{project.description}</p>
            </li>
        ))}
        </ul>)}
    </div>)
}
export default Home;
