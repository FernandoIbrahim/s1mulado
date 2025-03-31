<template>
    <Navbar/>
    <div class="bg-white w-full min-h-screen flex flex-col">
      <div class="flex font-[Inter] font-bold mt-20 md:mt-40 justify-center w-full md:flex">
        <h1 class="text-4xl md:text-6xl">s1mulado</h1>
      </div>
      <div class="flex flex-col md:flex-row font-[Inter] font-medium md:mt-10 mx-5 md:mx-20 p-5 md:p-10">
        <div class="w-full md:w-3/5 text-center md:text-left mt-10 md:mt-40">
          <h4 class="text-xl md:text-3xl">
            Obtenha o seu melhor resultado<br />
            na prova mais importante da sua vida.
          </h4>
          <div class="w-[200px] mt-5 md:mt-10 mx-auto md:mx-0">
            <button type="submit" @click="redirect"
              class="w-full rounded-md bg-midnight text-lg font-bold text-white shadow-xs focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-gray-600 py-2">
              Começar agora
            </button>
          </div>
        </div>
        <div class="w-full md:w-2/5 flex justify-center items-center mt-10 md:mt-0">
          <div class="w-full max-w-lg md:max-w-1xl bg-white rounded-md overflow-hidden">
            <img src="https://s1mulado.s3.sa-east-1.amazonaws.com/layout-imagens/enem.png" class="w-full object-cover" />
          </div>
        </div>
      </div>
    </div>
  
    <div class="bg-midnight w-full min-h-screen flex flex-col px-5 md:px-0">
      <div class="flex font-[Inter] font-extrabold mt-10 md:mt-40 justify-center w-full text-center">
        <h1 class="text-4xl md:text-6xl text-white">Comece agora a praticar</h1>
      </div>
      <div class="flex font-[Inter] font-medium mt-5 md:mt-10 w-full justify-center text-center">
        <h4 class="text-xl md:text-3xl text-white">
          Simulados realistas de graça<br />
          para você.
        </h4>
      </div>
      <div class="flex justify-center mt-10 w-full">
        <div class="w-full max-w-lg md:max-w-2xl bg-white rounded-md overflow-hidden">
          <img src="https://s1mulado.s3.sa-east-1.amazonaws.com/layout-imagens/simulado.png" class="w-full object-cover" />
        </div>
      </div>
    </div>

    <LoginModal v-if="this.loginModalStore.active" @login-closed="handleUserCurrentTest"/>

  </template>
  
  <script>
  import { isUserLoggedIn } from '@/services/authService';

  import Navbar from '@/components/landing-page/NavbarComponent.vue';
  import LoginModal from '@/components/modal/LoginModal.vue';
  import { useLoginModal } from '@/stores/modals'

  export default {
    name: "LandingPageLayout",
    data(){
      return {
        loginModalStore: useLoginModal()
      }
    },
    components: {
        Navbar,
        LoginModal
    },
    methods: {

      async redirect(){

        if(!isUserLoggedIn()) {

          this.loginModalStore.open();

        }else {

          this.$router.push('/home');

        }

      }

    }

  };
  </script>