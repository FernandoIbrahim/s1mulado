<template>
  <RadioGroup v-model="selectedValue">
    <ul>
      <li
        v-for="alternative in alternatives"
        :key="alternative.id"
        class="text-gray-700 mb-2"
      >
        <!-- Label que envolve toda a área clicável -->
        <ShadLabel 
          :for="alternative.id.toString()" 
          class="block w-full cursor-pointer"
        >
          <!-- Container relativo para posicionar o radio button -->
          <div class="flex min-h-10 w-full items-center bg-gray-100 rounded-md pl-10 relative hover:bg-gray-200 transition-colors">
            <!-- Radio button posicionado absolutamente à esquerda -->
            <RadioGroupItem
              :id="alternative.id.toString()"
              :value="alternative.letter"
              class="absolute left-3"
            />
            <!-- Texto da alternativa -->
            <span class="pl-5 py-3">{{ alternative.text }}</span>
          </div>
        </ShadLabel>
      </li>
    </ul>
  </RadioGroup>
</template>
  
  <script>

import { Label as ShadLabel } from '@/components/ui/label'
import { RadioGroup, RadioGroupItem } from '@/components/ui/radio-group'

  export default {
    props: {
      modelValue: String, // Recebe o valor selecionado do pai
      questionId: Number, // ID da questão
      alternatives: Array // Alternativas da questão
    },
    computed: {
      selectedValue: {
        get() {
          return this.modelValue;
        },
        set(value) {
        }
      }
    },
    components: {
      ShadLabel,
      RadioGroup,
      RadioGroupItem
    }
  }
  </script>