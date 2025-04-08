import { defineStore } from "pinia"

import { findTestById} from "@/services/testService";

export const useLoginModal = defineStore("loginModal", {
  state: () => {
    return { active: false }
  },

  actions: {
    open() {
      this.active = true;
    },

    close() {
        this.active = false;
    },
  },

})


export const useCompletedTestResult = defineStore("useCompletedTestResult", {
  state: () => {
    return {
       active: false,
       testData: null
      }
  },

  actions: {

    async open(testId) {

      this.testData = (await findTestById(testId)).data;
      this.active = true;

    },
    close() {

        this.active = false;
        
    },
    
  },

})