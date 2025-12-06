import api from "../api/axios";

const api_url="http://localhost:8081/api/v1/auth";

export async function loginUser(credentials){
    try {
    const response=await api.post(`${api_url}/login`,credentials)//use backticks here and not usual quotes
    return response.data;
    }
    catch (err){
        throw err.response?.data || {message:"Network error"};
    }
}

export async function registerUser(details){
    try{
    const response=await api.post(`${api_url}/register`,details);
        return response.data;
} catch (err){
        throw err.response?.data ||  { message: "Network error" };
}

}