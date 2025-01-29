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
        <h3 class="text-xl font-semibold text-gray-800 mb-4">Questão {{ index + 1 }}</h3>
        <p class="text-gray-700"><strong class="font-semibold">Ano:</strong> {{ question.question.year }}</p>
        <p class="text-gray-700"><strong class="font-semibold">Área de Conhecimento:</strong> {{ question.question.knowledgeArea }}</p>
        <p class="text-gray-700"><strong class="font-semibold">Disciplina:</strong> {{ question.question.subject.name }}</p>
        <p class="text-gray-700"><strong class="font-semibold">Título:</strong> {{ question.question.title }}</p>
        <p class="text-gray-700"><strong class="font-semibold">Contexto:</strong> {{ question.question.context || "Nenhum contexto fornecido." }}</p>
        <p class="text-gray-700"><strong class="font-semibold">Introdução das Alternativas:</strong> {{ question.question.alternativesIntroduction }}</p>
  
        <h4 class="text-lg font-semibold text-gray-800 mt-4 mb-2">Alternativas</h4>
        <ul>
          <li v-for="alternative in question.question.alternatives" :key="alternative.id" class="text-gray-700 mb-2">
            <strong class="font-semibold">{{ alternative.letter }}:</strong> {{ alternative.text }}
            <span v-if="alternative.isCorrect" class="text-green-600 ml-2">(Correta)</span>
          </li>
        </ul>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'Test',
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
    }
  };
  </script>