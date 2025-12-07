import { useState } from "react";
import api from "../api/axios";

export default function CreateModal({onClose,onProjectCreated}){
    const [projectName,setProjectName]=useState(null);
    const [projectDescription,setProjectDescription]=useState(null);
    const [loading,setLoading]=useState(false);
    const [error,setError]=useState(false)

    async function handleSubmit(e){
        e.preventDefault()
        setLoading(true);
        setError(false);

        try{
        const res=api.post("/projects",{
            projectName,projectDescription
        })
        onProjectCreated(res.data);
        onClose();
       }
       catch(err){
           setError(err.message|| "Failed to vreate Project")
       }
       finally{
        setLoading(false)
       }
    }
    return (
        <div id="root">
        <h1>Create Project</h1>


        {error && <p color="red">{error}</p>}

        <form>
            <label>Project Title</label>
            <input
            type="text"
            placeholder="Project name"
            value={projectName}
            onChange={(e)=>setProjectName(e.target.value)}
            required
            />

            <label>Project Description</label>
            <textarea
            required
            value={projectDescription}
            onChange={(e)=>setProjectDescription(e.target.value)}
            />
            <button type="button" onClick={onClose}>cancel</button>

            <button type="submit" onClick={handleSubmit}>   {loading ? "Creating..." : "Create"}</button>
        </form>
        </div>
    )
}