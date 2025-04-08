import axios from "axios";
import Cookies from "js-cookie";

const BASE_URL = import.meta.env.VITE_PUBLIC_JAVA_API_URL;

export const API_JSON_CLIENT = axios.create({

    baseURL: BASE_URL,
    headers: {
        "Content-Type": "application/json",
    },

});

export const API_FORM_DATA_CLIENT = axios.create({
    baseURL: BASE_URL,
    headers: {
        "Content-Type": "multipart/form-data",
    },
});

const addAuthInterceptor = (client) => {
    client.interceptors.request.use(async (config) => {

        const token = Cookies.get("jwt");


        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }

        return config;

    });
};

addAuthInterceptor(API_JSON_CLIENT);
addAuthInterceptor(API_FORM_DATA_CLIENT);