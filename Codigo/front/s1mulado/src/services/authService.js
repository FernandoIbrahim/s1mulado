import { API_JSON_CLIENT} from "@/lib/api";
import Cookies from "js-cookie";

export const login = async (loginData) => {
    const response = await API_JSON_CLIENT.post("/auth/login", loginData);

    return response;

};

export const register = async (registerData) => {
    const response = await API_JSON_CLIENT.post("/auth/register", registerData);

    return response;

};


export const isUserLoggedIn = () => {
    return (Cookies.get("jwt") !== null && Cookies.get("jwt") !== undefined);
};