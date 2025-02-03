import { API_JSON_CLIENT} from "@/lib/api";

export const create = async (test) => {
    const response = await API_JSON_CLIENT.post("/tests", test);

    return response;

};

export const patchQuestionAnswer = async (testQuestion) => {
    const response = await API_JSON_CLIENT.patch("/tests/test-questions", testQuestion);

    return response;

};

export const finalizeTest = async (id) => {
    const response = await API_JSON_CLIENT.post(`/tests/${id}/end`);

    return response

}
