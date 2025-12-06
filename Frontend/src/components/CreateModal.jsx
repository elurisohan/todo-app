import { useState } from "react";

export default function CreateModal(){
    const [projectName,setProjectName]=useState(null);
    const [projectDescription,setProjectDescription]=useState(null);

    return (
        <div id="root">
        <h1>Create Project</h1>
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
            <input
            type="text"
            required
            value={projectDescription}
            onChange={(e)=>setProjectDescription(e.target.value)}
            />
        </form>
        </div>
    )
}