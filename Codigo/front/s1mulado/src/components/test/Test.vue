<template>
    <div class="w-full h-full bg-white p-8">
      <h1 class="text-3xl font-bold text-green-600 mb-6">Detalhes da Prova</h1>
      <div class="bg-gray-100 p-6 rounded-lg shadow-md mb-8">
        <p class="text-gray-700"><strong class="font-semibold">ID:</strong> {{ test.id }}</p>
        <p class="text-gray-700"><strong class="font-semibold">Data:</strong> {{ formatDate(test.date) }}</p>
        <p class="text-gray-700"><strong class="font-semibold">Número de Questões:</strong> {{ test.questionsNumber }}</p>
      </div>
  
      <h2 class="text-2xl font-bold text-green-600 mb-4">Questões</h2>
      <div v-for="(question, index) in test.testQuestions" :key="question.id" class="bg-white p-6 rounded-lg shadow-md mb-6">

        <div class="flex flex-row items-center mb-4 justify-between">
          <h3 class="text-xl font-semibold text-gray-800">Questão {{ index + 1 }}</h3>
          <p class="text-gray-700 text-xs"><strong class="font-semibold">Área:</strong> {{ question.question.knowledgeArea }}</p>
        </div>

        <p class="text-gray-700"><strong class="font-semibold">Disciplina:</strong> {{ question.question.subject.name }}</p>
        <p class="text-gray-700"><strong class="font-semibold">Título:</strong> {{ question.question.title }}</p>
        <p class="text-gray-700"><strong class="font-semibold">Contexto:</strong> {{ question.question.context || "Nenhum contexto fornecido." }}</p>
        <p class="text-gray-700"><strong class="font-semibold">Introdução das Alternativas:</strong> {{ question.question.alternativesIntroduction }}</p>
  
        <h4 class="text-lg font-semibold text-gray-800 mt-4 mb-2">Alternativas</h4>
        
        <RadioGroup v-model="selectedAlternative">
            <ul>
              {{ console.log(question.question.alternatives) }}
              <li 
                v-for="alternative in question.question.alternatives" 
                :key="alternative.id" 
                class="text-gray-700 mb-2 flex items-center space-x-2"
              >
                  <div class="flex flex-row min-h-10 w-full items-center bg-gray-100 rounded-md"> 
                    <RadioGroupItem :id="(alternative.id).toString()" :value="alternative.letter" class="ml-5"/>
                    <ShadLabel :for="(alternative.id).toString()" class="pl-10">
                      <span class="pl-5">{{ alternative.text }}</span>
                    </ShadLabel>
                  </div>
              </li>

            </ul>
        </RadioGroup>
      </div>
    </div>
  </template>
  
  <script>

    import { Label as ShadLabel } from '@/components/ui/label'
    import { RadioGroup, RadioGroupItem } from '@/components/ui/radio-group'
  
  export default {
    name: 'Test',
    data(){
        return {
            selectedAlternative: null
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
        RadioGroup,
        RadioGroupItem,
        ShadLabel
    }
  };
  </script>