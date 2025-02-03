import { defineStore } from "pinia"
import { getUserCurrentTest } from "@/services/userService"


export const useCurrentTestStore = defineStore("userCurrentTest", {
  state: () => ({
    test: null
  }),
  actions: {

    async update() { 

      const response = await getUserCurrentTest();
      this.test = response.data;

    },

    set(data){
      this.test = Object.assign({}, data);
    }
    
  }
})