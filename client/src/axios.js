import axios from "axios";

export const makeRequest = axios.create({
    baseURL:"http://localhost:8085/",
    withCredentials:true,
});