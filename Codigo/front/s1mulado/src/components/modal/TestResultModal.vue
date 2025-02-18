<template>
  <div class="fixed inset-0 flex items-center justify-center bg-gray-500/50 z-10 backdrop-blur-sm">
      <div class="bg-white p-6 rounded-lg shadow-lg w-full max-w-[90%] max-h-[90vh] overflow-auto sm:min-w-[400px] md:min-w-[600px] lg:min-w-[700px]">
          
          <div class="w-full flex justify-end p-2">
              <div class="w-9 h-9 flex items-center">
                  <ButtonComponent class="w-full flex" @click="handleExitModal" padding-x="1" padding-y="1">X</ButtonComponent> 
              </div>
          </div>
          
          <TestComponent :test="testResultStore.testData"/>
      </div>
  </div>
</template>

<script>

import { useCompletedTestResult } from '@/stores/modals.js'

import ButtonComponent  from '@/components/common/ButtonComponent.vue'
import TestComponent from '@/components/test/TestComponent.vue';


export default{

  name: 'completedTestModal',
  data(){

    return {
      testResultStore: useCompletedTestResult(),
    }

  },
  methods: {

    async handleExitModal(){
      this.testResultStore.close();
    },

    async getTestData(){
      const response = await findTestById(this.testId);
      console.log(this.testData)
    }

  },
  components:{
    ButtonComponent,
    TestComponent
  }

}

</script>