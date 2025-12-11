import { useState } from "react";
import api from "../api/axios";

export default function CreateModal({onClose,onProjectCreated}){
    const [name,setName]=useState("");
    const [description,setDescription]=useState("");
    const [loading,setLoading]=useState(false);
    const [error,setError]=useState(false)

    async function handleSubmit(e){
        e.preventDefault()
        setLoading(true);
        setError(false);

        try{
        const res=await api.post("/projects/",{
            name,description
        })
        onProjectCreated(res.data);
        onClose();
       }
       catch(err){
           setError(err.message|| "Failed to create Project")
       }
       finally{
        setLoading(false)
       }
    }
    return (
        <div id="modal_root">
        <h1>Create Project</h1>


        {error && <p color="red">{error}</p>}

        <form>
            <label>Project Title</label>
            <input
            type="text"
            placeholder="Project name"
            value={name}
            onChange={(e)=>setName(e.target.value)}
            required
            />

            <label>Project Description</label>
            <textarea
            required
            value={description}
            onChange={(e)=>setDescription(e.target.value)}
            />
            <button type="button" onClick={onClose}>cancel</button>

            <button type="submit" onClick={handleSubmit}>   {loading ? "Creating..." : "Create"}</button>
        </form>
        </div>
    )
}