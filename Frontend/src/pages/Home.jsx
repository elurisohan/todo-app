import { getProjects } from "../services/projectService";
import { AuthContext } from "../context/AuthContext";
import {  useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import CreateModal from "../components/createModal";


function Home(){
    const [projects, setProjects]  = useState([]);
    const [loading,setLoading] = useState(true);
    const [error,setError] =useState(null);
    const [showmodal,setShowModal]=useState(false);
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
//here why can't we pass the project as arg instead of a property
    function handleSubmit(newProject){
        setProjects((prev)=>[...prev,newProject])
    }
//JS evaluate the code we write insideas  Call setShowModal(true) RIGHT NOW 2. Get the return value (undefined) 3. Assign that return value to onClick. Which is why you need to write as Create a new function: () => setShowModal(true) Assign that FUNCTION to onClick 3. React will call this function when clicked
    return (
        <div id="root">
        <h1>My Projects</h1>

        <button disabled>Profile</button>
        <button onClick={()=>setShowModal(true)}>+</button>
     
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

        {showmodal && (
        <CreateModal
            onClose={()=>setShowModal(false)}
            onProjectCreated={()=>handleSubmit()}
        />)}


    </div>)
}
export default Home;

/*
Render components with JSX: <MyComponent prop1={...} />.

Do not call component functions directly, as the context changes and React won't consider the component in the tree and it could become tough to debug. 
*/