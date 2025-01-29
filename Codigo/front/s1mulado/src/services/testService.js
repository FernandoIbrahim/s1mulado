import { API_JSON_CLIENT} from "@/lib/api";

export const create = async (test, header) => {
    const response = await API_JSON_CLIENT.post("/tests", test , {headers: header});

    return response;

};