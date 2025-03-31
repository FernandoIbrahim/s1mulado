<template>

    <div class="w-full h-full bg-white fex fex-column mt-10">

      <header class="flex flex-row justify-between items-center  h-20 w-full bg-white p-13">
        <ButtonComponent @click="createTest" padding-x="8" padding-y="2" class="transition duration-100 hover:scale-102 hover:-translate-y-1"> Iniciar </ButtonComponent>

        <SelectMenu v-model="selectedArea" title="Área do conhecimento:" :options="this.options" />

      </header>

      <Test v-if="testStore.test" :test="testStore.test" /> 

      
      <LoginModal v-if="this.loginModalStore.active" @login-closed="handleUserCurrentTest"/>

    </div>

</template>

<script>

  import { useToast } from 'vue-toastification'

  import { useLoginModal } from '@/stores/modals.js'

  import { isUserLoggedIn } from '@/services/authService.js'
  import {create} from '@/services/testService';

  import { useCurrentTestStore } from '@/stores/currentTest'

  import SelectMenu from '@/components/common/SelectMenu.vue'
  import ButtonComponent from '@/components/common/ButtonComponent.vue'
  import Test from '@/components/test/TestComponent.vue';
  import LoginModal from '@/components/modal/LoginModal.vue';


  export default {
    name: 'CreateTestComponent',
    setup() {
      const testStore = useCurrentTestStore()
      return { testStore }
    },
    data() {

      return {

        options: [
            { id: 1, name: 'Exatas' },
            { id: 2, name: 'Natureza' },
            { id: 3, name: 'Humanas' },
            { id: 4, name: 'Linguagens'}
        ],
        selectedArea: { id: 1, name: 'Exatas' },
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
          await create({ 
            "questionsNumber": 10, 
            "knowledgeArea": "NATURE"
          })
          await this.testStore.update() // Atualiza a store após criar
        } catch (error) {
          this.toast.error('Erro! Você já possui um teste em andamento.')
        }
      },


      async handleUserCurrentTest() {
        await this.testStore.update() 
      }


    },

    async created() {
      if(isUserLoggedIn()) {

        try{

          await this.testStore.update();
          
        }catch(error){
          console.log(error)
        }

      }
    }



  }

</script>

