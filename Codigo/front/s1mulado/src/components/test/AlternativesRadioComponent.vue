<template>
  <RadioGroup v-model="questionAnswer">
    <ul>
      <li
        v-for="alternative in alternatives"
        :key="alternative.id"
        class="text-gray-700 mb-2"
      >

        <ShadLabel 
          :for="alternative.id.toString()" 
          class="block w-full cursor-pointer"
        >

          <div class="flex min-h-10 w-full items-center bg-gray-100 rounded-md pl-10 relative hover:bg-gray-200 transition-colors" @click="answerQuestion(questionId, alternative.letter)">
            <RadioGroupItem
              :id="alternative.id.toString()"
              :value="alternative.letter"
              class="absolute left-3"
            />
            <span class="pl-5 py-3">{{ alternative.text }}</span>
          </div>
        </ShadLabel>
      </li>
    </ul>
  </RadioGroup>
</template>
  
  <script>

import { patchQuestionAnswer } from '@/services/testService'
import { Label as ShadLabel } from '@/components/ui/label'
import { RadioGroup, RadioGroupItem } from '@/components/ui/radio-group'

  export default {
    props: {
      modelValue: String, 
      questionId: Number, 
      alternatives: Array 
    },
    data(){
      return {
        questionAnswer: this.modelValue
      }
    },
    components: {
      ShadLabel,
      RadioGroup,
      RadioGroupItem
    },
    methods: {

      async answerQuestion(questionId, answer ){

        try{
          this.questionAnswer = answer;

          const response  = await patchQuestionAnswer({
            id: questionId,
            answer: answer, 
            usedTime: "01:00:40"
          })

          console.log(response)

        }catch(error){

          console.log(error)

        }

      }

    }
  }
  </script>