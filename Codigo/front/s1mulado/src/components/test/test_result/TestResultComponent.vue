<template>

    <div class="  flex flex-col bg-white rounded-lg p-4 shadow-md hover:shadow-xl  transition-shadow my-2 w-80/100 hover:scale-102 border-freshAir border hover:border-0">
      <div class="flex justify-between items-center mb-3">

        <span 
          :class="{
            'bg-green-100 text-green-800': testResult.concluded,
            'bg-yellow-100 text-yellow-800': !testResult.concluded
          }"
          class="px-3 py-1 rounded-full text-xs font-medium"
        >
          {{renameKnowledgeArea(testResult.knowledgeArea)}}
        </span>

        <span class="text-gray-800 text-xs">
          {{ formatDate(testResult.date) }}
        </span>
      </div>
      
      <div class="flex flex-col md:flex-row gap-4 md:gap-8 w-full justify-center">
        <div class="text-center">
          <p class="text-gray-500 text-xs">Questões</p>
          <p class="text-sm font-semibold text-gray-700">
            {{ testResult.totalQuestions }}
          </p>
        </div>
        
        <div class="text-center">
          <p class="text-gray-500 text-xs">Acertos</p>
          <p class="text-sm font-semibold text-green-600">
            {{ testResult.correctAnswers }}
          </p>
        </div>
        
        <div class="text-center">
          <p class="text-gray-500 text-xs">Percentual</p>
          <p class="text-sm font-semibold" :class="selectPercentColor(calculatePercentage(testResult.correctAnswers, testResult.totalQuestions))" >
            {{ calculatePercentage(testResult.correctAnswers, testResult.totalQuestions) }}%
          </p>
        </div>
      </div>
    </div>
  </template>
  
<script>

  export default {
    name: 'TestResult',
    props: {
      testResult: {
        type: Object,
        required: true,
        default: () => ({})
      }
    },
    methods: {

      calculatePercentage(correct, total) {
        if (total === 0) return 0
        return Math.round((correct / total) * 100)
      },

      formatDate(dateString) {
        try {
          const options = { 
            year: 'numeric', 
            month: 'long', 
            day: 'numeric', 
            hour: '2-digit', 
            minute: '2-digit' 
          }
          return new Date(dateString).toLocaleDateString('pt-BR', options)
        } catch (error) {
          console.error('Erro ao formatar data:', error)
          return dateString 
        }
      },

      renameKnowledgeArea(knowledgeArea){
        switch(knowledgeArea){
          case "NATURE": 
            return "NATUREZA";
            break;
          case "MATHEMATICS": 
            return "EXATAS";
            break;
          case "LANGUAGES": 
            return "LINGUAGENS";
            break;
          case "HUMANITIES": 
            return "HUMANAS";
            break;
        }
      },

      selectPercentColor(percentage){

        if(percentage >= 80){
          return "text-green-800";
        }else if(percentage >= 50){
          return "text-blue-800";
        }else{
          return "text-red-800";
        }

      }
      

    }
  }
</script>