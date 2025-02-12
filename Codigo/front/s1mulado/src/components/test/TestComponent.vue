<template>
    <div class="w-full h-full bg-white p-8">

      <h1 class="text-lg font-bold text-gray-500 mb-6">Prova</h1>
  
      <div v-for="(tesQuestion, index) in test.testQuestions" :key="tesQuestion.id" class="p-6 rounded-lg mb-8">

        <div class="flex flex-row items-center mb-4">
          <h3 class="text-lg font-semibold text-gray-800">Questão {{ index + 1 }} )</h3>
        </div>
        <div class="flex flex-row items-center justify-between pb-4">
          <p class="text-gray-500 text-xs ">{{ tesQuestion.question.title }}</p>
          <p class="text-gray-500 text-xs"><strong class="font-semibold">Área:</strong> {{ tesQuestion.question.knowledgeArea }}</p>
        </div>
        <p class="text-gray-500 mb-1">{{ tesQuestion.question.context || "Nenhum contexto fornecido." }}</p>
        <p class="text-gray-500"><strong class="font-semibold"></strong> {{ tesQuestion.question.alternativesIntroduction }}</p>
  
        <h4 class="text-sm font-semibold text-gray-800 mt-4 mb-2">Alternativas</h4>
        
        <AlternativesRadioComponent :question-id="tesQuestion.id" :alternatives="tesQuestion.question.alternatives" :model-value="tesQuestion.answer"/>
        <p v-if="tesQuestion.correct && test.concluded" class="text-green-600 ml-2">(Correta)</p>
        <p v-if="!tesQuestion.correct && test.concluded" class="text-red-600 ml-2">(Errada)</p>
      </div>
      
      <ButtonComponent v-if="!test.concluded" @click="sendTestAnswers">Enviar</ButtonComponent>

    </div>
  </template>
  
  <script>

    import { finalizeTest } from '@/services/testService';
    import AlternativesRadioComponent from '@/components/test/AlternativesRadioComponent.vue';
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
        AlternativesRadioComponent,
        ButtonComponent
    }
};
  </script>