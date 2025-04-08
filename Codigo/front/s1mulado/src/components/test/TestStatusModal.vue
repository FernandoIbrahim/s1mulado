<template>
  <div class="fixed bottom-0 left-0 flex items-start p-2 z-50 gap-2">
    <!-- Conteúdo -->
    <div v-show="isVisible" class="bg-white rounded-lg max-w-[200px] w-full p-6 relative">
      <!-- Cabeçalho -->
      <div class="flex justify-between items-center mb-4">
        <h2 class="text-l font-semibold">Questões</h2>
        <button @click="isVisible = false" class="text-gray-500 hover:text-gray-700">
          ×
        </button>
      </div>

      <!-- Grid de questões -->
      <div class="pt-2">
        <div class="grid grid-cols-5 gap-2">
          <div 
            v-for="n in 45"
            :key="n"
            class="w-5 h-5 flex items-center justify-center border-2 rounded-sm transition-colors cursor-pointer"
            :class="questionStatus(n)"
          >
            <p class="text-xs">{{ n }}</p>
          </div>
        </div>
      </div>

      <!-- Legenda -->
      <div class="mt-4 pt-4 flex gap-4 text-sm">
        <div class="flex items-center gap-2">
          <div class="w-4 h-4 bg-black border-2 border-black"></div>
          <span>Respondida</span>
        </div>
      </div>
    </div>

    <!-- Botão de toggle -->
    <button 
      @click="isVisible = !isVisible" 
      class="w-8 h-8 flex items-center justify-center bg-white rounded-full shadow-lg hover:bg-gray-50 transition-colors"
      v-show="!isVisible"
    >
      ▶
    </button>
  </div>
</template>

<script>
export default {
  props: {
      test: {
          type: Object,
          required: true
      }
  },
  data() {
      return {
          isVisible: true
      }
  },
  computed: {
      questionStatus() {
          return (questionNumber) => {
              const question = this.test.testQuestions[questionNumber - 1]
              const isAnswered = question?.answer?.trim()?.length > 0
              
              return isAnswered 
                  ? 'bg-black text-white border-black' 
                  : 'bg-white border-gray-300 text-gray-500'
          }
      }
  }
}
</script>