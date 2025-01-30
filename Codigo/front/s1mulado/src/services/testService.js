import { API_JSON_CLIENT} from "@/lib/api";

export const create = async (test) => {
    const response = await API_JSON_CLIENT.post("/tests", test);

    return response;

};