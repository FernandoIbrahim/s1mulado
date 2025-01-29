<template>

    <div class="w-full h-full bg-white fex fex-column">

      <header class="flex flex-row justify-between items-center  h-20 w-full bg-white">
        <ButtonComponent @click="handleButtonClick"/>

        <SelectMenu v-model="selectedArea" title="Área do conhecimento:" :options="this.options" />

      </header>

      <Test v-if="this.testData" :test="this.testData" />

    </div>

</template>

<script>


  import SelectMenu from '@/components/common/SelectMenu.vue'
  import ButtonComponent from '@/components/common/ButtonComponent.vue'
  import { API_JSON_CLIENT} from '@/lib/api';
  import {create} from '@/services/testService';
  import Test from '@/components/test/Test.vue';
  
  export default {
    name: 'TestComponent',
    data(){
      return {
        options: [
            { id: 1, name: 'Exatas' },
            { id: 2, name: 'Natureza' },
            { id: 3, name: 'Humanas' },
            { id: 4, name: 'Linguagens'}
        ],
        selectedArea: { id: 1, name: 'Exatas' },
        testData: null,
      }
    },
    components: {
      SelectMenu,
      ButtonComponent,
      Test
    },
    methods: {
      
      async handleButtonClick() {
        console.log("oi")
        // Verifica se uma área foi selecionada
        if (this.selectedArea) {
          try {

            const response = await create({ "questionsNumber": 1, "knowledgeArea": "NATURE"},{ 'Authorization': 'Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6IkZlcm5hbmRvSWJyYWhpbSIsInJvbGUiOiJTVFVERU5UIiwiZXhwIjoxNzM4MTIyMTAyfQ.SlOmGv2PuvV_GZjpNG-vm3r7GEgh3O2TDRclTHAcHN0'} );

            console.log('Resposta do servidor:', response);
            this.testData = response.data;

          } catch (error) {
  
            console.error('Erro ao enviar dados:', error);

          }
        } else {
          // Exibe alerta caso nenhuma área tenha sido selecionada
          alert('Por favor, selecione uma área de conhecimento.');
        }
      },
    }

  };



</script>