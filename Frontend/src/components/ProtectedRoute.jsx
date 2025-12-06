import { Navigate } from "react-router-dom";
import { AuthContext } from "../context/AuthContext";
import { useContext } from "react";



export default function ProtectedRoute({children}){//React will pass a props object with a children field, not a raw child argument
     const {isAuthenticated}=useContext(AuthContext);

    if (!isAuthenticated){
        return <Navigate to='/login' replace/>;
    }
    return children;
}



