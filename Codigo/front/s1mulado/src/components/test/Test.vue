<template>
    <div class="w-full h-full bg-white p-8">

      <h1 class="text-lg font-bold text-gray-500 mb-6">Prova</h1>
  
      <div v-for="(question, index) in test.testQuestions" :key="question.id" class="p-6 rounded-lg mb-8">

        <div class="flex flex-row items-center mb-4">
          <h3 class="text-lg font-semibold text-gray-800">Questão {{ index + 1 }} )</h3>
        </div>
        <div class="flex flex-row items-center justify-between pb-4">
          <p class="text-gray-500 text-xs ">{{ question.question.title }}</p>
          <p class="text-gray-500 text-xs"><strong class="font-semibold">Área:</strong> {{ question.question.knowledgeArea }}</p>
        </div>
        <p class="text-gray-500 mb-1">{{ question.question.context || "Nenhum contexto fornecido." }}</p>
        <p class="text-gray-500"><strong class="font-semibold"></strong> {{ question.question.alternativesIntroduction }}</p>
  
        <h4 class="text-sm font-semibold text-gray-800 mt-4 mb-2">Alternativas</h4>
        
        <AlternativesRadioComponent :question-id="question.id" :alternatives="question.question.alternatives" :model-value="question.answer"/>

      </div>

      <ButtonComponent>Enviar</ButtonComponent>

    </div>
  </template>
  
  <script>

    import AlternativesRadioComponent from '@/components/test/AlternativesRadioComponent.vue';
    import ButtonComponent from '@/components/common/ButtonComponent.vue';

export default {
  
    name: 'Test',
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
      formatDate(dateString) {
        const date = new Date(dateString);
        return date.toLocaleString(); // Formata a data para o formato local
      }
    },
    components: {
        AlternativesRadioComponent,
        ButtonComponent
    }
};
  </script>