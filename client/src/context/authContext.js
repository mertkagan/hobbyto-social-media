import { createContext, useEffect, useState, } from "react";
import { makeRequest } from "../axios";

export const AuthContext = createContext();

export const AuthContextProvider = ({ children }) => {
  const [currentUser, setCurrentUser] = useState(
    JSON.parse(localStorage.getItem("user")) || null
  );


   const login =async (values) => { 
         const res=  await makeRequest.post("/users/login", values ,{ withCredentials: true })
        
            //setCurrentUser(res.data);
            setCurrentUser(res.data)

          
        
         

      
   };


  const logout = () =>{
    setCurrentUser(null);
  }

  useEffect(() => {
    localStorage.setItem("user", JSON.stringify(currentUser));
  }, [currentUser]);

  return (
    
    <AuthContext.Provider value={{ currentUser,setCurrentUser,login, logout}}>
      {children} 
    </AuthContext.Provider>
    
  );
};

