import { useState } from "react";
import api from "../api/axios";

export default function EditModal({project, onClose, onProjectUpdated}){
    const [name,setName]=useState(project.name);
    const [description,setDescription]=useState(project.description);
    const [loading,setLoading]=useState(false);
    const [error,setError]=useState(false)

    async function handleSubmit(e){
        e.preventDefault()
        setLoading(true);
        setError(false);

        try{
            const res=await api.patch(`/projects/${project.id}`,{
                name,
                description
            })
            onProjectUpdated(res.data);
            onClose();
        }
        catch(err){
            setError(err.message|| "Failed to update Project")
        }
        finally{
            setLoading(false)
        }
    }

    return (
        <div style={styles.modalOverlay}>
        <div style={styles.modalContent}>
        <h2>Edit Project</h2>

        {error && <p style={{color:"red"}}>{error}</p>}

        <form onSubmit={handleSubmit}>
            <div style={styles.formGroup}>
                <label style={styles.label}>Project Title</label>
                <input
                    style={styles.input}
                    type="text"
                    placeholder="Project name"
                    value={name}
                    onChange={(e)=>setName(e.target.value)}
                    required
                />
            </div>

            <div style={styles.formGroup}>
                <label style={styles.label}>Project Description</label>
                <textarea
                    style={styles.textarea}
                    placeholder="Describe your project"
                    value={description}
                    onChange={(e)=>setDescription(e.target.value)}
                    required
                />
            </div>

            <div style={styles.buttonGroup}>
                <button type="button" onClick={onClose} style={styles.cancelButton}>Cancel</button>
                <button type="submit" disabled={loading} style={styles.submitButton}>
                    {loading ? "Updating..." : "Update Project"}
                </button>
            </div>
        </form>
        </div>
        </div>
    )
}

const styles = {
    modalOverlay: {
        position: 'fixed',
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
        backgroundColor: 'rgba(0,0,0,0.5)',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        zIndex: 1000
    },
    modalContent: {
        backgroundColor: 'white',
        padding: '30px',
        borderRadius: '8px',
        maxWidth: '500px',
        width: '90%'
    },
    formGroup: {
        marginBottom: '15px'
    },
    label: {
        display: 'block',
        marginBottom: '5px',
        fontWeight: '500',
        color: '#333'
    },
    input: {
        width: '100%',
        padding: '8px',
        borderRadius: '4px',
        border: '1px solid #ddd',
        fontSize: '14px',
        boxSizing: 'border-box'
    },
    textarea: {
        width: '100%',
        padding: '8px',
        borderRadius: '4px',
        border: '1px solid #ddd',
        fontSize: '14px',
        minHeight: '100px',
        boxSizing: 'border-box',
        resize: 'vertical'
    },
    buttonGroup: {
        display: 'flex',
        gap: '10px',
        justifyContent: 'flex-end',
        marginTop: '20px'
    },
    cancelButton: {
        padding: '10px 20px',
        borderRadius: '5px',
        border: '1px solid #ddd',
        backgroundColor: 'white',
        cursor: 'pointer',
        fontSize: '14px'
    },
    submitButton: {
        padding: '10px 20px',
        borderRadius: '5px',
        border: 'none',
        backgroundColor: '#2196F3',
        color: 'white',
        cursor: 'pointer',
        fontSize: '14px'
    }
}