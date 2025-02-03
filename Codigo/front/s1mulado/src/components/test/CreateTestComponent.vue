<template>

    <div class="w-full h-full bg-white fex fex-column">

      <header class="flex flex-row justify-between items-center  h-20 w-full bg-white">
        <ButtonComponent @click="createTest" > Iniciar </ButtonComponent>

        <SelectMenu v-model="selectedArea" title="Área do conhecimento:" :options="this.options" />

      </header>

      <Test v-if="this.testData" :test="this.testData" />

      
      <LoginModal v-if="this.loginModalStore.active" @login-closed="handleUserCurrentTest"/>

    </div>

</template>

<script>

  import { useToast } from 'vue-toastification'

  import { useLoginModal } from '@/stores/modals.js'

  import { isUserLoggedIn } from '@/services/authService.js'
  import { getUserCurrentTest } from '@/services/userService.js'
  import {create} from '@/services/testService';

  import SelectMenu from '@/components/common/SelectMenu.vue'
  import ButtonComponent from '@/components/common/ButtonComponent.vue'
  import Test from '@/components/test/Test.vue';
  import LoginModal from '@/components/modal/LoginModal.vue';
  
  export default {
    name: 'CreateTestComponent',
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
        loginModalStore: useLoginModal(),

        toast: useToast()

      }
    },
    components: {
      SelectMenu,
      ButtonComponent,
      Test,
      LoginModal
    },
    methods: {
      
      async createTest() {

        if(!isUserLoggedIn()){
            this.loginModalStore.open()
            return
        }
        
        try {
          
          const response = await create({ "questionsNumber": 2, "knowledgeArea": "NATURE"});
          console.log('Resposta do servidor:', response);
          this.testData = response.data;
          
        } catch (error) {

          this.toast.error('Erro! Você já possui um teste em andamento.');
          console.error('Erro ao enviar dados:', error);

        }

      },

      async handleUserCurrentTest(){

          try{
              
              const response =  await getUserCurrentTest();

              if(response.data != undefined || response.data != undefined){
                this.testData =  response.data
                
              }
              
          }catch(error){
              console.log(error)
          }

      }

    },
    created(){

      if(isUserLoggedIn()){
        this.handleUserCurrentTest();
      }

    }

  };



</script>