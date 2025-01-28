<template>

    <div class="w-full h-full bg-white">

      <header class="flex flex-row justify-between items-center  h-20 w-full bg-white">
        <ButtonComponent @click="handleButtonClick"/>

        <SelectMenu v-model="selectedArea" title="Área do conhecimento:" :options="[
            { id: 1, name: 'Exatas' },
            { id: 2, name: 'Natureza' },
            { id: 3, name: 'Humanas' },
            { id: 4, name: 'Linguagens'}
          ]" 
        />
      </header>

    </div>

</template>

<script>

  import SelectMenu from '@/components/SelectMenu.vue'
  import ButtonComponent from '@/components/ButtonComponent.vue'
  import axios from 'axios';
  
  export default {
    name: 'TestComponent',
    data(){
      return {
        selectedArea: null
      }
    },
    components: {
      SelectMenu,
      ButtonComponent,
    },
    methods: {
      async handleButtonClick() {
        console.log("oi")
        // Verifica se uma área foi selecionada
        if (this.selectedArea) {
          try {
            // Envia dados para o servidor via POST
            const response = await axios.post('http://localhost:8080/tests', {
              "questionsNumber": 1,
              "knowledgeArea": "NATURE"
            }, {
              headers: {
                'Authorization': 'Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6IkZlcm5hbmRvSWJyYWhpbSIsInJvbGUiOiJTVFVERU5UIiwiZXhwIjoxNzM4MDkxNjM1fQ.DvGMr3q6YhBhXDKaMGAXjNeGzeHlyfkSnoo9mGOpShw',
              }
            });
          
            // Exibe a resposta no console
            console.log('Resposta do servidor:', response.data);
          } catch (error) {
            // Trata erro caso a requisição falhe
            console.error('Erro ao enviar dados:', error);
          }
        } else {
          // Exibe alerta caso nenhuma área tenha sido selecionada
          alert('Por favor, selecione uma área de conhecimento.');
        }
      },
    },

  };



</script>