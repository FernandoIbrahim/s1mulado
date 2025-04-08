import { defineStore } from "pinia"

export const useTestResultHistoryFilter = defineStore("testResultHistoryFilter", {
    state: () => {

        return {

            minDate: null,
            maxDate: null,
            knowledgeArea: { id: 2, name: "Natureza" }

        }

    },

    actions: {
        setMaxDate(maxDate){
            this.maxDate = maxDate;
        },

        setMinDate(minDate){
            this.minDate = minDate;
        },

        setKnowledgeArea(knowledgeArea){
            this.knowledgeArea = knowledgeArea;
        }
    }
    
})