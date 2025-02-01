import { API_JSON_CLIENT} from "@/lib/api";

export const getUserData = async () => {

    const response = await API_JSON_CLIENT.get("/users/me");

    return response;

};

export const getUserCurrentTest = async () => {

    const response = await API_JSON_CLIENT.get("/users/me/current-test");

    return response;

};


