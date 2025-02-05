import { API_JSON_CLIENT} from "@/lib/api";

export const getUserData = async () => {

    const response = await API_JSON_CLIENT.get("/users/me");

    return response;

};

export const getUserCurrentTest = async () => {

    const response = await API_JSON_CLIENT.get("/users/me/current-test");

    return response;

};


export const getUserTestResultHistory = async (pageable) => {

    const params = {
        page: pageable.page,
        size: pageable.size,
        sort: pageable.sort
    };


    const response = await API_JSON_CLIENT.get("/users/me/test-history", {
        params: params
    });

    return response;

};

