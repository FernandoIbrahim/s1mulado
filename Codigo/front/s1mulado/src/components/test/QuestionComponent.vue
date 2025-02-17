<template>

    <p class="text-gray-500 mb-1">{{ this.testQuestion.question.context || "Nenhum contexto fornecido." }}</p>
    <p class="text-gray-500"><strong class="font-semibold"></strong> {{ this.testQuestion.question.alternativesIntroduction }}</p>
    <h4 class="text-sm font-semibold text-gray-800 mt-4 mb-2">Alternativas</h4>

    <AlternativesRadioComponent :question-id="testQuestion.id" :alternatives="testQuestion.question.alternatives" :model-value="testQuestion.answer"/>
    <p v-if="testQuestion.correct && isTestConcluded" class="text-green-600 ml-2">(Correta)</p>
    <p v-if="!testQuestion.correct && isTestConcluded" class="text-red-600 ml-2">(Errada)</p>

</template>

<script>

    import AlternativesRadioComponent from '@/components/test/AlternativesRadioComponent.vue';

    export default {
        props: {
            testQuestion: {
                required: true,
            },
            isTestConcluded: {
                required :true
            }
        },
        components: {
            AlternativesRadioComponent
        }, mounted(){
            console.log(this.testQuestion);
        }
    }


</script>