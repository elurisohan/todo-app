import { createContext} from "react";


//The reason we're maintaining this component is to maintain global state of data to avoid prop drilling. 
export const AuthContext= createContext();
//You use new Something() only when Something is a constructor function or class meant to be instantiated. Ordinary functions are just called like something(). createContext is oridinary function.

