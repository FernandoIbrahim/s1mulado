<template>
  
    <div class="w-full h-full bg-white p-8">

      <!-- <h1 class="text-lg font-bold text-gray-500 mb-6">Prova</h1> -->
  
      <div v-for="(testQuestion, index) in test.testQuestions" :key="testQuestion.id" class="p-6 rounded-lg mb-8">
        
        <div class="flex flex-row items-center mb-8">
          <h3 class="text-lg font-semibold text-gray-800">Questão {{ index + 1 }} </h3>
        </div>
        <QuestionComponent :testQuestion="testQuestion" :isTestConcluded="test.concluded"/>

      </div>
      
      <ButtonComponent v-if="!test.concluded" @click="sendTestAnswers" padding-x="2" padding-y="2">Enviar</ButtonComponent>

    </div>
    
  </template>
  
  <script>

    import { finalizeTest } from '@/services/testService';
    //import AlternativesRadioComponent from '@/components/test/AlternativesRadioComponent.vue';
    import QuestionComponent from '@/components/test/QuestionComponent.vue';
    import ButtonComponent from '@/components/common/ButtonComponent.vue';
    import { useCurrentTestStore } from '@/stores/currentTest';

export default {
  
    name: 'Test',
    setup() {
      const testStore = useCurrentTestStore()
      return { testStore }
    },
    data(){
        return {
        }
    },
    props: {
      test: {
        type: Object,
        required: true
      }
    },
    methods: {

      async sendTestAnswers(){

        try{

          const response = await finalizeTest(this.test.id);
          const answerTestData = response.data;
          await this.testStore.set(answerTestData);
          
          console.log(this.testStore.test);

        }catch(error){

          console.log(error);

        }

      }

    },
    components: {
        QuestionComponent,
        ButtonComponent
    }
};
  </script>