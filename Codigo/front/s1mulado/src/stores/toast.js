import { defineStore } from 'pinia'
import { useToast } from 'vue-toastification'


export const useCounterStore = defineStore('counter', {
  state: () => ({
    count: 0
  }),
  actions: {
    increment() {
      this.count++
      const toast = useToast()
      toast.success("Incremented!")
    }
  }
})