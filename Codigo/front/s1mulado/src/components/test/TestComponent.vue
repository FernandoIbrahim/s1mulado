<template>

    <div class="w-full h-full bg-black">

      <header class="flex flex-row justify-between items-center  h-20 w-full bg-white">
        <ButtonComponent/>

        <SelectMenu title="Área do conhecimento:" :options="[
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

    export default {

      name: 'TestComponent',
      components: {
        SelectMenu,
        ButtonComponent
      }

    };

</script>