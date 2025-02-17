<template>
    <div>
      <div class="fixed inset-0 flex items-center justify-center bg-gray-500 hover:bg-sky-100/10 z-10 backdrop-blur-xs">
        <div class="bg-white p-6 rounded-lg shadow-lg w-[400px] ">

          <div class = "w-full h-fit flex justify-end p-2">
            <div class="w-9 h-9 flex items-center">
              <ButtonComponent class="w-full flex" @click="handleExitModal" padding-x="1" padding-y="1" >X</ButtonComponent> 
            </div>
          </div>
          
          <h1>oi</h1>

        </div>
      </div>
    </div>
  </template>


<script>

import { useCompletedTest } from '@/stores/modals.js'

export default{

  name: 'completedTestModal',
  methods: {

    async handleExitModal(){
        this.useCompletedTest.close();
    }

  }

}

</script>