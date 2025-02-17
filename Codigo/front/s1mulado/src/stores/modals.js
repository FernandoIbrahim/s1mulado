import { defineStore } from "pinia"

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


export const useCompletedTest = defineStore("completedTest", {
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