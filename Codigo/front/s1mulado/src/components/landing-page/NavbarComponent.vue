<template>
  <nav class="bg-white w-full fixed top-0 left-0 z-50 font-[Inter]">
    <div class="max-w-7xl mx-auto px-5 md:px-10">
      <div class="flex justify-end md:justify-between items-center h-16">

        <!-- 🔹 MENU PARA TELAS GRANDES -->
        <div class="hidden md:flex space-x-6">
          <a href="#" class="text-gray-700 hover:text-gray-900 px-4">Início</a>
          <a href="#" class="text-gray-700 hover:text-gray-900 px-4">Funcionalidades</a>
          <a href="#" class="text-gray-700 hover:text-gray-900 px-4">Contato</a>
        </div>

        <!-- 🔹 BOTÃO "COMEÇAR AGORA" -->
        <div class="hidden md:block">
          <button @click="redirect" class="rounded-md bg-midnight text-white text-lg font-bold py-2 px-4 hover:bg-gray-800">
            Começar agora
          </button>
        </div>

        <!-- 🔹 BOTÃO MOBILE -->
        <div class="md:hidden">
          <button @click="toggleMenu" class="text-gray-700 focus:outline-none">
            <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16m-7 6h7"></path>
            </svg>
          </button>
        </div>
      </div>
    </div>

    <!-- 🔹 MENU MOBILE -->
    <div v-if="isOpen" 
         class="md:hidden bg-white border-t transition-all duration-900 ease-in-out transform"
         :class="isOpen ? 'translate-y-0' : '-translate-y-full'">
      <a href="#" class="block px-8 py-2 text-gray-700 hover:bg-gray-100">Início</a>
      <a href="#" class="block px-8 py-2 text-gray-700 hover:bg-gray-100">Funcionalidades</a>
      <a href="#" class="block px-8 py-2 text-gray-700 hover:bg-gray-100">Contato</a>
    </div>
  </nav>
</template>

<script>
export default {
data() {
  return {
    isOpen: false, // Estado do menu mobile
  };
},
methods: {
  toggleMenu() {
    this.isOpen = !this.isOpen;
  },

},
};
</script>

<style scoped>
/* Espaço para o conteúdo abaixo do Navbar */
body {
padding-top: 64px;
}
</style>
